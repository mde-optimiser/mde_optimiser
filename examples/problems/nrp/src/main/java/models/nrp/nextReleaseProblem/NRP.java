/** */
package models.nrp.nextReleaseProblem;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 *
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>NRP</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>The following features are supported:
 *
 * <ul>
 *   <li>{@link models.nrp.nextReleaseProblem.NRP#getCustomers <em>Customers</em>}
 *   <li>{@link models.nrp.nextReleaseProblem.NRP#getAvailableArtifacts <em>Available
 *       Artifacts</em>}
 *   <li>{@link models.nrp.nextReleaseProblem.NRP#getRequirements <em>Requirements</em>}
 *   <li>{@link models.nrp.nextReleaseProblem.NRP#getSolutions <em>Solutions</em>}
 * </ul>
 *
 * @see models.nrp.nextReleaseProblem.EcorePackage#getNRP()
 * @model
 * @generated
 */
public interface NRP extends EObject {
  /**
   * Returns the value of the '<em><b>Customers</b></em>' containment reference list. The list
   * contents are of type {@link models.nrp.nextReleaseProblem.Customer}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Customers</em>' containment reference list.
   * @see models.nrp.nextReleaseProblem.EcorePackage#getNRP_Customers()
   * @model containment="true" required="true"
   * @generated
   */
  EList<Customer> getCustomers();

  /**
   * Returns the value of the '<em><b>Available Artifacts</b></em>' containment reference list. The
   * list contents are of type {@link models.nrp.nextReleaseProblem.SoftwareArtifact}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Available Artifacts</em>' containment reference list.
   * @see models.nrp.nextReleaseProblem.EcorePackage#getNRP_AvailableArtifacts()
   * @model containment="true" required="true"
   * @generated
   */
  EList<SoftwareArtifact> getAvailableArtifacts();

  /**
   * Returns the value of the '<em><b>Requirements</b></em>' containment reference list. The list
   * contents are of type {@link models.nrp.nextReleaseProblem.Requirement}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Requirements</em>' containment reference list.
   * @see models.nrp.nextReleaseProblem.EcorePackage#getNRP_Requirements()
   * @model containment="true" required="true"
   * @generated
   */
  EList<Requirement> getRequirements();

  /**
   * Returns the value of the '<em><b>Solutions</b></em>' containment reference list. The list
   * contents are of type {@link models.nrp.nextReleaseProblem.Solution}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Solutions</em>' containment reference list.
   * @see models.nrp.nextReleaseProblem.EcorePackage#getNRP_Solutions()
   * @model containment="true"
   * @generated
   */
  EList<Solution> getSolutions();
} // NRP
