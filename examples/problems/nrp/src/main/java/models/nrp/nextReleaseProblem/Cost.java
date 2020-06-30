/** */
package models.nrp.nextReleaseProblem;

import org.eclipse.emf.ecore.EObject;

/**
 *
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Cost</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>The following features are supported:
 *
 * <ul>
 *   <li>{@link models.nrp.nextReleaseProblem.Cost#getType <em>Type</em>}
 *   <li>{@link models.nrp.nextReleaseProblem.Cost#getAmount <em>Amount</em>}
 * </ul>
 *
 * @see models.nrp.nextReleaseProblem.EcorePackage#getCost()
 * @model
 * @generated
 */
public interface Cost extends EObject {
  /**
   * Returns the value of the '<em><b>Type</b></em>' attribute. The literals are from the
   * enumeration {@link models.nrp.nextReleaseProblem.CostType}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Type</em>' attribute.
   * @see models.nrp.nextReleaseProblem.CostType
   * @see #setType(CostType)
   * @see models.nrp.nextReleaseProblem.EcorePackage#getCost_Type()
   * @model
   * @generated
   */
  CostType getType();

  /**
   * Sets the value of the '{@link models.nrp.nextReleaseProblem.Cost#getType <em>Type</em>}'
   * attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @param value the new value of the '<em>Type</em>' attribute.
   * @see models.nrp.nextReleaseProblem.CostType
   * @see #getType()
   * @generated
   */
  void setType(CostType value);

  /**
   * Returns the value of the '<em><b>Amount</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Amount</em>' attribute.
   * @see #setAmount(double)
   * @see models.nrp.nextReleaseProblem.EcorePackage#getCost_Amount()
   * @model
   * @generated
   */
  double getAmount();

  /**
   * Sets the value of the '{@link models.nrp.nextReleaseProblem.Cost#getAmount <em>Amount</em>}'
   * attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @param value the new value of the '<em>Amount</em>' attribute.
   * @see #getAmount()
   * @generated
   */
  void setAmount(double value);
} // Cost
