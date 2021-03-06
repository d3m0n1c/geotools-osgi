/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.opengis.wcs10;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Time Sequence Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * An ordered sequence of time positions or intervals. The time positions and periods shall be ordered from the oldest to the newest.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link net.opengis.wcs10.TimeSequenceType#getGroup <em>Group</em>}</li>
 *   <li>{@link net.opengis.wcs10.TimeSequenceType#getTimePosition <em>Time Position</em>}</li>
 *   <li>{@link net.opengis.wcs10.TimeSequenceType#getTimePeriod <em>Time Period</em>}</li>
 * </ul>
 * </p>
 *
 * @see net.opengis.wcs10.Wcs10Package#getTimeSequenceType()
 * @model extendedMetaData="name='TimeSequenceType' kind='elementOnly'"
 * @generated
 */
public interface TimeSequenceType extends EObject {
    /**
	 * Returns the value of the '<em><b>Group</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Group</em>' attribute list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Group</em>' attribute list.
	 * @see net.opengis.wcs10.Wcs10Package#getTimeSequenceType_Group()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='group' name='group:0'"
	 * @generated
	 */
    FeatureMap getGroup();

    /**
	 * Returns the value of the '<em><b>Time Position</b></em>' containment reference list.
	 * The list contents are of type {@link net.opengis.gml.TimePositionType}.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Direct representation of a temporal position.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Time Position</em>' containment reference list.
	 * @see net.opengis.wcs10.Wcs10Package#getTimeSequenceType_TimePosition()
	 * @model type="net.opengis.gml.TimePositionType" containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='timePosition' namespace='http://www.opengis.net/gml' group='#group:0'"
	 * @generated
	 */
    EList getTimePosition();

    /**
	 * Returns the value of the '<em><b>Time Period</b></em>' containment reference list.
	 * The list contents are of type {@link net.opengis.wcs10.TimePeriodType}.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Time Period</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Time Period</em>' containment reference list.
	 * @see net.opengis.wcs10.Wcs10Package#getTimeSequenceType_TimePeriod()
	 * @model type="net.opengis.wcs10.TimePeriodType" containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='timePeriod' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
    EList getTimePeriod();

} // TimeSequenceType
