/*
 *    GeoTools - The Open Source Java GIS Toolkit
 *    http://geotools.org
 * 
 *    (C) 2004-2008, Open Source Geospatial Foundation (OSGeo)
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
 *    This package contains documentation from OpenGIS specifications.
 *    OpenGIS consortium's work is fully acknowledged here.
 */
package org.geotools.metadata.iso.quality;

import org.opengis.metadata.quality.CompletenessCommission;


/**
 * Excess data present in the dataset, as described by the scope.
 *
 *
 * @source $URL: http://svn.osgeo.org/geotools/tags/8.0-M1/modules/library/metadata/src/main/java/org/geotools/metadata/iso/quality/CompletenessCommissionImpl.java $
 * @version $Id: CompletenessCommissionImpl.java 37293 2011-05-25 03:48:38Z mbedward $
 * @author Martin Desruisseaux (IRD)
 * @author Touraïvane
 *
 * @since 2.1
 */
public class CompletenessCommissionImpl extends CompletenessImpl implements CompletenessCommission {
    /**
     * Serial number for interoperability with different versions.
     */
    private static final long serialVersionUID = 1565144822249562765L;

    /**
     * Constructs an initially empty completeness commission.
     */
    public CompletenessCommissionImpl() {
    }

    /**
     * Constructs a metadata entity initialized with the values from the specified metadata.
     *
     * @since 2.4
     */
    public CompletenessCommissionImpl(final CompletenessCommission source) {
        super(source);
    }
}
