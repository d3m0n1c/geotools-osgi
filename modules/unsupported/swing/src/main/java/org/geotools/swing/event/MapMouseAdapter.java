/*
 *    GeoTools - The Open Source Java GIS Toolkit
 *    http://geotools.org
 *
 *    (C) 2003-2008, Open Source Geospatial Foundation (OSGeo)
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

package org.geotools.swing.event;


/**
 * An adapter class that implements all of the mouse event handling methods
 * defined in the MapMouseListener interface as empty methods, allowing sub-classes
 * to just override the methods they need. 
 *
 * @author Michael Bedward
 * @since 2.6
 *
 *
 * @source $URL: http://svn.osgeo.org/geotools/tags/8.0-M1/modules/unsupported/swing/src/main/java/org/geotools/swing/event/MapMouseAdapter.java $
 * @version $Id: MapMouseAdapter.java 37306 2011-05-25 06:13:21Z mbedward $
 */
public class MapMouseAdapter implements MapMouseListener {

    /**
     * Respond to a mouse click event received from the map pane
     *
     * @param ev the mouse event
     */
    public void onMouseClicked(MapMouseEvent ev) {}

    /**
     * Respond to a mouse dragged event received from the map pane
     *
     * @param ev the mouse event
     */
    public void onMouseDragged(MapMouseEvent ev) {}

    /**
     * Respond to a mouse entered event received from the map pane
     *
     * @param ev the mouse event
     */
    public void onMouseEntered(MapMouseEvent ev) {}

    /**
     * Respond to a mouse exited event received from the map pane
     *
     * @param ev the mouse event
     */
    public void onMouseExited(MapMouseEvent ev) {}

    /**
     * Respond to a mouse movement event received from the map pane
     *
     * @param ev the mouse event
     */
    public void onMouseMoved(MapMouseEvent ev) {}

    /**
     * Respond to a mouse button press event received from the map pane
     *
     * @param ev the mouse event
     */
    public void onMousePressed(MapMouseEvent ev) {}

    /**
     * Respond to a mouse button release event received from the map pane
     *
     * @param ev the mouse event
     */
    public void onMouseReleased(MapMouseEvent ev) {}

    /**
     * Respond to a mouse wheel scroll event received from the map pane
     *
     * @param ev the mouse event
     */
    public void onMouseWheelMoved(MapMouseEvent ev) {}

}
