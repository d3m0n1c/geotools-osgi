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
package org.geotools.temporal.object;

import org.opengis.temporal.Duration;

/**
 * A data type to be used for describing length or distance in the temporal dimension.
 *
 * @author Mehdi Sidhoum (Geomatys)
 *
 *
 * @source $URL: http://svn.osgeo.org/geotools/tags/8.0-M1/modules/library/main/src/main/java/org/geotools/temporal/object/DefaultDuration.java $
 */
public abstract class DefaultDuration implements Duration {
    public abstract long getTimeInMillis();
}
