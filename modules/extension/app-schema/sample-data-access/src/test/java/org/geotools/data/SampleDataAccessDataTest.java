/*
 *    GeoTools - The Open Source Java GIS Toolkit
 *    http://geotools.org
 *
 *    (C) 2008-2011, Open Source Geospatial Foundation (OSGeo)
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

package org.geotools.data;

import java.util.List;

import junit.framework.TestCase;

import org.geotools.data.SampleDataAccessData;
import org.opengis.feature.Feature;

/**
 * Test the test data created by {@link SampleDataAccessData}.
 * 
 * @author Ben Caradoc-Davies (CSIRO Earth Science and Resource Engineering)
 * @version $Id: SampleDataAccessDataTest.java 37417 2011-06-13 04:40:41Z bencaradocdavies $
 *
 *
 * @source $URL: http://svn.osgeo.org/geotools/tags/8.0-M1/modules/extension/app-schema/sample-data-access/src/test/java/org/geotools/data/SampleDataAccessDataTest.java $
 * @since 2.6
 */
public class SampleDataAccessDataTest extends TestCase {

    /**
     * Test that two features are created.
     */
    public static void testData() {
        List<Feature> features = SampleDataAccessData.createMappedFeatures();
        assertEquals(2, features.size());
    }

}
