/** */
package models.nrp.nextReleaseProblem;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 *
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Solution</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>The following features are supported:
 *
 * <ul>
 *   <li>{@link models.nrp.nextReleaseProblem.Solution#getSelectedArtifacts <em>Selected
 *       Artifacts</em>}
 * </ul>
 *
 * @see models.nrp.nextReleaseProblem.EcorePackage#getSolution()
 * @model
 * @generated
 */
public interface Solution extends EObject {
  /**
   * Returns the value of the '<em><b>Selected Artifacts</b></em>' reference list. The list contents
   * are of type {@link models.nrp.nextReleaseProblem.SoftwareArtifact}. It is bidirectional and its
   * opposite is '{@link models.nrp.nextReleaseProblem.SoftwareArtifact#getSolutions
   * <em>Solutions</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Selected Artifacts</em>' reference list.
   * @see models.nrp.nextReleaseProblem.EcorePackage#getSolution_SelectedArtifacts()
   * @see models.nrp.nextReleaseProblem.SoftwareArtifact#getSolutions
   * @model opposite="solutions"
   * @generated
   */
  EList<SoftwareArtifact> getSelectedArtifacts();
} // Solution
