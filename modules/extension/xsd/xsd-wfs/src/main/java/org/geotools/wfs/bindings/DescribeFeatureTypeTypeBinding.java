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
package org.geotools.wfs.bindings;

import javax.xml.namespace.QName;

import net.opengis.wfs.BaseRequestType;
import net.opengis.wfs.DescribeFeatureTypeType;
import net.opengis.wfs.WfsFactory;

import org.geotools.wfs.WFS;
import org.geotools.xml.AbstractComplexEMFBinding;
import org.geotools.xml.Binding;
import org.geotools.xml.ElementInstance;
import org.geotools.xml.Node;


/**
 * Binding object for the type
 * http://www.opengis.net/wfs:DescribeFeatureTypeType.
 *
 * <p>
 *
 * <pre>
 *         <code>
 *  &lt;xsd:complexType name=&quot;DescribeFeatureTypeType&quot;&gt;
 *      &lt;xsd:annotation&gt;
 *          &lt;xsd:documentation&gt;
 *              The DescribeFeatureType operation allows a client application
 *              to request that a Web Feature Service describe one or more
 *              feature types.   A Web Feature Service must be able to generate
 *              feature descriptions as valid GML3 application schemas.
 *              The schemas generated by the DescribeFeatureType operation can
 *              be used by a client application to validate the output.
 *              Feature instances within the WFS interface must be specified
 *              using GML3.  The schema of feature instances specified within
 *              the WFS interface must validate against the feature schemas
 *              generated by the DescribeFeatureType request.
 *           &lt;/xsd:documentation&gt;
 *      &lt;/xsd:annotation&gt;
 *      &lt;xsd:complexContent&gt;
 *          &lt;xsd:extension base=&quot;wfs:BaseRequestType&quot;&gt;
 *              &lt;xsd:sequence&gt;
 *                  &lt;xsd:element maxOccurs=&quot;unbounded&quot; minOccurs=&quot;0&quot;
 *                      name=&quot;TypeName&quot; type=&quot;xsd:QName&quot;&gt;
 *                      &lt;xsd:annotation&gt;
 *                          &lt;xsd:documentation&gt;
 *                          The TypeName element is used to enumerate the
 *                          feature types to be described.  If no TypeName
 *                          elements are specified then all features should
 *                          be described.  The name must be a valid type
 *                          that belongs to the feature content as defined
 *                          by the GML Application Schema.
 *                       &lt;/xsd:documentation&gt;
 *                      &lt;/xsd:annotation&gt;
 *                  &lt;/xsd:element&gt;
 *              &lt;/xsd:sequence&gt;
 *              &lt;xsd:attribute default=&quot;text/xml; subtype=gml/3.1.1&quot;
 *                  name=&quot;outputFormat&quot; type=&quot;xsd:string&quot; use=&quot;optional&quot;&gt;
 *                  &lt;xsd:annotation&gt;
 *                      &lt;xsd:documentation&gt;
 *                       The outputFormat attribute is used to specify what schema
 *                       description language should be used to describe features.
 *                       The default value of 'text/xml; subtype=3.1.1' means that
 *                       the WFS must generate a GML3 application schema that can
 *                       be used to validate the GML3 output of a GetFeature
 *                       request or feature instances specified in Transaction
 *                       operations.
 *                       For the purposes of experimentation, vendor extension,
 *                       or even extensions that serve a specific community of
 *                       interest, other acceptable output format values may be
 *                       advertised by a WFS service in the capabilities document.
 *                       The meaning of such values in not defined in the WFS
 *                       specification.  The only proviso is such cases is that
 *                       clients may safely ignore outputFormat values that do
 *                       not recognize.
 *                    &lt;/xsd:documentation&gt;
 *                  &lt;/xsd:annotation&gt;
 *              &lt;/xsd:attribute&gt;
 *          &lt;/xsd:extension&gt;
 *      &lt;/xsd:complexContent&gt;
 *  &lt;/xsd:complexType&gt;
 * </code>
 *         </pre>
 *
 * </p>
 *
 * @generated
 *
 *
 * @source $URL: http://svn.osgeo.org/geotools/tags/8.0-M1/modules/extension/xsd/xsd-wfs/src/main/java/org/geotools/wfs/bindings/DescribeFeatureTypeTypeBinding.java $
 */
public class DescribeFeatureTypeTypeBinding extends AbstractComplexEMFBinding {
    private WfsFactory wfsFactory;

    public DescribeFeatureTypeTypeBinding(WfsFactory factory) {
        super(factory);
        this.wfsFactory = factory;
    }

    /**
     * @generated
     */
    public QName getTarget() {
        return WFS.DescribeFeatureTypeType;
    }

    /**
     * @return {@link Binding#BEFORE} so at parse time we can return an instance
     *         of {@link DescribeFeatureTypeType}, or the framework would try
     *         to create a {@link BaseRequestType},which is abstract.
     */
    public int getExecutionMode() {
        return BEFORE;
    }

    /**
     * Simply returns an instance of {@link DescribeFeatureTypeType} and lets
     * the framework to reflectively set the object properties.
     */
    public Object parse(ElementInstance instance, Node node, Object value)
        throws Exception {
        return wfsFactory.createDescribeFeatureTypeType();
    }
}