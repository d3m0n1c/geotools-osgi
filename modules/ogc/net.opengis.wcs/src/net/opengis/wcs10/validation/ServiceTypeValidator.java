/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.opengis.wcs10.validation;

import net.opengis.gml.CodeListType;

import net.opengis.wcs10.ResponsiblePartyType;

import org.eclipse.emf.common.util.EList;

/**
 * A sample validator interface for {@link net.opengis.wcs10.ServiceType}.
 * This doesn't really do anything, and it's not a real EMF artifact.
 * It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can be extended.
 * This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface ServiceTypeValidator {
    boolean validate();

    boolean validateKeywords(EList value);
    boolean validateResponsibleParty(ResponsiblePartyType value);
    boolean validateFees(CodeListType value);
    boolean validateAccessConstraints(EList value);
    boolean validateUpdateSequence(String value);
    boolean validateVersion(String value);
}
