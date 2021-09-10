/** */
package models.knapsack.ecore;

import org.eclipse.emf.common.util.EList;

/**
 *
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Knapsack</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>The following features are supported:
 *
 * <ul>
 *   <li>{@link models.knapsack.ecore.Knapsack#getCapacity <em>Capacity</em>}
 *   <li>{@link models.knapsack.ecore.Knapsack#getContains <em>Contains</em>}
 * </ul>
 *
 * @see models.knapsack.ecore.EcorePackage#getKnapsack()
 * @model
 * @generated
 */
public interface Knapsack extends NamedElement {
  /**
   * Returns the value of the '<em><b>Capacity</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Capacity</em>' attribute.
   * @see #setCapacity(Double)
   * @see models.knapsack.ecore.EcorePackage#getKnapsack_Capacity()
   * @model required="true"
   * @generated
   */
  Double getCapacity();

  /**
   * Sets the value of the '{@link models.knapsack.ecore.Knapsack#getCapacity <em>Capacity</em>}'
   * attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @param value the new value of the '<em>Capacity</em>' attribute.
   * @see #getCapacity()
   * @generated
   */
  void setCapacity(Double value);

  /**
   * Returns the value of the '<em><b>Contains</b></em>' reference list. The list contents are of
   * type {@link models.knapsack.ecore.Item}. It is bidirectional and its opposite is '{@link
   * models.knapsack.ecore.Item#getIsContainedBy <em>Is Contained By</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Contains</em>' reference list.
   * @see models.knapsack.ecore.EcorePackage#getKnapsack_Contains()
   * @see models.knapsack.ecore.Item#getIsContainedBy
   * @model opposite="isContainedBy"
   * @generated
   */
  EList<Item> getContains();
} // Knapsack
