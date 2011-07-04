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
package org.geotools.graph.structure.basic;

import java.util.ArrayList;
import java.util.Iterator;

import org.geotools.graph.structure.Edge;
import org.geotools.graph.structure.Node;

/**
 * Basic implementation of Edge.
 * 
 * @author Justin Deoliveira, Refractions Research Inc, jdeolive@refractions.net
 *
 *
 * @source $URL: http://svn.osgeo.org/geotools/tags/8.0-M1/modules/extension/graph/src/main/java/org/geotools/graph/structure/basic/BasicEdge.java $
 */
public class BasicEdge extends BasicGraphable implements Edge {

  /** A node */
  private Node m_nodeA;
  
  /** B node */
  private Node m_nodeB;
  
  /**
   * Constructs a new edge.
   * 
   * @param nodeA A node of edge.
   * @param nodeB B node of edge.
   */
  public BasicEdge(Node nodeA, Node nodeB) {
    super();
    m_nodeA = nodeA;
    m_nodeB = nodeB;
  }
  
  /**
   * @see Edge#getNodeA()
   */
  public Node getNodeA() {
    return(m_nodeA);
  }

  /**
   * @see Edge#getNodeB()
   */
  public Node getNodeB() {
    return(m_nodeB);
  }

  /**
   * Returns null if the specified node is neither the A node or the B node.
   * 
   * @see Edge#getOtherNode(Node)
   */
  public Node getOtherNode(Node node) {
    if (node.equals(m_nodeA)) return(m_nodeB);
    if (node.equals(m_nodeB)) return(m_nodeA);
    return(null);
  }

   /** 
   * Returns all edges that are adjacent to both the A and B nodes.  This
   * iterator is generated by calculating an underlying collection upon each 
   * method call. 
   *
   * @see org.geotools.graph.structure.Graphable#getRelated()
   */
  public Iterator getRelated() {
  	ArrayList adj = new ArrayList();
    
    for (Iterator itr = m_nodeA.getEdges().iterator(); itr.hasNext();) {
      Edge e = (Edge)itr.next();
      switch(e.compareNodes(this)) {
        case EQUAL_NODE_ORIENTATION: //same node orientation
          if (e.equals(this)) continue;
        case OPPOSITE_NODE_ORIENTATION: //opposite node orientation
        case UNEQUAL_NODE_ORIENTATION: //different
          adj.add(e);  	
      }
    }
    
    for (Iterator itr = m_nodeB.getEdges().iterator(); itr.hasNext();) {
      Edge e = (Edge)itr.next();
      switch(e.compareNodes(this)) {
        case EQUAL_NODE_ORIENTATION: 
        case OPPOSITE_NODE_ORIENTATION: 
          continue; //edges already added from other node
        case UNEQUAL_NODE_ORIENTATION:
          adj.add(e);  	
      }
    }
    
    return(adj.iterator());
  }

  /**
   * @see Edge#reverse()
   */
  public void reverse() {
    Node n = m_nodeA;
    m_nodeA = m_nodeB;
    m_nodeB = n;
  }
  
  /**
   * @see Edge#compareNodes(Edge)
   */
  public int compareNodes(Edge other) {
    if(m_nodeA.equals(other.getNodeA()) && m_nodeB.equals(other.getNodeB())) 
      return(EQUAL_NODE_ORIENTATION);
    
    if(
      (m_nodeA.equals(other.getNodeA()) && m_nodeB.equals(other.getNodeB()))||
      (m_nodeA.equals(other.getNodeB()) && m_nodeB.equals(other.getNodeA()))
    ) return(OPPOSITE_NODE_ORIENTATION);
  
    return(UNEQUAL_NODE_ORIENTATION);
  }
  
  /**
   * Returns ([A node.toString()],[B node.toString()]). 
   */
  public String toString() {
    return(super.toString()+" ("+m_nodeA.getID()+","+m_nodeB.getID()+")");  
  }
}