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
package org.geotools.graph.structure.line;

import org.geotools.graph.structure.basic.BasicNode;

import com.vividsolutions.jts.geom.Coordinate;

/**
 * Basic implementation of XYNode extended from BasicNode. The coordinate is
 * stored in the underlying object reference of the node.
 * 
 * @see org.geotools.graph.structure.basic.BasicNode
 * 
 * @author Justin Deoliveira, Refractions Research Inc, jdeolive@refractions.net
 * 
 *
 * @source $URL: http://svn.osgeo.org/geotools/tags/8.0-M1/modules/extension/graph/src/main/java/org/geotools/graph/structure/line/BasicXYNode.java $
 */
public class BasicXYNode extends BasicNode implements XYNode {

  /**
   * @see XYNode#getCoordinate()
   */
  public Coordinate getCoordinate() {
    return((Coordinate)getObject());
  }

  /**
   * @see XYNode#setCoordinate(Coordinate)
   */
  public void setCoordinate(Coordinate c) {
    setObject(c);
  }
}