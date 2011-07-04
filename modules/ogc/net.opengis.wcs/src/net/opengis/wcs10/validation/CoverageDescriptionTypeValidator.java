/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.opengis.wcs10.validation;

import org.eclipse.emf.common.util.EList;

/**
 * A sample validator interface for {@link net.opengis.wcs10.CoverageDescriptionType}.
 * This doesn't really do anything, and it's not a real EMF artifact.
 * It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can be extended.
 * This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface CoverageDescriptionTypeValidator {
    boolean validate();

    boolean validateCoverageOffering(EList value);
    boolean validateUpdateSequence(String value);
    boolean validateVersion(String value);
}
