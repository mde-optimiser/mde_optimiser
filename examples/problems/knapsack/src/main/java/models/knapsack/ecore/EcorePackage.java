/** */
package models.knapsack.ecore;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 *
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model. It contains accessors for the meta objects to represent
 *
 * <ul>
 *   <li>each class,
 *   <li>each feature of each class,
 *   <li>each operation of each class,
 *   <li>each enum,
 *   <li>and each data type
 * </ul>
 *
 * <!-- end-user-doc -->
 *
 * @see models.knapsack.ecore.EcoreFactory
 * @model kind="package"
 * @generated
 */
public interface EcorePackage extends EPackage {
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  String eNAME = "ecore";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  String eNS_URI = "http://uk.ac.kcl.inf.mdeoptimiser.examples/knapsack/1.0";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  String eNS_PREFIX = "models.knapsack";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  EcorePackage eINSTANCE = models.knapsack.ecore.impl.EcorePackageImpl.init();

  /**
   * The meta object id for the '{@link models.knapsack.ecore.impl.NamedElementImpl <em>Named
   * Element</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see models.knapsack.ecore.impl.NamedElementImpl
   * @see models.knapsack.ecore.impl.EcorePackageImpl#getNamedElement()
   * @generated
   */
  int NAMED_ELEMENT = 3;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int NAMED_ELEMENT__NAME = 0;

  /**
   * The number of structural features of the '<em>Named Element</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int NAMED_ELEMENT_FEATURE_COUNT = 1;

  /**
   * The number of operations of the '<em>Named Element</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int NAMED_ELEMENT_OPERATION_COUNT = 0;

  /**
   * The meta object id for the '{@link models.knapsack.ecore.impl.PlanImpl <em>Plan</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see models.knapsack.ecore.impl.PlanImpl
   * @see models.knapsack.ecore.impl.EcorePackageImpl#getPlan()
   * @generated
   */
  int PLAN = 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int PLAN__NAME = NAMED_ELEMENT__NAME;

  /**
   * The feature id for the '<em><b>Items</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int PLAN__ITEMS = NAMED_ELEMENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Knapsacks</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int PLAN__KNAPSACKS = NAMED_ELEMENT_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Plan</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int PLAN_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 2;

  /**
   * The number of operations of the '<em>Plan</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int PLAN_OPERATION_COUNT = NAMED_ELEMENT_OPERATION_COUNT + 0;

  /**
   * The meta object id for the '{@link models.knapsack.ecore.impl.ItemImpl <em>Item</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see models.knapsack.ecore.impl.ItemImpl
   * @see models.knapsack.ecore.impl.EcorePackageImpl#getItem()
   * @generated
   */
  int ITEM = 1;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int ITEM__NAME = NAMED_ELEMENT__NAME;

  /**
   * The feature id for the '<em><b>Weight</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int ITEM__WEIGHT = NAMED_ELEMENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Gain</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int ITEM__GAIN = NAMED_ELEMENT_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Is Contained By</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int ITEM__IS_CONTAINED_BY = NAMED_ELEMENT_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>Item</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int ITEM_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 3;

  /**
   * The number of operations of the '<em>Item</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int ITEM_OPERATION_COUNT = NAMED_ELEMENT_OPERATION_COUNT + 0;

  /**
   * The meta object id for the '{@link models.knapsack.ecore.impl.KnapsackImpl <em>Knapsack</em>}'
   * class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see models.knapsack.ecore.impl.KnapsackImpl
   * @see models.knapsack.ecore.impl.EcorePackageImpl#getKnapsack()
   * @generated
   */
  int KNAPSACK = 2;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int KNAPSACK__NAME = NAMED_ELEMENT__NAME;

  /**
   * The feature id for the '<em><b>Capacity</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int KNAPSACK__CAPACITY = NAMED_ELEMENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Contains</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int KNAPSACK__CONTAINS = NAMED_ELEMENT_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Knapsack</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int KNAPSACK_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 2;

  /**
   * The number of operations of the '<em>Knapsack</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int KNAPSACK_OPERATION_COUNT = NAMED_ELEMENT_OPERATION_COUNT + 0;

  /**
   * Returns the meta object for class '{@link models.knapsack.ecore.Plan <em>Plan</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the meta object for class '<em>Plan</em>'.
   * @see models.knapsack.ecore.Plan
   * @generated
   */
  EClass getPlan();

  /**
   * Returns the meta object for the containment reference list '{@link
   * models.knapsack.ecore.Plan#getItems <em>Items</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the meta object for the containment reference list '<em>Items</em>'.
   * @see models.knapsack.ecore.Plan#getItems()
   * @see #getPlan()
   * @generated
   */
  EReference getPlan_Items();

  /**
   * Returns the meta object for the containment reference list '{@link
   * models.knapsack.ecore.Plan#getKnapsacks <em>Knapsacks</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the meta object for the containment reference list '<em>Knapsacks</em>'.
   * @see models.knapsack.ecore.Plan#getKnapsacks()
   * @see #getPlan()
   * @generated
   */
  EReference getPlan_Knapsacks();

  /**
   * Returns the meta object for class '{@link models.knapsack.ecore.Item <em>Item</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the meta object for class '<em>Item</em>'.
   * @see models.knapsack.ecore.Item
   * @generated
   */
  EClass getItem();

  /**
   * Returns the meta object for the attribute '{@link models.knapsack.ecore.Item#getWeight
   * <em>Weight</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the meta object for the attribute '<em>Weight</em>'.
   * @see models.knapsack.ecore.Item#getWeight()
   * @see #getItem()
   * @generated
   */
  EAttribute getItem_Weight();

  /**
   * Returns the meta object for the attribute '{@link models.knapsack.ecore.Item#getGain
   * <em>Gain</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the meta object for the attribute '<em>Gain</em>'.
   * @see models.knapsack.ecore.Item#getGain()
   * @see #getItem()
   * @generated
   */
  EAttribute getItem_Gain();

  /**
   * Returns the meta object for the reference '{@link models.knapsack.ecore.Item#getIsContainedBy
   * <em>Is Contained By</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the meta object for the reference '<em>Is Contained By</em>'.
   * @see models.knapsack.ecore.Item#getIsContainedBy()
   * @see #getItem()
   * @generated
   */
  EReference getItem_IsContainedBy();

  /**
   * Returns the meta object for class '{@link models.knapsack.ecore.Knapsack <em>Knapsack</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the meta object for class '<em>Knapsack</em>'.
   * @see models.knapsack.ecore.Knapsack
   * @generated
   */
  EClass getKnapsack();

  /**
   * Returns the meta object for the attribute '{@link models.knapsack.ecore.Knapsack#getCapacity
   * <em>Capacity</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the meta object for the attribute '<em>Capacity</em>'.
   * @see models.knapsack.ecore.Knapsack#getCapacity()
   * @see #getKnapsack()
   * @generated
   */
  EAttribute getKnapsack_Capacity();

  /**
   * Returns the meta object for the reference list '{@link
   * models.knapsack.ecore.Knapsack#getContains <em>Contains</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the meta object for the reference list '<em>Contains</em>'.
   * @see models.knapsack.ecore.Knapsack#getContains()
   * @see #getKnapsack()
   * @generated
   */
  EReference getKnapsack_Contains();

  /**
   * Returns the meta object for class '{@link models.knapsack.ecore.NamedElement <em>Named
   * Element</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the meta object for class '<em>Named Element</em>'.
   * @see models.knapsack.ecore.NamedElement
   * @generated
   */
  EClass getNamedElement();

  /**
   * Returns the meta object for the attribute '{@link models.knapsack.ecore.NamedElement#getName
   * <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see models.knapsack.ecore.NamedElement#getName()
   * @see #getNamedElement()
   * @generated
   */
  EAttribute getNamedElement_Name();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the factory that creates the instances of the model.
   * @generated
   */
  EcoreFactory getEcoreFactory();

  /**
   *
   * <!-- begin-user-doc -->
   * Defines literals for the meta objects that represent
   *
   * <ul>
   *   <li>each class,
   *   <li>each feature of each class,
   *   <li>each operation of each class,
   *   <li>each enum,
   *   <li>and each data type
   * </ul>
   *
   * <!-- end-user-doc -->
   *
   * @generated
   */
  interface Literals {
    /**
     * The meta object literal for the '{@link models.knapsack.ecore.impl.PlanImpl <em>Plan</em>}'
     * class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see models.knapsack.ecore.impl.PlanImpl
     * @see models.knapsack.ecore.impl.EcorePackageImpl#getPlan()
     * @generated
     */
    EClass PLAN = eINSTANCE.getPlan();

    /**
     * The meta object literal for the '<em><b>Items</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    EReference PLAN__ITEMS = eINSTANCE.getPlan_Items();

    /**
     * The meta object literal for the '<em><b>Knapsacks</b></em>' containment reference list
     * feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    EReference PLAN__KNAPSACKS = eINSTANCE.getPlan_Knapsacks();

    /**
     * The meta object literal for the '{@link models.knapsack.ecore.impl.ItemImpl <em>Item</em>}'
     * class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see models.knapsack.ecore.impl.ItemImpl
     * @see models.knapsack.ecore.impl.EcorePackageImpl#getItem()
     * @generated
     */
    EClass ITEM = eINSTANCE.getItem();

    /**
     * The meta object literal for the '<em><b>Weight</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    EAttribute ITEM__WEIGHT = eINSTANCE.getItem_Weight();

    /**
     * The meta object literal for the '<em><b>Gain</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    EAttribute ITEM__GAIN = eINSTANCE.getItem_Gain();

    /**
     * The meta object literal for the '<em><b>Is Contained By</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    EReference ITEM__IS_CONTAINED_BY = eINSTANCE.getItem_IsContainedBy();

    /**
     * The meta object literal for the '{@link models.knapsack.ecore.impl.KnapsackImpl
     * <em>Knapsack</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see models.knapsack.ecore.impl.KnapsackImpl
     * @see models.knapsack.ecore.impl.EcorePackageImpl#getKnapsack()
     * @generated
     */
    EClass KNAPSACK = eINSTANCE.getKnapsack();

    /**
     * The meta object literal for the '<em><b>Capacity</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    EAttribute KNAPSACK__CAPACITY = eINSTANCE.getKnapsack_Capacity();

    /**
     * The meta object literal for the '<em><b>Contains</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    EReference KNAPSACK__CONTAINS = eINSTANCE.getKnapsack_Contains();

    /**
     * The meta object literal for the '{@link models.knapsack.ecore.impl.NamedElementImpl <em>Named
     * Element</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see models.knapsack.ecore.impl.NamedElementImpl
     * @see models.knapsack.ecore.impl.EcorePackageImpl#getNamedElement()
     * @generated
     */
    EClass NAMED_ELEMENT = eINSTANCE.getNamedElement();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    EAttribute NAMED_ELEMENT__NAME = eINSTANCE.getNamedElement_Name();
  }
} // EcorePackage
