/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.opengis.wcs10.validation;

import net.opengis.wcs10.SpatialSubsetType;
import net.opengis.wcs10.TimeSequenceType;

/**
 * A sample validator interface for {@link net.opengis.wcs10.DomainSubsetType}.
 * This doesn't really do anything, and it's not a real EMF artifact.
 * It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can be extended.
 * This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface DomainSubsetTypeValidator {
    boolean validate();

    boolean validateSpatialSubset(SpatialSubsetType value);
    boolean validateTemporalSubset(TimeSequenceType value);
    boolean validateTemporalSubset1(TimeSequenceType value);
}
