/**
 * <copyright>
 * </copyright>
 *
 * $Id: AcceptVersionsType.java 29859 2008-04-09 04:42:44Z jdeolive $
 */
package net.opengis.ows11;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Accept Versions Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Prioritized sequence of one or more specification versions accepted by client, with preferred versions listed first. See Version negotiation subclause for more information. 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link net.opengis.ows11.AcceptVersionsType#getVersion <em>Version</em>}</li>
 * </ul>
 * </p>
 *
 * @see net.opengis.ows11.Ows11Package#getAcceptVersionsType()
 * @model extendedMetaData="name='AcceptVersionsType' kind='elementOnly'"
 * @generated
 */
public interface AcceptVersionsType extends EObject {
    /**
     * Returns the value of the '<em><b>Version</b></em>' attribute list.
     * The list contents are of type {@link java.lang.String}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Version</em>' attribute list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Version</em>' attribute list.
     * @see net.opengis.ows11.Ows11Package#getAcceptVersionsType_Version()
     * @model unique="false" dataType="net.opengis.ows11.VersionType" required="true"
     *        extendedMetaData="kind='element' name='Version' namespace='##targetNamespace'"
     * @generated
     */
    EList getVersion();

} // AcceptVersionsType
