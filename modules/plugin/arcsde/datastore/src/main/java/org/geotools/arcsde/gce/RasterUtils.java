/*
 *    GeoTools - The Open Source Java GIS Toolkit
 *    http://geotools.org
 *
 *    (C) 2002-2009, Open Source Geospatial Foundation (OSGeo)
 *
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Lesser General Public
 *    License as published by the Free Software Foundation;
 *    version 2.1 of the License.
 *
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *    Lesser General Public License for more details.
 *
 */
package org.geotools.arcsde.gce;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.image.DataBuffer;
import java.awt.image.IndexColorModel;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.geotools.coverage.grid.GeneralGridRange;
import org.geotools.data.DataSourceException;
import org.geotools.geometry.GeneralEnvelope;
import org.geotools.geometry.jts.ReferencedEnvelope;
import org.geotools.referencing.CRS;
import org.geotools.referencing.operation.builder.GridToEnvelopeMapper;
import org.geotools.resources.image.ColorUtilities;
import org.geotools.util.logging.Logging;
import org.opengis.coverage.grid.GridCoverage;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.datum.PixelInCell;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.NoninvertibleTransformException;
import org.opengis.referencing.operation.TransformException;

import com.esri.sde.sdk.client.SeCoordinateReference;
import com.esri.sde.sdk.pe.PeCoordinateSystem;
import com.esri.sde.sdk.pe.PeFactory;
import com.esri.sde.sdk.pe.PeGeographicCS;
import com.esri.sde.sdk.pe.PeProjectedCS;
import com.esri.sde.sdk.pe.PeProjectionException;

/**
 * 
 * @author Gabriel Roldan (OpenGeo)
 * @since 2.5.4
 * @version $Id: RasterUtils.java 32465 2009-02-11 00:12:14Z groldan $
 * @source $URL$
 */
@SuppressWarnings( { "nls", "deprecation" })
class RasterUtils {

    private static final Logger LOGGER = Logging.getLogger("org.geotools.arcsde.gce");

    private RasterUtils() {
        // do nothing
    }

    public static ReferencedEnvelope toReferencedEnvelope(GeneralEnvelope envelope) {
        double minx = envelope.getMinimum(0);
        double maxx = envelope.getMaximum(0);
        double miny = envelope.getMinimum(1);
        double maxy = envelope.getMaximum(1);
        CoordinateReferenceSystem crs = envelope.getCoordinateReferenceSystem();

        ReferencedEnvelope refEnv = new ReferencedEnvelope(minx, maxx, miny, maxy, crs);
        return refEnv;
    }

    public static ReferencedEnvelope toNativeCrs(final GeneralEnvelope requestedEnvelope,
            final CoordinateReferenceSystem nativeCRS) throws IllegalArgumentException {

        ReferencedEnvelope reqEnv = toReferencedEnvelope(requestedEnvelope);

        if (!CRS.equalsIgnoreMetadata(nativeCRS, reqEnv.getCoordinateReferenceSystem())) {
            // we're being reprojected. We'll need to reproject reqEnv into
            // our native coordsys
            try {
                // ReferencedEnvelope origReqEnv = reqEnv;
                reqEnv = reqEnv.transform(nativeCRS, true);
            } catch (FactoryException fe) {
                // unable to reproject?
                throw new IllegalArgumentException("Unable to find a reprojection from requested "
                        + "coordsys to native coordsys for this request", fe);
            } catch (TransformException te) {
                throw new IllegalArgumentException("Unable to perform reprojection from requested "
                        + "coordsys to native coordsys for this request", te);
            }
        }
        return reqEnv;
    }

    /**
     * Gets the coordinate system that will be associated to the {@link GridCoverage}.
     * 
     * @param rasterAttributes
     */
    public static CoordinateReferenceSystem findCompatibleCRS(final SeCoordinateReference seCoordRef)
            throws DataSourceException {

        try {
            final PeCoordinateSystem coordSys = seCoordRef.getCoordSys();

            int epsgCode = -1;
            final int[] seEpsgCodes;
            if (coordSys instanceof PeGeographicCS) {
                seEpsgCodes = PeFactory.geogcsCodelist();
            } else if (coordSys instanceof PeProjectedCS) {
                seEpsgCodes = PeFactory.projcsCodelist();
            } else {
                throw new RuntimeException("Shouldnt happen!: Unnkown SeCoordSys type");
            }
            int seEpsgCode;
            PeCoordinateSystem candidate;
            for (int i = 0; i < seEpsgCodes.length; i++) {
                try {
                    seEpsgCode = seEpsgCodes[i];
                    candidate = (PeCoordinateSystem) PeFactory.factory(seEpsgCode);
                    // in ArcSDE 9.2, if the PeFactory doesn't support a projection it claimed to
                    // support, it returns 'null'. So check for it.
                    if (candidate != null && candidate.getName().trim().equals(coordSys.getName())) {
                        epsgCode = seEpsgCode;
                        break;
                    }
                } catch (PeProjectionException pe) {
                    // Strangely SDE includes codes in the projcsCodeList() that
                    // it doesn't actually support.
                    // Catch the exception and skip them here.
                }
            }

            CoordinateReferenceSystem crs;
            if (epsgCode == -1) {
                ArcSDERasterFormat.LOGGER.warning("Couldn't determine EPSG code for this raster."
                        + "  Using SDE's WKT-like coordSysDescription() instead.");
                crs = CRS.parseWKT(seCoordRef.getCoordSysDescription());
            } else {
                crs = CRS.decode("EPSG:" + epsgCode);
            }
            return crs;
        } catch (FactoryException e) {
            ArcSDERasterFormat.LOGGER.log(Level.SEVERE, "", e);
            throw new DataSourceException(e);
        } catch (PeProjectionException e) {
            ArcSDERasterFormat.LOGGER.log(Level.SEVERE, "", e);
            throw new DataSourceException(e);
        }
    }

    public static class QueryInfo {

        private GeneralEnvelope requestedEnvelope;

        private Rectangle requestedDim;

        private int pyramidLevel;

        /**
         * The two-dimensional range of tile indices whose envelope intersect the requested extent.
         * Will have negative width and height if none of the tiles do.
         */
        private Rectangle matchingTiles;

        private GeneralEnvelope resultEnvelope;

        private Rectangle resultDimension;

        @Override
        public String toString() {
            StringBuilder s = new StringBuilder("[Raster query info:");
            s.append("\n\tpyramid level        : ").append(pyramidLevel);
            s.append("\n\trequested envelope   : ").append(requestedEnvelope);
            s.append("\n\trequested dimension  : ").append(requestedDim);
            s.append("\n\tmatching tiles       : ").append(matchingTiles);
            s.append("\n\tresult envelope      : ").append(resultEnvelope);
            s.append("\n\tresult dimension     : ").append(resultDimension);
            s.append("\n]");
            return s.toString();
        }

        public GeneralEnvelope getRequestedEnvelope() {
            return requestedEnvelope;
        }

        public Rectangle getRequestedDim() {
            return requestedDim;
        }

        public int getPyramidLevel() {
            return pyramidLevel;
        }

        public Rectangle getMatchingTiles() {
            return matchingTiles;
        }

        public GeneralEnvelope getResultEnvelope() {
            return resultEnvelope;
        }

        public Rectangle getResultDimension() {
            return resultDimension;
        }
    }

    public static QueryInfo fitRequestToRaster(final GeneralEnvelope requestedEnvelope,
            final Rectangle requestedDim, final ArcSDEPyramid pyramidInfo, final int pyramidLevel) {
        final ArcSDEPyramidLevel level = pyramidInfo.getPyramidLevel(pyramidLevel);

        final CoordinateReferenceSystem nativeCrs;
        {
            nativeCrs = level.getEnvelope().getCoordinateReferenceSystem();
            CoordinateReferenceSystem requestCrs = requestedEnvelope.getCoordinateReferenceSystem();
            if (!CRS.equalsIgnoreMetadata(nativeCrs, requestCrs)) {
                throw new IllegalArgumentException("Request CRS and native CRS shall be equivalent");
            }
        }

        QueryInfo queryInfo = new QueryInfo();
        queryInfo.requestedEnvelope = requestedEnvelope;
        queryInfo.requestedDim = requestedDim;
        queryInfo.pyramidLevel = pyramidLevel;

        final Rectangle levelGridRange = level.getRange();

        final GeneralEnvelope levelEnvelope = new GeneralEnvelope(level.getEnvelope());
        final MathTransform rasterToModel = createRasterToModel(levelGridRange, levelEnvelope);

        Rectangle pixelSpaceOverlappingArea;
        pixelSpaceOverlappingArea = calculateMatchingLevelDimension(requestedEnvelope,
                rasterToModel, levelGridRange);
        if (pixelSpaceOverlappingArea.width > 0 && pixelSpaceOverlappingArea.height > 0) {
            // there is at least one pixel to query
            queryInfo.resultEnvelope = getResultEnvelope(pixelSpaceOverlappingArea, rasterToModel,
                    nativeCrs);

            Dimension tileSize = pyramidInfo.getTileDimension();
            queryInfo.matchingTiles = finaMatchingTiles(tileSize, level.getNumTilesWide(), level
                    .getNumTilesHigh(), pixelSpaceOverlappingArea);
            queryInfo.resultDimension = getResultDimensionForTileRange(queryInfo.matchingTiles,
                    tileSize, pixelSpaceOverlappingArea);
        }
        // calculateQueryDimensionAndEnvelope(queryInfo, pyramidInfo);

        return queryInfo;
    }

    public static MathTransform createRasterToModel(final Rectangle levelRange,
            final GeneralEnvelope levelEnvelope) {
        /*
         * GeneralGridRange range's is exclusive for the higher coords, so we expand it by one in
         * both dimensions
         */
        Rectangle expandedRange = new Rectangle(levelRange.x, levelRange.y, levelRange.width + 1,
                levelRange.height + 1);
        GeneralGridRange gridRange = new GeneralGridRange(expandedRange);

        // create a raster to model transform, from this tile pixel space to the tile's geographic
        // extent
        GridToEnvelopeMapper geMapper = new GridToEnvelopeMapper(gridRange, levelEnvelope);
        geMapper.setPixelAnchor(PixelInCell.CELL_CORNER);

        final MathTransform rasterToModel = geMapper.createTransform();
        return rasterToModel;
    }

    private static GeneralEnvelope getResultEnvelope(final Rectangle pixelSpaceOverlappingArea,
            final MathTransform rasterToModel, final CoordinateReferenceSystem crs) {

        GeneralEnvelope envelope = new GeneralEnvelope(crs);
        envelope.setEnvelope(pixelSpaceOverlappingArea.getMinX(), pixelSpaceOverlappingArea
                .getMinY(), pixelSpaceOverlappingArea.getMaxX(), pixelSpaceOverlappingArea
                .getMaxY());

        GeneralEnvelope resultingEnvelope;
        try {
            resultingEnvelope = CRS.transform(rasterToModel, envelope);
        } catch (TransformException e) {
            throw new RuntimeException("Error transforming pixel range to target CRS");
        }
        resultingEnvelope.setCoordinateReferenceSystem(crs);
        return resultingEnvelope;
    }

    private static Rectangle getResultDimensionForTileRange(final Rectangle matchingTiles,
            final Dimension tileSize, final Rectangle pixelRange) {

        int minx = pixelRange.x - (tileSize.width * matchingTiles.x);
        int miny = pixelRange.y - (tileSize.height * matchingTiles.y);
        return new Rectangle(minx, miny, pixelRange.width, pixelRange.height);
    }

    /**
     * Returns the rectangle specifying the matching tiles for a given pyramid level and rectangle
     * specifying the overlapping area to request in the level's pixel space.
     * 
     * @param pixelRange
     * @param tilesHigh
     * @param tilesWide
     * @param tileSize
     * @param numTilesHigh
     * @param numTilesWide
     * 
     * @param pixelRange
     * @param level
     * 
     * @return a rectangle holding the coordinates in tile space that fully covers the requested
     *         pixel range for the given pyramid level, or a negative area rectangle
     */
    private static Rectangle finaMatchingTiles(final Dimension tileSize, int numTilesWide,
            int numTilesHigh, final Rectangle pixelRange) {

        final int minPixelX = pixelRange.x;
        final int minPixelY = pixelRange.y;

        int minTileX = (int) Math.floor(minPixelX / tileSize.getWidth());
        int minTileY = (int) Math.floor(minPixelY / tileSize.getHeight());

        int numTilesX = (int) Math.ceil(pixelRange.getWidth() / tileSize.getWidth());
        int numTilesY = (int) Math.ceil(pixelRange.getHeight() / tileSize.getHeight());

        Rectangle matchingTiles = new Rectangle(minTileX, minTileY, numTilesX - minTileX, numTilesY
                - minTileY);
        return matchingTiles;
    }

    /**
     * For a given pyramid level and request extent, calculates the minimum pixel range overlapping
     * the requested geographical area
     * 
     * @param level
     * @param requestedEnvelope
     * 
     * @return a rectangle in the pyramid level pixel space covering the overlapping area with the
     *         requested envelope, or a zero area rectangle if the level does not geographically
     *         overlaps with the requested envelope.
     */
    static Rectangle calculateMatchingLevelDimension(final GeneralEnvelope requestedEnvelope,
            final MathTransform rasterToModel, final Rectangle levelGridRange) {

        Rectangle levelOverlappingPixels;
        int levelMinPixelX;
        int levelMaxPixelX;
        int levelMinPixelY;
        int levelMaxPixelY;
        {
            // use a model to raster transform to find out which pixel range at the specified level
            // better match the requested extent
            GeneralEnvelope requestedPixels;
            try {
                MathTransform modelToRaster = rasterToModel.inverse();
                requestedPixels = CRS.transform(modelToRaster, requestedEnvelope);
            } catch (NoninvertibleTransformException e) {
                throw new IllegalArgumentException(e);
            } catch (TransformException e) {
                throw new IllegalArgumentException(e);
            }

            levelMinPixelX = (int) Math.floor(requestedPixels.getMinimum(0));
            levelMaxPixelX = (int) Math.ceil(requestedPixels.getMaximum(0));

            levelMinPixelY = (int) Math.floor(requestedPixels.getMinimum(1));
            levelMaxPixelY = (int) Math.ceil(requestedPixels.getMaximum(1));

            final int width = levelMaxPixelX - levelMinPixelX;
            final int height = levelMaxPixelY - levelMinPixelY;
            levelOverlappingPixels = new Rectangle(levelMinPixelX, levelMinPixelY, width, height);
        }

        if (relates(levelGridRange, levelOverlappingPixels)) {
            // adapt the requested pixel extent to what the tile level can serve
            levelOverlappingPixels.x = Math.max(levelMinPixelX, levelGridRange.x);
            levelOverlappingPixels.y = Math.max(levelMinPixelY, levelGridRange.y);

            levelMaxPixelX = Math.min(levelMaxPixelX, levelGridRange.x + levelGridRange.width);
            levelMaxPixelY = Math.min(levelMaxPixelY, levelGridRange.y + levelGridRange.height);

            levelOverlappingPixels.width = levelMaxPixelX - levelOverlappingPixels.x;
            levelOverlappingPixels.height = levelMaxPixelY - levelOverlappingPixels.y;
        } else {
            // there are no overlapping pixels between the requested extent and the level extent
            levelOverlappingPixels.x = 0;
            levelOverlappingPixels.y = 0;
            levelOverlappingPixels.width = 0;
            levelOverlappingPixels.height = 0;
        }
        return levelOverlappingPixels;
    }

    private static boolean relates(Rectangle r1, Rectangle r2) {
        // expand r2 by one so intersects acts as relates (counts adjacent edges)
        return r1.intersects(new Rectangle(r2.x - 1, r2.y - 1, r2.width + 2, r2.height + 2));
    }

    public static IndexColorModel sdeColorMapToJavaColorModel(final int bitsPerPixel,
            final DataBuffer colorMapData) {
        if (colorMapData == null) {
            throw new NullPointerException("colorMapData");
        }

        if (colorMapData.getNumBanks() < 3 || colorMapData.getNumBanks() > 4) {
            throw new IllegalArgumentException("colorMapData shall have 3 or 4 banks: "
                    + colorMapData.getNumBanks());
        }

        final int numBanks = colorMapData.getNumBanks();
        final int mapSize = colorMapData.getSize();

        int[] ARGB = new int[mapSize];
        int r;
        int g;
        int b;
        int a;
        for (int i = 0; i < mapSize; i++) {
            r = colorMapData.getElem(0, i);
            g = colorMapData.getElem(1, i);
            b = colorMapData.getElem(2, i);
            a = numBanks == 4 ? colorMapData.getElem(3, i) : 255;
            int rgba = ColorUtilities.getIntFromColor(r, g, b, a);
            ARGB[i] = rgba;
        }

        IndexColorModel colorModel = ColorUtilities.getIndexColorModel(ARGB);

        return colorModel;
    }
}
