/*
 *    GeoTools - The Open Source Java GIS Toolkit
 *    http://geotools.org
 *
 *    (C) 2002-2008, Open Source Geospatial Foundation (OSGeo)
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
package org.geotools.wfs.v1_0;

import org.geotools.filter.v1_0.OGCConfiguration;

/**
 * Parser configuration for the wfs 1.0 schema.
 *
 * @generated
 *
 *
 * @source $URL: http://svn.osgeo.org/geotools/tags/8.0-M1/modules/extension/xsd/xsd-wfs/src/main/java/org/geotools/wfs/v1_0/WFSConfiguration.java $
 */
public class WFSConfiguration extends org.geotools.wfs.WFSConfiguration {
    /**
     * Creates a new configuration.
     *
     * @generated
     */
    public WFSConfiguration() {
        super(WFS.getInstance());
        
        addDependency(new OGCConfiguration());
    }
    
    protected void configureBindings(org.picocontainer.MutablePicoContainer container) {
        container.registerComponentImplementation(WFS.QueryType, QueryTypeBinding.class);
    }
}
