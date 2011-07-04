/**
 * <copyright>
 * </copyright>
 *
 * $Id: ContentsTypeValidator.java 29859 2008-04-09 04:42:44Z jdeolive $
 */
package net.opengis.wcs11.validation;

import org.eclipse.emf.common.util.EList;

/**
 * A sample validator interface for {@link net.opengis.wcs11.ContentsType}.
 * This doesn't really do anything, and it's not a real EMF artifact.
 * It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can be extended.
 * This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface ContentsTypeValidator {
    boolean validate();

    boolean validateCoverageSummary(EList value);
    boolean validateSupportedCRS(EList value);
    boolean validateSupportedFormat(EList value);
    boolean validateOtherSource(EList value);
}
