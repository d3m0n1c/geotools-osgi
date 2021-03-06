/*
 *    GeoTools - The Open Source Java GIS Toolkit
 *    http://geotools.org
 *
 *    (C) 2007-2008, Open Source Geospatial Foundation (OSGeo)
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
 */
package org.geotools.coverageio.gdal.ecw;

import it.geosolutions.imageio.plugins.ecw.ECWImageReaderSpi;

import org.geotools.coverage.grid.GridCoverage2D;
import org.geotools.coverageio.gdal.BaseGDALGridCoverage2DReader;
import org.geotools.data.DataSourceException;
import org.geotools.factory.Hints;
import org.opengis.coverage.grid.Format;
import org.opengis.coverage.grid.GridCoverageReader;

/**
 * This class can read a ECW data source and create a {@link GridCoverage2D}
 * from the data.
 * 
 * @author Daniele Romagnoli, GeoSolutions.
 * @author Simone Giannecchini (simboss), GeoSolutions
 * @since 2.5.x
 *
 * @source $URL: http://svn.osgeo.org/geotools/tags/8.0-M1/modules/plugin/imageio-ext-gdal/src/main/java/org/geotools/coverageio/gdal/ecw/ECWReader.java $
 */
public final class ECWReader extends BaseGDALGridCoverage2DReader implements
        GridCoverageReader {
    private final static String worldFileExt = ".eww";

    /**
     * Creates a new instance of a {@link ECWReader}. I assume nothing about
     * file extension.
     * 
     * @param input
     *                Source object for which we want to build an
     *                {@link ECWReader}.
     * @throws DataSourceException
     */
    public ECWReader(Object input) throws DataSourceException {
        this(input, null);
    }

    /**
     * Creates a new instance of a {@link ECWReader}. I assume nothing about
     * file extension.
     * 
     * @param input
     *                Source object for which we want to build an
     *                {@link ECWReader}.
     * @param hints
     *                Hints to be used by this reader throughout his life.
     * @throws DataSourceException
     */
    public ECWReader(Object input, Hints hints) throws DataSourceException {
        super(input, hints, worldFileExt, new ECWImageReaderSpi());
    }

    /**
     * @see org.opengis.coverage.grid.GridCoverageReader#getFormat()
     */
    public Format getFormat() {
        return new ECWFormat();
    }
}
