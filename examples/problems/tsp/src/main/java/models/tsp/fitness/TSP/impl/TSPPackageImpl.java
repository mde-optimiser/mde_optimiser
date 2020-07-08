/** */
package models.tsp.fitness.TSP.impl;

import models.tsp.fitness.TSP.City;
import models.tsp.fitness.TSP.Distance;
import models.tsp.fitness.TSP.Region;
import models.tsp.fitness.TSP.TSPFactory;
import models.tsp.fitness.TSP.TSPPackage;
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
public class TSPPackageImpl extends EPackageImpl implements TSPPackage {
  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  private EClass regionEClass = null;

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  private EClass cityEClass = null;

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  private EClass distanceEClass = null;

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
   * @see models.tsp.fitness.TSP.TSPPackage#eNS_URI
   * @see #init()
   * @generated
   */
  private TSPPackageImpl() {
    super(eNS_URI, TSPFactory.eINSTANCE);
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
   * <p>This method is used to initialize {@link TSPPackage#eINSTANCE} when that field is accessed.
   * Clients should not invoke it directly. Instead, they should simply access that field to obtain
   * the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
  public static TSPPackage init() {
    if (isInited) return (TSPPackage) EPackage.Registry.INSTANCE.getEPackage(TSPPackage.eNS_URI);

    // Obtain or create and register package
    Object registeredTSPPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
    TSPPackageImpl theTSPPackage =
        registeredTSPPackage instanceof TSPPackageImpl
            ? (TSPPackageImpl) registeredTSPPackage
            : new TSPPackageImpl();

    isInited = true;

    // Create package meta-data objects
    theTSPPackage.createPackageContents();

    // Initialize created meta-data
    theTSPPackage.initializePackageContents();

    // Mark meta-data to indicate it can't be changed
    theTSPPackage.freeze();

    // Update the registry and return the package
    EPackage.Registry.INSTANCE.put(TSPPackage.eNS_URI, theTSPPackage);
    return theTSPPackage;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public EClass getRegion() {
    return regionEClass;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public EAttribute getRegion_Name() {
    return (EAttribute) regionEClass.getEStructuralFeatures().get(0);
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public EReference getRegion_Cities() {
    return (EReference) regionEClass.getEStructuralFeatures().get(1);
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public EReference getRegion_Distances() {
    return (EReference) regionEClass.getEStructuralFeatures().get(2);
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public EClass getCity() {
    return cityEClass;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public EAttribute getCity_Name() {
    return (EAttribute) cityEClass.getEStructuralFeatures().get(0);
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public EAttribute getCity_Start() {
    return (EAttribute) cityEClass.getEStructuralFeatures().get(1);
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public EReference getCity_Origin() {
    return (EReference) cityEClass.getEStructuralFeatures().get(2);
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public EReference getCity_Destination() {
    return (EReference) cityEClass.getEStructuralFeatures().get(3);
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public EReference getCity_Next() {
    return (EReference) cityEClass.getEStructuralFeatures().get(4);
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public EClass getDistance() {
    return distanceEClass;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public EAttribute getDistance_Distance() {
    return (EAttribute) distanceEClass.getEStructuralFeatures().get(0);
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public EReference getDistance_From() {
    return (EReference) distanceEClass.getEStructuralFeatures().get(1);
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public EReference getDistance_To() {
    return (EReference) distanceEClass.getEStructuralFeatures().get(2);
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public TSPFactory getTSPFactory() {
    return (TSPFactory) getEFactoryInstance();
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
    regionEClass = createEClass(REGION);
    createEAttribute(regionEClass, REGION__NAME);
    createEReference(regionEClass, REGION__CITIES);
    createEReference(regionEClass, REGION__DISTANCES);

    cityEClass = createEClass(CITY);
    createEAttribute(cityEClass, CITY__NAME);
    createEAttribute(cityEClass, CITY__START);
    createEReference(cityEClass, CITY__ORIGIN);
    createEReference(cityEClass, CITY__DESTINATION);
    createEReference(cityEClass, CITY__NEXT);

    distanceEClass = createEClass(DISTANCE);
    createEAttribute(distanceEClass, DISTANCE__DISTANCE);
    createEReference(distanceEClass, DISTANCE__FROM);
    createEReference(distanceEClass, DISTANCE__TO);
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

    // Initialize classes, features, and operations; add parameters
    initEClass(
        regionEClass,
        Region.class,
        "Region",
        !IS_ABSTRACT,
        !IS_INTERFACE,
        IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(
        getRegion_Name(),
        ecorePackage.getEString(),
        "name",
        null,
        1,
        1,
        Region.class,
        !IS_TRANSIENT,
        !IS_VOLATILE,
        IS_CHANGEABLE,
        !IS_UNSETTABLE,
        !IS_ID,
        IS_UNIQUE,
        !IS_DERIVED,
        IS_ORDERED);
    initEReference(
        getRegion_Cities(),
        this.getCity(),
        null,
        "cities",
        null,
        0,
        -1,
        Region.class,
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
        getRegion_Distances(),
        this.getDistance(),
        null,
        "distances",
        null,
        0,
        -1,
        Region.class,
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
        cityEClass, City.class, "City", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(
        getCity_Name(),
        ecorePackage.getEString(),
        "name",
        null,
        1,
        1,
        City.class,
        !IS_TRANSIENT,
        !IS_VOLATILE,
        IS_CHANGEABLE,
        !IS_UNSETTABLE,
        !IS_ID,
        IS_UNIQUE,
        !IS_DERIVED,
        IS_ORDERED);
    initEAttribute(
        getCity_Start(),
        ecorePackage.getEBoolean(),
        "start",
        null,
        1,
        1,
        City.class,
        !IS_TRANSIENT,
        !IS_VOLATILE,
        IS_CHANGEABLE,
        !IS_UNSETTABLE,
        !IS_ID,
        IS_UNIQUE,
        !IS_DERIVED,
        IS_ORDERED);
    initEReference(
        getCity_Origin(),
        this.getDistance(),
        this.getDistance_From(),
        "origin",
        null,
        0,
        -1,
        City.class,
        !IS_TRANSIENT,
        !IS_VOLATILE,
        IS_CHANGEABLE,
        !IS_COMPOSITE,
        IS_RESOLVE_PROXIES,
        !IS_UNSETTABLE,
        IS_UNIQUE,
        !IS_DERIVED,
        IS_ORDERED);
    initEReference(
        getCity_Destination(),
        this.getDistance(),
        this.getDistance_To(),
        "destination",
        null,
        0,
        -1,
        City.class,
        !IS_TRANSIENT,
        !IS_VOLATILE,
        IS_CHANGEABLE,
        !IS_COMPOSITE,
        IS_RESOLVE_PROXIES,
        !IS_UNSETTABLE,
        IS_UNIQUE,
        !IS_DERIVED,
        IS_ORDERED);
    initEReference(
        getCity_Next(),
        this.getCity(),
        null,
        "next",
        null,
        0,
        1,
        City.class,
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
        distanceEClass,
        Distance.class,
        "Distance",
        !IS_ABSTRACT,
        !IS_INTERFACE,
        IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(
        getDistance_Distance(),
        ecorePackage.getEDoubleObject(),
        "distance",
        null,
        1,
        1,
        Distance.class,
        !IS_TRANSIENT,
        !IS_VOLATILE,
        IS_CHANGEABLE,
        !IS_UNSETTABLE,
        !IS_ID,
        IS_UNIQUE,
        !IS_DERIVED,
        IS_ORDERED);
    initEReference(
        getDistance_From(),
        this.getCity(),
        this.getCity_Origin(),
        "from",
        null,
        1,
        1,
        Distance.class,
        !IS_TRANSIENT,
        !IS_VOLATILE,
        IS_CHANGEABLE,
        !IS_COMPOSITE,
        IS_RESOLVE_PROXIES,
        !IS_UNSETTABLE,
        IS_UNIQUE,
        !IS_DERIVED,
        IS_ORDERED);
    initEReference(
        getDistance_To(),
        this.getCity(),
        this.getCity_Destination(),
        "to",
        null,
        1,
        1,
        Distance.class,
        !IS_TRANSIENT,
        !IS_VOLATILE,
        IS_CHANGEABLE,
        !IS_COMPOSITE,
        IS_RESOLVE_PROXIES,
        !IS_UNSETTABLE,
        IS_UNIQUE,
        !IS_DERIVED,
        IS_ORDERED);

    // Create resource
    createResource(eNS_URI);
  }
} // TSPPackageImpl
