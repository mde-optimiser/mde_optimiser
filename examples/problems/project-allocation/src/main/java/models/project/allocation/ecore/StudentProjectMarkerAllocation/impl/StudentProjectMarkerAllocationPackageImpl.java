/** */
package models.project.allocation.ecore.StudentProjectMarkerAllocation.impl;

import models.project.allocation.ecore.StudentProjectMarkerAllocation.Academic;
import models.project.allocation.ecore.StudentProjectMarkerAllocation.MarkerAllocationModel;
import models.project.allocation.ecore.StudentProjectMarkerAllocation.MarkingTeam;
import models.project.allocation.ecore.StudentProjectMarkerAllocation.Project;
import models.project.allocation.ecore.StudentProjectMarkerAllocation.Student;
import models.project.allocation.ecore.StudentProjectMarkerAllocation.StudentProjectMarkerAllocationFactory;
import models.project.allocation.ecore.StudentProjectMarkerAllocation.StudentProjectMarkerAllocationPackage;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

/**
 *
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class StudentProjectMarkerAllocationPackageImpl extends EPackageImpl
    implements StudentProjectMarkerAllocationPackage {
  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  private EClass academicEClass = null;

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  private EClass projectEClass = null;

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  private EClass studentEClass = null;

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  private EClass markingTeamEClass = null;

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  private EClass markerAllocationModelEClass = null;

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
   * @see
   *     models.project.allocation.ecore.StudentProjectMarkerAllocation.StudentProjectMarkerAllocationPackage#eNS_URI
   * @see #init()
   * @generated
   */
  private StudentProjectMarkerAllocationPackageImpl() {
    super(eNS_URI, StudentProjectMarkerAllocationFactory.eINSTANCE);
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
   * <p>This method is used to initialize {@link StudentProjectMarkerAllocationPackage#eINSTANCE}
   * when that field is accessed. Clients should not invoke it directly. Instead, they should simply
   * access that field to obtain the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
  public static StudentProjectMarkerAllocationPackage init() {
    if (isInited)
      return (StudentProjectMarkerAllocationPackage)
          EPackage.Registry.INSTANCE.getEPackage(StudentProjectMarkerAllocationPackage.eNS_URI);

    // Obtain or create and register package
    Object registeredStudentProjectMarkerAllocationPackage =
        EPackage.Registry.INSTANCE.get(eNS_URI);
    StudentProjectMarkerAllocationPackageImpl theStudentProjectMarkerAllocationPackage =
        registeredStudentProjectMarkerAllocationPackage
                instanceof StudentProjectMarkerAllocationPackageImpl
            ? (StudentProjectMarkerAllocationPackageImpl)
                registeredStudentProjectMarkerAllocationPackage
            : new StudentProjectMarkerAllocationPackageImpl();

    isInited = true;

    // Initialize simple dependencies
    XMLTypePackage.eINSTANCE.eClass();

    // Create package meta-data objects
    theStudentProjectMarkerAllocationPackage.createPackageContents();

    // Initialize created meta-data
    theStudentProjectMarkerAllocationPackage.initializePackageContents();

    // Mark meta-data to indicate it can't be changed
    theStudentProjectMarkerAllocationPackage.freeze();

    // Update the registry and return the package
    EPackage.Registry.INSTANCE.put(
        StudentProjectMarkerAllocationPackage.eNS_URI, theStudentProjectMarkerAllocationPackage);
    return theStudentProjectMarkerAllocationPackage;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public EClass getAcademic() {
    return academicEClass;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public EReference getAcademic_Projects() {
    return (EReference) academicEClass.getEStructuralFeatures().get(0);
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public EReference getAcademic_SecondMarker() {
    return (EReference) academicEClass.getEStructuralFeatures().get(1);
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public EReference getAcademic_ThirdMarker() {
    return (EReference) academicEClass.getEStructuralFeatures().get(2);
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public EReference getAcademic_MarkingTeam() {
    return (EReference) academicEClass.getEStructuralFeatures().get(3);
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public EAttribute getAcademic_Name() {
    return (EAttribute) academicEClass.getEStructuralFeatures().get(4);
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public EAttribute getAcademic_Number() {
    return (EAttribute) academicEClass.getEStructuralFeatures().get(5);
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public EAttribute getAcademic_Email() {
    return (EAttribute) academicEClass.getEStructuralFeatures().get(6);
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public EClass getProject() {
    return projectEClass;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public EReference getProject_Supervisor() {
    return (EReference) projectEClass.getEStructuralFeatures().get(0);
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public EReference getProject_Student() {
    return (EReference) projectEClass.getEStructuralFeatures().get(1);
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public EReference getProject_SecondMarker() {
    return (EReference) projectEClass.getEStructuralFeatures().get(2);
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public EReference getProject_ThirdMarker() {
    return (EReference) projectEClass.getEStructuralFeatures().get(3);
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public EAttribute getProject_Title() {
    return (EAttribute) projectEClass.getEStructuralFeatures().get(4);
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public EAttribute getProject_Stage() {
    return (EAttribute) projectEClass.getEStructuralFeatures().get(5);
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public EClass getStudent() {
    return studentEClass;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public EReference getStudent_Project() {
    return (EReference) studentEClass.getEStructuralFeatures().get(0);
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public EAttribute getStudent_Email() {
    return (EAttribute) studentEClass.getEStructuralFeatures().get(1);
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public EAttribute getStudent_Name() {
    return (EAttribute) studentEClass.getEStructuralFeatures().get(2);
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public EAttribute getStudent_Number() {
    return (EAttribute) studentEClass.getEStructuralFeatures().get(3);
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public EClass getMarkingTeam() {
    return markingTeamEClass;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public EAttribute getMarkingTeam_Number() {
    return (EAttribute) markingTeamEClass.getEStructuralFeatures().get(0);
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public EReference getMarkingTeam_Academics() {
    return (EReference) markingTeamEClass.getEStructuralFeatures().get(1);
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public EClass getMarkerAllocationModel() {
    return markerAllocationModelEClass;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public EReference getMarkerAllocationModel_Students() {
    return (EReference) markerAllocationModelEClass.getEStructuralFeatures().get(0);
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public EReference getMarkerAllocationModel_Projects() {
    return (EReference) markerAllocationModelEClass.getEStructuralFeatures().get(1);
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public EReference getMarkerAllocationModel_Academics() {
    return (EReference) markerAllocationModelEClass.getEStructuralFeatures().get(2);
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public EReference getMarkerAllocationModel_MarkingTeams() {
    return (EReference) markerAllocationModelEClass.getEStructuralFeatures().get(3);
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public StudentProjectMarkerAllocationFactory getStudentProjectMarkerAllocationFactory() {
    return (StudentProjectMarkerAllocationFactory) getEFactoryInstance();
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
    academicEClass = createEClass(ACADEMIC);
    createEReference(academicEClass, ACADEMIC__PROJECTS);
    createEReference(academicEClass, ACADEMIC__SECOND_MARKER);
    createEReference(academicEClass, ACADEMIC__THIRD_MARKER);
    createEReference(academicEClass, ACADEMIC__MARKING_TEAM);
    createEAttribute(academicEClass, ACADEMIC__NAME);
    createEAttribute(academicEClass, ACADEMIC__NUMBER);
    createEAttribute(academicEClass, ACADEMIC__EMAIL);

    projectEClass = createEClass(PROJECT);
    createEReference(projectEClass, PROJECT__SUPERVISOR);
    createEReference(projectEClass, PROJECT__STUDENT);
    createEReference(projectEClass, PROJECT__SECOND_MARKER);
    createEReference(projectEClass, PROJECT__THIRD_MARKER);
    createEAttribute(projectEClass, PROJECT__TITLE);
    createEAttribute(projectEClass, PROJECT__STAGE);

    studentEClass = createEClass(STUDENT);
    createEReference(studentEClass, STUDENT__PROJECT);
    createEAttribute(studentEClass, STUDENT__EMAIL);
    createEAttribute(studentEClass, STUDENT__NAME);
    createEAttribute(studentEClass, STUDENT__NUMBER);

    markingTeamEClass = createEClass(MARKING_TEAM);
    createEAttribute(markingTeamEClass, MARKING_TEAM__NUMBER);
    createEReference(markingTeamEClass, MARKING_TEAM__ACADEMICS);

    markerAllocationModelEClass = createEClass(MARKER_ALLOCATION_MODEL);
    createEReference(markerAllocationModelEClass, MARKER_ALLOCATION_MODEL__STUDENTS);
    createEReference(markerAllocationModelEClass, MARKER_ALLOCATION_MODEL__PROJECTS);
    createEReference(markerAllocationModelEClass, MARKER_ALLOCATION_MODEL__ACADEMICS);
    createEReference(markerAllocationModelEClass, MARKER_ALLOCATION_MODEL__MARKING_TEAMS);
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

    // Obtain other dependent packages
    XMLTypePackage theXMLTypePackage =
        (XMLTypePackage) EPackage.Registry.INSTANCE.getEPackage(XMLTypePackage.eNS_URI);

    // Create type parameters

    // Set bounds for type parameters

    // Add supertypes to classes

    // Initialize classes, features, and operations; add parameters
    initEClass(
        academicEClass,
        Academic.class,
        "Academic",
        !IS_ABSTRACT,
        !IS_INTERFACE,
        IS_GENERATED_INSTANCE_CLASS);
    initEReference(
        getAcademic_Projects(),
        this.getProject(),
        this.getProject_Supervisor(),
        "projects",
        null,
        0,
        -1,
        Academic.class,
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
        getAcademic_SecondMarker(),
        this.getProject(),
        this.getProject_SecondMarker(),
        "secondMarker",
        null,
        1,
        -1,
        Academic.class,
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
        getAcademic_ThirdMarker(),
        this.getProject(),
        this.getProject_ThirdMarker(),
        "thirdMarker",
        null,
        1,
        -1,
        Academic.class,
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
        getAcademic_MarkingTeam(),
        this.getMarkingTeam(),
        this.getMarkingTeam_Academics(),
        "markingTeam",
        null,
        0,
        -1,
        Academic.class,
        !IS_TRANSIENT,
        !IS_VOLATILE,
        IS_CHANGEABLE,
        !IS_COMPOSITE,
        IS_RESOLVE_PROXIES,
        !IS_UNSETTABLE,
        IS_UNIQUE,
        !IS_DERIVED,
        IS_ORDERED);
    initEAttribute(
        getAcademic_Name(),
        theXMLTypePackage.getString(),
        "name",
        null,
        0,
        1,
        Academic.class,
        !IS_TRANSIENT,
        !IS_VOLATILE,
        IS_CHANGEABLE,
        !IS_UNSETTABLE,
        !IS_ID,
        IS_UNIQUE,
        !IS_DERIVED,
        IS_ORDERED);
    initEAttribute(
        getAcademic_Number(),
        theXMLTypePackage.getString(),
        "number",
        null,
        0,
        1,
        Academic.class,
        !IS_TRANSIENT,
        !IS_VOLATILE,
        IS_CHANGEABLE,
        !IS_UNSETTABLE,
        !IS_ID,
        IS_UNIQUE,
        !IS_DERIVED,
        IS_ORDERED);
    initEAttribute(
        getAcademic_Email(),
        theXMLTypePackage.getString(),
        "email",
        null,
        0,
        1,
        Academic.class,
        !IS_TRANSIENT,
        !IS_VOLATILE,
        IS_CHANGEABLE,
        !IS_UNSETTABLE,
        !IS_ID,
        IS_UNIQUE,
        !IS_DERIVED,
        IS_ORDERED);

    initEClass(
        projectEClass,
        Project.class,
        "Project",
        !IS_ABSTRACT,
        !IS_INTERFACE,
        IS_GENERATED_INSTANCE_CLASS);
    initEReference(
        getProject_Supervisor(),
        this.getAcademic(),
        this.getAcademic_Projects(),
        "supervisor",
        null,
        1,
        1,
        Project.class,
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
        getProject_Student(),
        this.getStudent(),
        this.getStudent_Project(),
        "student",
        null,
        0,
        1,
        Project.class,
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
        getProject_SecondMarker(),
        this.getAcademic(),
        this.getAcademic_SecondMarker(),
        "secondMarker",
        null,
        1,
        1,
        Project.class,
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
        getProject_ThirdMarker(),
        this.getAcademic(),
        this.getAcademic_ThirdMarker(),
        "thirdMarker",
        null,
        1,
        1,
        Project.class,
        !IS_TRANSIENT,
        !IS_VOLATILE,
        IS_CHANGEABLE,
        !IS_COMPOSITE,
        IS_RESOLVE_PROXIES,
        !IS_UNSETTABLE,
        IS_UNIQUE,
        !IS_DERIVED,
        IS_ORDERED);
    initEAttribute(
        getProject_Title(),
        theXMLTypePackage.getString(),
        "title",
        null,
        0,
        1,
        Project.class,
        !IS_TRANSIENT,
        !IS_VOLATILE,
        IS_CHANGEABLE,
        !IS_UNSETTABLE,
        !IS_ID,
        IS_UNIQUE,
        !IS_DERIVED,
        IS_ORDERED);
    initEAttribute(
        getProject_Stage(),
        theXMLTypePackage.getString(),
        "stage",
        null,
        0,
        1,
        Project.class,
        !IS_TRANSIENT,
        !IS_VOLATILE,
        IS_CHANGEABLE,
        !IS_UNSETTABLE,
        !IS_ID,
        IS_UNIQUE,
        !IS_DERIVED,
        IS_ORDERED);

    initEClass(
        studentEClass,
        Student.class,
        "Student",
        !IS_ABSTRACT,
        !IS_INTERFACE,
        IS_GENERATED_INSTANCE_CLASS);
    initEReference(
        getStudent_Project(),
        this.getProject(),
        this.getProject_Student(),
        "project",
        null,
        1,
        1,
        Student.class,
        !IS_TRANSIENT,
        !IS_VOLATILE,
        IS_CHANGEABLE,
        !IS_COMPOSITE,
        IS_RESOLVE_PROXIES,
        !IS_UNSETTABLE,
        IS_UNIQUE,
        !IS_DERIVED,
        IS_ORDERED);
    initEAttribute(
        getStudent_Email(),
        theXMLTypePackage.getString(),
        "email",
        null,
        0,
        1,
        Student.class,
        !IS_TRANSIENT,
        !IS_VOLATILE,
        IS_CHANGEABLE,
        !IS_UNSETTABLE,
        !IS_ID,
        IS_UNIQUE,
        !IS_DERIVED,
        IS_ORDERED);
    initEAttribute(
        getStudent_Name(),
        theXMLTypePackage.getString(),
        "name",
        null,
        0,
        1,
        Student.class,
        !IS_TRANSIENT,
        !IS_VOLATILE,
        IS_CHANGEABLE,
        !IS_UNSETTABLE,
        !IS_ID,
        IS_UNIQUE,
        !IS_DERIVED,
        IS_ORDERED);
    initEAttribute(
        getStudent_Number(),
        theXMLTypePackage.getString(),
        "number",
        null,
        0,
        1,
        Student.class,
        !IS_TRANSIENT,
        !IS_VOLATILE,
        IS_CHANGEABLE,
        !IS_UNSETTABLE,
        !IS_ID,
        IS_UNIQUE,
        !IS_DERIVED,
        IS_ORDERED);

    initEClass(
        markingTeamEClass,
        MarkingTeam.class,
        "MarkingTeam",
        !IS_ABSTRACT,
        !IS_INTERFACE,
        IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(
        getMarkingTeam_Number(),
        theXMLTypePackage.getInteger(),
        "number",
        null,
        0,
        1,
        MarkingTeam.class,
        !IS_TRANSIENT,
        !IS_VOLATILE,
        IS_CHANGEABLE,
        !IS_UNSETTABLE,
        !IS_ID,
        IS_UNIQUE,
        !IS_DERIVED,
        IS_ORDERED);
    initEReference(
        getMarkingTeam_Academics(),
        this.getAcademic(),
        this.getAcademic_MarkingTeam(),
        "academics",
        null,
        0,
        -1,
        MarkingTeam.class,
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
        markerAllocationModelEClass,
        MarkerAllocationModel.class,
        "MarkerAllocationModel",
        !IS_ABSTRACT,
        !IS_INTERFACE,
        IS_GENERATED_INSTANCE_CLASS);
    initEReference(
        getMarkerAllocationModel_Students(),
        this.getStudent(),
        null,
        "students",
        null,
        1,
        -1,
        MarkerAllocationModel.class,
        !IS_TRANSIENT,
        !IS_VOLATILE,
        IS_CHANGEABLE,
        IS_COMPOSITE,
        !IS_RESOLVE_PROXIES,
        !IS_UNSETTABLE,
        IS_UNIQUE,
        !IS_DERIVED,
        IS_ORDERED);
    initEReference(
        getMarkerAllocationModel_Projects(),
        this.getProject(),
        null,
        "projects",
        null,
        1,
        -1,
        MarkerAllocationModel.class,
        !IS_TRANSIENT,
        !IS_VOLATILE,
        IS_CHANGEABLE,
        IS_COMPOSITE,
        !IS_RESOLVE_PROXIES,
        !IS_UNSETTABLE,
        IS_UNIQUE,
        !IS_DERIVED,
        IS_ORDERED);
    initEReference(
        getMarkerAllocationModel_Academics(),
        this.getAcademic(),
        null,
        "academics",
        null,
        1,
        -1,
        MarkerAllocationModel.class,
        !IS_TRANSIENT,
        !IS_VOLATILE,
        IS_CHANGEABLE,
        IS_COMPOSITE,
        !IS_RESOLVE_PROXIES,
        !IS_UNSETTABLE,
        IS_UNIQUE,
        !IS_DERIVED,
        IS_ORDERED);
    initEReference(
        getMarkerAllocationModel_MarkingTeams(),
        this.getMarkingTeam(),
        null,
        "markingTeams",
        null,
        0,
        -1,
        MarkerAllocationModel.class,
        !IS_TRANSIENT,
        !IS_VOLATILE,
        IS_CHANGEABLE,
        IS_COMPOSITE,
        !IS_RESOLVE_PROXIES,
        !IS_UNSETTABLE,
        IS_UNIQUE,
        !IS_DERIVED,
        IS_ORDERED);

    // Create resource
    createResource(eNS_URI);
  }
} // StudentProjectMarkerAllocationPackageImpl
