/** */
package models.knapsack.ecore;

/**
 *
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Item</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>The following features are supported:
 *
 * <ul>
 *   <li>{@link models.knapsack.ecore.Item#getWeight <em>Weight</em>}
 *   <li>{@link models.knapsack.ecore.Item#getGain <em>Gain</em>}
 *   <li>{@link models.knapsack.ecore.Item#getIsContainedBy <em>Is Contained By</em>}
 * </ul>
 *
 * @see models.knapsack.ecore.EcorePackage#getItem()
 * @model
 * @generated
 */
public interface Item extends NamedElement {
  /**
   * Returns the value of the '<em><b>Weight</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Weight</em>' attribute.
   * @see #setWeight(Double)
   * @see models.knapsack.ecore.EcorePackage#getItem_Weight()
   * @model required="true"
   * @generated
   */
  Double getWeight();

  /**
   * Sets the value of the '{@link models.knapsack.ecore.Item#getWeight <em>Weight</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @param value the new value of the '<em>Weight</em>' attribute.
   * @see #getWeight()
   * @generated
   */
  void setWeight(Double value);

  /**
   * Returns the value of the '<em><b>Gain</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Gain</em>' attribute.
   * @see #setGain(Double)
   * @see models.knapsack.ecore.EcorePackage#getItem_Gain()
   * @model required="true"
   * @generated
   */
  Double getGain();

  /**
   * Sets the value of the '{@link models.knapsack.ecore.Item#getGain <em>Gain</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @param value the new value of the '<em>Gain</em>' attribute.
   * @see #getGain()
   * @generated
   */
  void setGain(Double value);

  /**
   * Returns the value of the '<em><b>Is Contained By</b></em>' reference. It is bidirectional and
   * its opposite is '{@link models.knapsack.ecore.Knapsack#getContains <em>Contains</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Is Contained By</em>' reference.
   * @see #setIsContainedBy(Knapsack)
   * @see models.knapsack.ecore.EcorePackage#getItem_IsContainedBy()
   * @see models.knapsack.ecore.Knapsack#getContains
   * @model opposite="contains"
   * @generated
   */
  Knapsack getIsContainedBy();

  /**
   * Sets the value of the '{@link models.knapsack.ecore.Item#getIsContainedBy <em>Is Contained
   * By</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @param value the new value of the '<em>Is Contained By</em>' reference.
   * @see #getIsContainedBy()
   * @generated
   */
  void setIsContainedBy(Knapsack value);
} // Item
