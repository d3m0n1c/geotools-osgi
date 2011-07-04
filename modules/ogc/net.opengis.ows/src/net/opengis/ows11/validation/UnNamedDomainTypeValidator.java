/**
 * <copyright>
 * </copyright>
 *
 * $Id: UnNamedDomainTypeValidator.java 29859 2008-04-09 04:42:44Z jdeolive $
 */
package net.opengis.ows11.validation;

import net.opengis.ows11.AllowedValuesType;
import net.opengis.ows11.AnyValueType;
import net.opengis.ows11.DomainMetadataType;
import net.opengis.ows11.NoValuesType;
import net.opengis.ows11.ValueType;
import net.opengis.ows11.ValuesReferenceType;

import org.eclipse.emf.common.util.EList;

/**
 * A sample validator interface for {@link net.opengis.ows11.UnNamedDomainType}.
 * This doesn't really do anything, and it's not a real EMF artifact.
 * It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can be extended.
 * This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface UnNamedDomainTypeValidator {
    boolean validate();

    boolean validateAllowedValues(AllowedValuesType value);
    boolean validateAnyValue(AnyValueType value);
    boolean validateNoValues(NoValuesType value);
    boolean validateValuesReference(ValuesReferenceType value);
    boolean validateDefaultValue(ValueType value);
    boolean validateMeaning(DomainMetadataType value);
    boolean validateDataType(DomainMetadataType value);
    boolean validateUOM(DomainMetadataType value);
    boolean validateReferenceSystem(DomainMetadataType value);
    boolean validateMetadata(EList value);
}
