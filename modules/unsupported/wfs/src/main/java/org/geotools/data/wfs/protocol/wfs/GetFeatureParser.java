/*
 *    GeoTools - The Open Source Java GIS Toolkit
 *    http://geotools.org
 *
 *    (C) 2008, Open Source Geospatial Foundation (OSGeo)
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
package org.geotools.data.wfs.protocol.wfs;

import java.io.IOException;

import org.opengis.feature.simple.SimpleFeature;

/**
 * Interface to abstract out the parsing of features coming from a GetFeature request from the
 * underlying xml parsing technology in use.
 * 
 * @author Gabriel Roldan (TOPP)
 * @version $Id: GetFeatureParser.java 37306 2011-05-25 06:13:21Z mbedward $
 * @since 2.5.x
 *
 *
 * @source $URL: http://svn.osgeo.org/geotools/tags/8.0-M1/modules/unsupported/wfs/src/main/java/org/geotools/data/wfs/protocol/wfs/GetFeatureParser.java $
 *         http://gtsvn.refractions.net/trunk/modules/plugin/wfs/src/main/java/org/geotools/data
 *         /wfs/protocol/wfs/GetFeatureParser.java $
 */
public interface GetFeatureParser {

    /**
     * Returns the number of features if advertised by the server and the parser was able to get
     * that information for example from the {@code wfs:FeatureCollection} "numberOfFeatures" xml
     * attribute, or {@code -1} if unknown.
     * 
     * @return number of features advertised by server, or {@code -1} if unknown
     */
    public int getNumberOfFeatures();

    /**
     * @return the next feature in the stream or {@code null} if there are no more features to
     *         parse.
     */
    SimpleFeature parse() throws IOException;

    void close() throws IOException;
}
