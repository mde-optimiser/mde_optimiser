/** */
package models.knapsack.ecore;

import org.eclipse.emf.common.util.EList;

/**
 *
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Plan</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>The following features are supported:
 *
 * <ul>
 *   <li>{@link models.knapsack.ecore.Plan#getItems <em>Items</em>}
 *   <li>{@link models.knapsack.ecore.Plan#getKnapsacks <em>Knapsacks</em>}
 * </ul>
 *
 * @see models.knapsack.ecore.EcorePackage#getPlan()
 * @model
 * @generated
 */
public interface Plan extends NamedElement {
  /**
   * Returns the value of the '<em><b>Items</b></em>' containment reference list. The list contents
   * are of type {@link models.knapsack.ecore.Item}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Items</em>' containment reference list.
   * @see models.knapsack.ecore.EcorePackage#getPlan_Items()
   * @model containment="true" ordered="false"
   * @generated
   */
  EList<Item> getItems();

  /**
   * Returns the value of the '<em><b>Knapsacks</b></em>' containment reference list. The list
   * contents are of type {@link models.knapsack.ecore.Knapsack}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Knapsacks</em>' containment reference list.
   * @see models.knapsack.ecore.EcorePackage#getPlan_Knapsacks()
   * @model containment="true" ordered="false"
   * @generated
   */
  EList<Knapsack> getKnapsacks();
} // Plan
