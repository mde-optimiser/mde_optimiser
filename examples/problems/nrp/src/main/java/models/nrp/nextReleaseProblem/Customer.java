/** */
package models.nrp.nextReleaseProblem;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 *
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Customer</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>The following features are supported:
 *
 * <ul>
 *   <li>{@link models.nrp.nextReleaseProblem.Customer#getName <em>Name</em>}
 *   <li>{@link models.nrp.nextReleaseProblem.Customer#getImportance <em>Importance</em>}
 *   <li>{@link models.nrp.nextReleaseProblem.Customer#getAssigns <em>Assigns</em>}
 * </ul>
 *
 * @see models.nrp.nextReleaseProblem.EcorePackage#getCustomer()
 * @model
 * @generated
 */
public interface Customer extends EObject {
  /**
   * Returns the value of the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Name</em>' attribute.
   * @see #setName(String)
   * @see models.nrp.nextReleaseProblem.EcorePackage#getCustomer_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link models.nrp.nextReleaseProblem.Customer#getName <em>Name</em>}'
   * attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Importance</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Importance</em>' attribute.
   * @see #setImportance(double)
   * @see models.nrp.nextReleaseProblem.EcorePackage#getCustomer_Importance()
   * @model
   * @generated
   */
  double getImportance();

  /**
   * Sets the value of the '{@link models.nrp.nextReleaseProblem.Customer#getImportance
   * <em>Importance</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @param value the new value of the '<em>Importance</em>' attribute.
   * @see #getImportance()
   * @generated
   */
  void setImportance(double value);

  /**
   * Returns the value of the '<em><b>Assigns</b></em>' reference list. The list contents are of
   * type {@link models.nrp.nextReleaseProblem.Valuation}. It is bidirectional and its opposite is
   * '{@link models.nrp.nextReleaseProblem.Valuation#getAssignedBy <em>Assigned By</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Assigns</em>' reference list.
   * @see models.nrp.nextReleaseProblem.EcorePackage#getCustomer_Assigns()
   * @see models.nrp.nextReleaseProblem.Valuation#getAssignedBy
   * @model opposite="assignedBy" required="true"
   * @generated
   */
  EList<Valuation> getAssigns();
} // Customer
