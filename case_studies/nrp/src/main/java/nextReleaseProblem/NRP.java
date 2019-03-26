/**
 */
package nextReleaseProblem;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>NRP</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link nextReleaseProblem.NRP#getCustomers <em>Customers</em>}</li>
 *   <li>{@link nextReleaseProblem.NRP#getAvailableArtifacts <em>Available Artifacts</em>}</li>
 *   <li>{@link nextReleaseProblem.NRP#getRequirements <em>Requirements</em>}</li>
 *   <li>{@link nextReleaseProblem.NRP#getSolutions <em>Solutions</em>}</li>
 * </ul>
 *
 * @see nextReleaseProblem.NextReleaseProblemPackage#getNRP()
 * @model
 * @generated
 */
public interface NRP extends EObject {
	/**
	 * Returns the value of the '<em><b>Customers</b></em>' containment reference list.
	 * The list contents are of type {@link nextReleaseProblem.Customer}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Customers</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Customers</em>' containment reference list.
	 * @see nextReleaseProblem.NextReleaseProblemPackage#getNRP_Customers()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<Customer> getCustomers();

	/**
	 * Returns the value of the '<em><b>Available Artifacts</b></em>' containment reference list.
	 * The list contents are of type {@link nextReleaseProblem.SoftwareArtifact}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Available Artifacts</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Available Artifacts</em>' containment reference list.
	 * @see nextReleaseProblem.NextReleaseProblemPackage#getNRP_AvailableArtifacts()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<SoftwareArtifact> getAvailableArtifacts();

	/**
	 * Returns the value of the '<em><b>Requirements</b></em>' containment reference list.
	 * The list contents are of type {@link nextReleaseProblem.Requirement}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Requirements</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Requirements</em>' containment reference list.
	 * @see nextReleaseProblem.NextReleaseProblemPackage#getNRP_Requirements()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<Requirement> getRequirements();

	/**
	 * Returns the value of the '<em><b>Solutions</b></em>' containment reference list.
	 * The list contents are of type {@link nextReleaseProblem.Solution}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Solutions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Solutions</em>' containment reference list.
	 * @see nextReleaseProblem.NextReleaseProblemPackage#getNRP_Solutions()
	 * @model containment="true"
	 * @generated
	 */
	EList<Solution> getSolutions();

} // NRP
