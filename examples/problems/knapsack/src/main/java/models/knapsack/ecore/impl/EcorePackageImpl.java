/** */
package models.knapsack.ecore.impl;

import models.knapsack.ecore.EcoreFactory;
import models.knapsack.ecore.EcorePackage;
import models.knapsack.ecore.Item;
import models.knapsack.ecore.Knapsack;
import models.knapsack.ecore.NamedElement;
import models.knapsack.ecore.Plan;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 *
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class EcorePackageImpl extends EPackageImpl implements EcorePackage {
  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  private EClass planEClass = null;

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  private EClass itemEClass = null;

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  private EClass knapsackEClass = null;

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  private EClass namedElementEClass = null;

  /**
   * Creates an instance of the model <b>Package</b>, registered with {@link
   * org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package package URI value.
   *
   * <p>Note: the correct way to create the package is via the static factory method {@link #init
   * init()}, which also performs initialization of the package, or returns the registered package,
   * if one already exists.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see org.eclipse.emf.ecore.EPackage.Registry
   * @see models.knapsack.ecore.EcorePackage#eNS_URI
   * @see #init()
   * @generated
   */
  private EcorePackageImpl() {
    super(eNS_URI, EcoreFactory.eINSTANCE);
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  private static boolean isInited = false;

  /**
   * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon
   * which it depends.
   *
   * <p>This method is used to initialize {@link EcorePackage#eINSTANCE} when that field is
   * accessed. Clients should not invoke it directly. Instead, they should simply access that field
   * to obtain the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
  public static EcorePackage init() {
    if (isInited)
      return (EcorePackage) EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);

    // Obtain or create and register package
    Object registeredEcorePackage = EPackage.Registry.INSTANCE.get(eNS_URI);
    EcorePackageImpl theEcorePackage =
        registeredEcorePackage instanceof EcorePackageImpl
            ? (EcorePackageImpl) registeredEcorePackage
            : new EcorePackageImpl();

    isInited = true;

    // Create package meta-data objects
    theEcorePackage.createPackageContents();

    // Initialize created meta-data
    theEcorePackage.initializePackageContents();

    // Mark meta-data to indicate it can't be changed
    theEcorePackage.freeze();

    // Update the registry and return the package
    EPackage.Registry.INSTANCE.put(EcorePackage.eNS_URI, theEcorePackage);
    return theEcorePackage;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public EClass getPlan() {
    return planEClass;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public EReference getPlan_Items() {
    return (EReference) planEClass.getEStructuralFeatures().get(0);
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public EReference getPlan_Knapsacks() {
    return (EReference) planEClass.getEStructuralFeatures().get(1);
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public EClass getItem() {
    return itemEClass;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public EAttribute getItem_Weight() {
    return (EAttribute) itemEClass.getEStructuralFeatures().get(0);
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public EAttribute getItem_Gain() {
    return (EAttribute) itemEClass.getEStructuralFeatures().get(1);
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public EReference getItem_IsContainedBy() {
    return (EReference) itemEClass.getEStructuralFeatures().get(2);
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public EClass getKnapsack() {
    return knapsackEClass;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public EAttribute getKnapsack_Capacity() {
    return (EAttribute) knapsackEClass.getEStructuralFeatures().get(0);
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public EReference getKnapsack_Contains() {
    return (EReference) knapsackEClass.getEStructuralFeatures().get(1);
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public EClass getNamedElement() {
    return namedElementEClass;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public EAttribute getNamedElement_Name() {
    return (EAttribute) namedElementEClass.getEStructuralFeatures().get(0);
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public EcoreFactory getEcoreFactory() {
    return (EcoreFactory) getEFactoryInstance();
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  private boolean isCreated = false;

  /**
   * Creates the meta-model objects for the package. This method is guarded to have no affect on any
   * invocation but its first.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public void createPackageContents() {
    if (isCreated) return;
    isCreated = true;

    // Create classes and their features
    planEClass = createEClass(PLAN);
    createEReference(planEClass, PLAN__ITEMS);
    createEReference(planEClass, PLAN__KNAPSACKS);

    itemEClass = createEClass(ITEM);
    createEAttribute(itemEClass, ITEM__WEIGHT);
    createEAttribute(itemEClass, ITEM__GAIN);
    createEReference(itemEClass, ITEM__IS_CONTAINED_BY);

    knapsackEClass = createEClass(KNAPSACK);
    createEAttribute(knapsackEClass, KNAPSACK__CAPACITY);
    createEReference(knapsackEClass, KNAPSACK__CONTAINS);

    namedElementEClass = createEClass(NAMED_ELEMENT);
    createEAttribute(namedElementEClass, NAMED_ELEMENT__NAME);
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  private boolean isInitialized = false;

  /**
   * Complete the initialization of the package and its meta-model. This method is guarded to have
   * no affect on any invocation but its first.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public void initializePackageContents() {
    if (isInitialized) return;
    isInitialized = true;

    // Initialize package
    setName(eNAME);
    setNsPrefix(eNS_PREFIX);
    setNsURI(eNS_URI);

    // Create type parameters

    // Set bounds for type parameters

    // Add supertypes to classes
    planEClass.getESuperTypes().add(this.getNamedElement());
    itemEClass.getESuperTypes().add(this.getNamedElement());
    knapsackEClass.getESuperTypes().add(this.getNamedElement());

    // Initialize classes, features, and operations; add parameters
    initEClass(
        planEClass, Plan.class, "Plan", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(
        getPlan_Items(),
        this.getItem(),
        null,
        "items",
        null,
        0,
        -1,
        Plan.class,
        !IS_TRANSIENT,
        !IS_VOLATILE,
        IS_CHANGEABLE,
        IS_COMPOSITE,
        !IS_RESOLVE_PROXIES,
        !IS_UNSETTABLE,
        IS_UNIQUE,
        !IS_DERIVED,
        !IS_ORDERED);
    initEReference(
        getPlan_Knapsacks(),
        this.getKnapsack(),
        null,
        "knapsacks",
        null,
        0,
        -1,
        Plan.class,
        !IS_TRANSIENT,
        !IS_VOLATILE,
        IS_CHANGEABLE,
        IS_COMPOSITE,
        !IS_RESOLVE_PROXIES,
        !IS_UNSETTABLE,
        IS_UNIQUE,
        !IS_DERIVED,
        !IS_ORDERED);

    initEClass(
        itemEClass, Item.class, "Item", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(
        getItem_Weight(),
        ecorePackage.getEDoubleObject(),
        "weight",
        null,
        1,
        1,
        Item.class,
        !IS_TRANSIENT,
        !IS_VOLATILE,
        IS_CHANGEABLE,
        !IS_UNSETTABLE,
        !IS_ID,
        IS_UNIQUE,
        !IS_DERIVED,
        IS_ORDERED);
    initEAttribute(
        getItem_Gain(),
        ecorePackage.getEDoubleObject(),
        "gain",
        null,
        1,
        1,
        Item.class,
        !IS_TRANSIENT,
        !IS_VOLATILE,
        IS_CHANGEABLE,
        !IS_UNSETTABLE,
        !IS_ID,
        IS_UNIQUE,
        !IS_DERIVED,
        IS_ORDERED);
    initEReference(
        getItem_IsContainedBy(),
        this.getKnapsack(),
        this.getKnapsack_Contains(),
        "isContainedBy",
        null,
        0,
        1,
        Item.class,
        !IS_TRANSIENT,
        !IS_VOLATILE,
        IS_CHANGEABLE,
        !IS_COMPOSITE,
        IS_RESOLVE_PROXIES,
        !IS_UNSETTABLE,
        IS_UNIQUE,
        !IS_DERIVED,
        IS_ORDERED);

    initEClass(
        knapsackEClass,
        Knapsack.class,
        "Knapsack",
        !IS_ABSTRACT,
        !IS_INTERFACE,
        IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(
        getKnapsack_Capacity(),
        ecorePackage.getEDoubleObject(),
        "capacity",
        null,
        1,
        1,
        Knapsack.class,
        !IS_TRANSIENT,
        !IS_VOLATILE,
        IS_CHANGEABLE,
        !IS_UNSETTABLE,
        !IS_ID,
        IS_UNIQUE,
        !IS_DERIVED,
        IS_ORDERED);
    initEReference(
        getKnapsack_Contains(),
        this.getItem(),
        this.getItem_IsContainedBy(),
        "contains",
        null,
        0,
        -1,
        Knapsack.class,
        !IS_TRANSIENT,
        !IS_VOLATILE,
        IS_CHANGEABLE,
        !IS_COMPOSITE,
        IS_RESOLVE_PROXIES,
        !IS_UNSETTABLE,
        IS_UNIQUE,
        !IS_DERIVED,
        IS_ORDERED);

    initEClass(
        namedElementEClass,
        NamedElement.class,
        "NamedElement",
        IS_ABSTRACT,
        !IS_INTERFACE,
        IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(
        getNamedElement_Name(),
        ecorePackage.getEString(),
        "name",
        null,
        1,
        1,
        NamedElement.class,
        !IS_TRANSIENT,
        !IS_VOLATILE,
        IS_CHANGEABLE,
        !IS_UNSETTABLE,
        !IS_ID,
        IS_UNIQUE,
        !IS_DERIVED,
        IS_ORDERED);

    // Create resource
    createResource(eNS_URI);
  }
} // EcorePackageImpl
