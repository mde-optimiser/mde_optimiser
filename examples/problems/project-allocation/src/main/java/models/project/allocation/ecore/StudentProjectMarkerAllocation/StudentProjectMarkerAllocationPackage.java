/** */
package models.project.allocation.ecore.StudentProjectMarkerAllocation;

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
 * @see
 *     models.project.allocation.ecore.StudentProjectMarkerAllocation.StudentProjectMarkerAllocationFactory
 * @model kind="package"
 * @generated
 */
public interface StudentProjectMarkerAllocationPackage extends EPackage {
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  String eNAME = "StudentProjectMarkerAllocation";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  String eNS_URI = "http://www.example.org/StudentProjectMarkerAllocation";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  String eNS_PREFIX = "StudentProjectMarkerAllocation";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  StudentProjectMarkerAllocationPackage eINSTANCE =
      models.project.allocation.ecore.StudentProjectMarkerAllocation.impl
          .StudentProjectMarkerAllocationPackageImpl.init();

  /**
   * The meta object id for the '{@link
   * models.project.allocation.ecore.StudentProjectMarkerAllocation.impl.AcademicImpl
   * <em>Academic</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see models.project.allocation.ecore.StudentProjectMarkerAllocation.impl.AcademicImpl
   * @see
   *     models.project.allocation.ecore.StudentProjectMarkerAllocation.impl.StudentProjectMarkerAllocationPackageImpl#getAcademic()
   * @generated
   */
  int ACADEMIC = 0;

  /**
   * The feature id for the '<em><b>Projects</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int ACADEMIC__PROJECTS = 0;

  /**
   * The feature id for the '<em><b>Second Marker</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int ACADEMIC__SECOND_MARKER = 1;

  /**
   * The feature id for the '<em><b>Third Marker</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int ACADEMIC__THIRD_MARKER = 2;

  /**
   * The feature id for the '<em><b>Marking Team</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int ACADEMIC__MARKING_TEAM = 3;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int ACADEMIC__NAME = 4;

  /**
   * The feature id for the '<em><b>Number</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int ACADEMIC__NUMBER = 5;

  /**
   * The feature id for the '<em><b>Email</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int ACADEMIC__EMAIL = 6;

  /**
   * The number of structural features of the '<em>Academic</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int ACADEMIC_FEATURE_COUNT = 7;

  /**
   * The number of operations of the '<em>Academic</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int ACADEMIC_OPERATION_COUNT = 0;

  /**
   * The meta object id for the '{@link
   * models.project.allocation.ecore.StudentProjectMarkerAllocation.impl.ProjectImpl
   * <em>Project</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see models.project.allocation.ecore.StudentProjectMarkerAllocation.impl.ProjectImpl
   * @see
   *     models.project.allocation.ecore.StudentProjectMarkerAllocation.impl.StudentProjectMarkerAllocationPackageImpl#getProject()
   * @generated
   */
  int PROJECT = 1;

  /**
   * The feature id for the '<em><b>Supervisor</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int PROJECT__SUPERVISOR = 0;

  /**
   * The feature id for the '<em><b>Student</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int PROJECT__STUDENT = 1;

  /**
   * The feature id for the '<em><b>Second Marker</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int PROJECT__SECOND_MARKER = 2;

  /**
   * The feature id for the '<em><b>Third Marker</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int PROJECT__THIRD_MARKER = 3;

  /**
   * The feature id for the '<em><b>Title</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int PROJECT__TITLE = 4;

  /**
   * The feature id for the '<em><b>Stage</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int PROJECT__STAGE = 5;

  /**
   * The number of structural features of the '<em>Project</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int PROJECT_FEATURE_COUNT = 6;

  /**
   * The number of operations of the '<em>Project</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int PROJECT_OPERATION_COUNT = 0;

  /**
   * The meta object id for the '{@link
   * models.project.allocation.ecore.StudentProjectMarkerAllocation.impl.StudentImpl
   * <em>Student</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see models.project.allocation.ecore.StudentProjectMarkerAllocation.impl.StudentImpl
   * @see
   *     models.project.allocation.ecore.StudentProjectMarkerAllocation.impl.StudentProjectMarkerAllocationPackageImpl#getStudent()
   * @generated
   */
  int STUDENT = 2;

  /**
   * The feature id for the '<em><b>Project</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int STUDENT__PROJECT = 0;

  /**
   * The feature id for the '<em><b>Email</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int STUDENT__EMAIL = 1;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int STUDENT__NAME = 2;

  /**
   * The feature id for the '<em><b>Number</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int STUDENT__NUMBER = 3;

  /**
   * The number of structural features of the '<em>Student</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int STUDENT_FEATURE_COUNT = 4;

  /**
   * The number of operations of the '<em>Student</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int STUDENT_OPERATION_COUNT = 0;

  /**
   * The meta object id for the '{@link
   * models.project.allocation.ecore.StudentProjectMarkerAllocation.impl.MarkingTeamImpl <em>Marking
   * Team</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see models.project.allocation.ecore.StudentProjectMarkerAllocation.impl.MarkingTeamImpl
   * @see
   *     models.project.allocation.ecore.StudentProjectMarkerAllocation.impl.StudentProjectMarkerAllocationPackageImpl#getMarkingTeam()
   * @generated
   */
  int MARKING_TEAM = 3;

  /**
   * The feature id for the '<em><b>Number</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int MARKING_TEAM__NUMBER = 0;

  /**
   * The feature id for the '<em><b>Academics</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int MARKING_TEAM__ACADEMICS = 1;

  /**
   * The number of structural features of the '<em>Marking Team</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int MARKING_TEAM_FEATURE_COUNT = 2;

  /**
   * The number of operations of the '<em>Marking Team</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int MARKING_TEAM_OPERATION_COUNT = 0;

  /**
   * The meta object id for the '{@link
   * models.project.allocation.ecore.StudentProjectMarkerAllocation.impl.MarkerAllocationModelImpl
   * <em>Marker Allocation Model</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see
   *     models.project.allocation.ecore.StudentProjectMarkerAllocation.impl.MarkerAllocationModelImpl
   * @see
   *     models.project.allocation.ecore.StudentProjectMarkerAllocation.impl.StudentProjectMarkerAllocationPackageImpl#getMarkerAllocationModel()
   * @generated
   */
  int MARKER_ALLOCATION_MODEL = 4;

  /**
   * The feature id for the '<em><b>Students</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int MARKER_ALLOCATION_MODEL__STUDENTS = 0;

  /**
   * The feature id for the '<em><b>Projects</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int MARKER_ALLOCATION_MODEL__PROJECTS = 1;

  /**
   * The feature id for the '<em><b>Academics</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int MARKER_ALLOCATION_MODEL__ACADEMICS = 2;

  /**
   * The feature id for the '<em><b>Marking Teams</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int MARKER_ALLOCATION_MODEL__MARKING_TEAMS = 3;

  /**
   * The number of structural features of the '<em>Marker Allocation Model</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int MARKER_ALLOCATION_MODEL_FEATURE_COUNT = 4;

  /**
   * The number of operations of the '<em>Marker Allocation Model</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int MARKER_ALLOCATION_MODEL_OPERATION_COUNT = 0;

  /**
   * Returns the meta object for class '{@link
   * models.project.allocation.ecore.StudentProjectMarkerAllocation.Academic <em>Academic</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the meta object for class '<em>Academic</em>'.
   * @see models.project.allocation.ecore.StudentProjectMarkerAllocation.Academic
   * @generated
   */
  EClass getAcademic();

  /**
   * Returns the meta object for the reference list '{@link
   * models.project.allocation.ecore.StudentProjectMarkerAllocation.Academic#getProjects
   * <em>Projects</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the meta object for the reference list '<em>Projects</em>'.
   * @see models.project.allocation.ecore.StudentProjectMarkerAllocation.Academic#getProjects()
   * @see #getAcademic()
   * @generated
   */
  EReference getAcademic_Projects();

  /**
   * Returns the meta object for the reference list '{@link
   * models.project.allocation.ecore.StudentProjectMarkerAllocation.Academic#getSecondMarker
   * <em>Second Marker</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the meta object for the reference list '<em>Second Marker</em>'.
   * @see models.project.allocation.ecore.StudentProjectMarkerAllocation.Academic#getSecondMarker()
   * @see #getAcademic()
   * @generated
   */
  EReference getAcademic_SecondMarker();

  /**
   * Returns the meta object for the reference list '{@link
   * models.project.allocation.ecore.StudentProjectMarkerAllocation.Academic#getThirdMarker
   * <em>Third Marker</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the meta object for the reference list '<em>Third Marker</em>'.
   * @see models.project.allocation.ecore.StudentProjectMarkerAllocation.Academic#getThirdMarker()
   * @see #getAcademic()
   * @generated
   */
  EReference getAcademic_ThirdMarker();

  /**
   * Returns the meta object for the reference list '{@link
   * models.project.allocation.ecore.StudentProjectMarkerAllocation.Academic#getMarkingTeam
   * <em>Marking Team</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the meta object for the reference list '<em>Marking Team</em>'.
   * @see models.project.allocation.ecore.StudentProjectMarkerAllocation.Academic#getMarkingTeam()
   * @see #getAcademic()
   * @generated
   */
  EReference getAcademic_MarkingTeam();

  /**
   * Returns the meta object for the attribute '{@link
   * models.project.allocation.ecore.StudentProjectMarkerAllocation.Academic#getName
   * <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see models.project.allocation.ecore.StudentProjectMarkerAllocation.Academic#getName()
   * @see #getAcademic()
   * @generated
   */
  EAttribute getAcademic_Name();

  /**
   * Returns the meta object for the attribute '{@link
   * models.project.allocation.ecore.StudentProjectMarkerAllocation.Academic#getNumber
   * <em>Number</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the meta object for the attribute '<em>Number</em>'.
   * @see models.project.allocation.ecore.StudentProjectMarkerAllocation.Academic#getNumber()
   * @see #getAcademic()
   * @generated
   */
  EAttribute getAcademic_Number();

  /**
   * Returns the meta object for the attribute '{@link
   * models.project.allocation.ecore.StudentProjectMarkerAllocation.Academic#getEmail
   * <em>Email</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the meta object for the attribute '<em>Email</em>'.
   * @see models.project.allocation.ecore.StudentProjectMarkerAllocation.Academic#getEmail()
   * @see #getAcademic()
   * @generated
   */
  EAttribute getAcademic_Email();

  /**
   * Returns the meta object for class '{@link
   * models.project.allocation.ecore.StudentProjectMarkerAllocation.Project <em>Project</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the meta object for class '<em>Project</em>'.
   * @see models.project.allocation.ecore.StudentProjectMarkerAllocation.Project
   * @generated
   */
  EClass getProject();

  /**
   * Returns the meta object for the reference '{@link
   * models.project.allocation.ecore.StudentProjectMarkerAllocation.Project#getSupervisor
   * <em>Supervisor</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the meta object for the reference '<em>Supervisor</em>'.
   * @see models.project.allocation.ecore.StudentProjectMarkerAllocation.Project#getSupervisor()
   * @see #getProject()
   * @generated
   */
  EReference getProject_Supervisor();

  /**
   * Returns the meta object for the reference '{@link
   * models.project.allocation.ecore.StudentProjectMarkerAllocation.Project#getStudent
   * <em>Student</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the meta object for the reference '<em>Student</em>'.
   * @see models.project.allocation.ecore.StudentProjectMarkerAllocation.Project#getStudent()
   * @see #getProject()
   * @generated
   */
  EReference getProject_Student();

  /**
   * Returns the meta object for the reference '{@link
   * models.project.allocation.ecore.StudentProjectMarkerAllocation.Project#getSecondMarker
   * <em>Second Marker</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the meta object for the reference '<em>Second Marker</em>'.
   * @see models.project.allocation.ecore.StudentProjectMarkerAllocation.Project#getSecondMarker()
   * @see #getProject()
   * @generated
   */
  EReference getProject_SecondMarker();

  /**
   * Returns the meta object for the reference '{@link
   * models.project.allocation.ecore.StudentProjectMarkerAllocation.Project#getThirdMarker <em>Third
   * Marker</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the meta object for the reference '<em>Third Marker</em>'.
   * @see models.project.allocation.ecore.StudentProjectMarkerAllocation.Project#getThirdMarker()
   * @see #getProject()
   * @generated
   */
  EReference getProject_ThirdMarker();

  /**
   * Returns the meta object for the attribute '{@link
   * models.project.allocation.ecore.StudentProjectMarkerAllocation.Project#getTitle
   * <em>Title</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the meta object for the attribute '<em>Title</em>'.
   * @see models.project.allocation.ecore.StudentProjectMarkerAllocation.Project#getTitle()
   * @see #getProject()
   * @generated
   */
  EAttribute getProject_Title();

  /**
   * Returns the meta object for the attribute '{@link
   * models.project.allocation.ecore.StudentProjectMarkerAllocation.Project#getStage
   * <em>Stage</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the meta object for the attribute '<em>Stage</em>'.
   * @see models.project.allocation.ecore.StudentProjectMarkerAllocation.Project#getStage()
   * @see #getProject()
   * @generated
   */
  EAttribute getProject_Stage();

  /**
   * Returns the meta object for class '{@link
   * models.project.allocation.ecore.StudentProjectMarkerAllocation.Student <em>Student</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the meta object for class '<em>Student</em>'.
   * @see models.project.allocation.ecore.StudentProjectMarkerAllocation.Student
   * @generated
   */
  EClass getStudent();

  /**
   * Returns the meta object for the reference '{@link
   * models.project.allocation.ecore.StudentProjectMarkerAllocation.Student#getProject
   * <em>Project</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the meta object for the reference '<em>Project</em>'.
   * @see models.project.allocation.ecore.StudentProjectMarkerAllocation.Student#getProject()
   * @see #getStudent()
   * @generated
   */
  EReference getStudent_Project();

  /**
   * Returns the meta object for the attribute '{@link
   * models.project.allocation.ecore.StudentProjectMarkerAllocation.Student#getEmail
   * <em>Email</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the meta object for the attribute '<em>Email</em>'.
   * @see models.project.allocation.ecore.StudentProjectMarkerAllocation.Student#getEmail()
   * @see #getStudent()
   * @generated
   */
  EAttribute getStudent_Email();

  /**
   * Returns the meta object for the attribute '{@link
   * models.project.allocation.ecore.StudentProjectMarkerAllocation.Student#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see models.project.allocation.ecore.StudentProjectMarkerAllocation.Student#getName()
   * @see #getStudent()
   * @generated
   */
  EAttribute getStudent_Name();

  /**
   * Returns the meta object for the attribute '{@link
   * models.project.allocation.ecore.StudentProjectMarkerAllocation.Student#getNumber
   * <em>Number</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the meta object for the attribute '<em>Number</em>'.
   * @see models.project.allocation.ecore.StudentProjectMarkerAllocation.Student#getNumber()
   * @see #getStudent()
   * @generated
   */
  EAttribute getStudent_Number();

  /**
   * Returns the meta object for class '{@link
   * models.project.allocation.ecore.StudentProjectMarkerAllocation.MarkingTeam <em>Marking
   * Team</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the meta object for class '<em>Marking Team</em>'.
   * @see models.project.allocation.ecore.StudentProjectMarkerAllocation.MarkingTeam
   * @generated
   */
  EClass getMarkingTeam();

  /**
   * Returns the meta object for the attribute '{@link
   * models.project.allocation.ecore.StudentProjectMarkerAllocation.MarkingTeam#getNumber
   * <em>Number</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the meta object for the attribute '<em>Number</em>'.
   * @see models.project.allocation.ecore.StudentProjectMarkerAllocation.MarkingTeam#getNumber()
   * @see #getMarkingTeam()
   * @generated
   */
  EAttribute getMarkingTeam_Number();

  /**
   * Returns the meta object for the reference list '{@link
   * models.project.allocation.ecore.StudentProjectMarkerAllocation.MarkingTeam#getAcademics
   * <em>Academics</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the meta object for the reference list '<em>Academics</em>'.
   * @see models.project.allocation.ecore.StudentProjectMarkerAllocation.MarkingTeam#getAcademics()
   * @see #getMarkingTeam()
   * @generated
   */
  EReference getMarkingTeam_Academics();

  /**
   * Returns the meta object for class '{@link
   * models.project.allocation.ecore.StudentProjectMarkerAllocation.MarkerAllocationModel <em>Marker
   * Allocation Model</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the meta object for class '<em>Marker Allocation Model</em>'.
   * @see models.project.allocation.ecore.StudentProjectMarkerAllocation.MarkerAllocationModel
   * @generated
   */
  EClass getMarkerAllocationModel();

  /**
   * Returns the meta object for the containment reference list '{@link
   * models.project.allocation.ecore.StudentProjectMarkerAllocation.MarkerAllocationModel#getStudents
   * <em>Students</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the meta object for the containment reference list '<em>Students</em>'.
   * @see
   *     models.project.allocation.ecore.StudentProjectMarkerAllocation.MarkerAllocationModel#getStudents()
   * @see #getMarkerAllocationModel()
   * @generated
   */
  EReference getMarkerAllocationModel_Students();

  /**
   * Returns the meta object for the containment reference list '{@link
   * models.project.allocation.ecore.StudentProjectMarkerAllocation.MarkerAllocationModel#getProjects
   * <em>Projects</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the meta object for the containment reference list '<em>Projects</em>'.
   * @see
   *     models.project.allocation.ecore.StudentProjectMarkerAllocation.MarkerAllocationModel#getProjects()
   * @see #getMarkerAllocationModel()
   * @generated
   */
  EReference getMarkerAllocationModel_Projects();

  /**
   * Returns the meta object for the containment reference list '{@link
   * models.project.allocation.ecore.StudentProjectMarkerAllocation.MarkerAllocationModel#getAcademics
   * <em>Academics</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the meta object for the containment reference list '<em>Academics</em>'.
   * @see
   *     models.project.allocation.ecore.StudentProjectMarkerAllocation.MarkerAllocationModel#getAcademics()
   * @see #getMarkerAllocationModel()
   * @generated
   */
  EReference getMarkerAllocationModel_Academics();

  /**
   * Returns the meta object for the containment reference list '{@link
   * models.project.allocation.ecore.StudentProjectMarkerAllocation.MarkerAllocationModel#getMarkingTeams
   * <em>Marking Teams</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the meta object for the containment reference list '<em>Marking Teams</em>'.
   * @see
   *     models.project.allocation.ecore.StudentProjectMarkerAllocation.MarkerAllocationModel#getMarkingTeams()
   * @see #getMarkerAllocationModel()
   * @generated
   */
  EReference getMarkerAllocationModel_MarkingTeams();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the factory that creates the instances of the model.
   * @generated
   */
  StudentProjectMarkerAllocationFactory getStudentProjectMarkerAllocationFactory();

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
     * The meta object literal for the '{@link
     * models.project.allocation.ecore.StudentProjectMarkerAllocation.impl.AcademicImpl
     * <em>Academic</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see models.project.allocation.ecore.StudentProjectMarkerAllocation.impl.AcademicImpl
     * @see
     *     models.project.allocation.ecore.StudentProjectMarkerAllocation.impl.StudentProjectMarkerAllocationPackageImpl#getAcademic()
     * @generated
     */
    EClass ACADEMIC = eINSTANCE.getAcademic();

    /**
     * The meta object literal for the '<em><b>Projects</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    EReference ACADEMIC__PROJECTS = eINSTANCE.getAcademic_Projects();

    /**
     * The meta object literal for the '<em><b>Second Marker</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    EReference ACADEMIC__SECOND_MARKER = eINSTANCE.getAcademic_SecondMarker();

    /**
     * The meta object literal for the '<em><b>Third Marker</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    EReference ACADEMIC__THIRD_MARKER = eINSTANCE.getAcademic_ThirdMarker();

    /**
     * The meta object literal for the '<em><b>Marking Team</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    EReference ACADEMIC__MARKING_TEAM = eINSTANCE.getAcademic_MarkingTeam();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    EAttribute ACADEMIC__NAME = eINSTANCE.getAcademic_Name();

    /**
     * The meta object literal for the '<em><b>Number</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    EAttribute ACADEMIC__NUMBER = eINSTANCE.getAcademic_Number();

    /**
     * The meta object literal for the '<em><b>Email</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    EAttribute ACADEMIC__EMAIL = eINSTANCE.getAcademic_Email();

    /**
     * The meta object literal for the '{@link
     * models.project.allocation.ecore.StudentProjectMarkerAllocation.impl.ProjectImpl
     * <em>Project</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see models.project.allocation.ecore.StudentProjectMarkerAllocation.impl.ProjectImpl
     * @see
     *     models.project.allocation.ecore.StudentProjectMarkerAllocation.impl.StudentProjectMarkerAllocationPackageImpl#getProject()
     * @generated
     */
    EClass PROJECT = eINSTANCE.getProject();

    /**
     * The meta object literal for the '<em><b>Supervisor</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    EReference PROJECT__SUPERVISOR = eINSTANCE.getProject_Supervisor();

    /**
     * The meta object literal for the '<em><b>Student</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    EReference PROJECT__STUDENT = eINSTANCE.getProject_Student();

    /**
     * The meta object literal for the '<em><b>Second Marker</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    EReference PROJECT__SECOND_MARKER = eINSTANCE.getProject_SecondMarker();

    /**
     * The meta object literal for the '<em><b>Third Marker</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    EReference PROJECT__THIRD_MARKER = eINSTANCE.getProject_ThirdMarker();

    /**
     * The meta object literal for the '<em><b>Title</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    EAttribute PROJECT__TITLE = eINSTANCE.getProject_Title();

    /**
     * The meta object literal for the '<em><b>Stage</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    EAttribute PROJECT__STAGE = eINSTANCE.getProject_Stage();

    /**
     * The meta object literal for the '{@link
     * models.project.allocation.ecore.StudentProjectMarkerAllocation.impl.StudentImpl
     * <em>Student</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see models.project.allocation.ecore.StudentProjectMarkerAllocation.impl.StudentImpl
     * @see
     *     models.project.allocation.ecore.StudentProjectMarkerAllocation.impl.StudentProjectMarkerAllocationPackageImpl#getStudent()
     * @generated
     */
    EClass STUDENT = eINSTANCE.getStudent();

    /**
     * The meta object literal for the '<em><b>Project</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    EReference STUDENT__PROJECT = eINSTANCE.getStudent_Project();

    /**
     * The meta object literal for the '<em><b>Email</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    EAttribute STUDENT__EMAIL = eINSTANCE.getStudent_Email();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    EAttribute STUDENT__NAME = eINSTANCE.getStudent_Name();

    /**
     * The meta object literal for the '<em><b>Number</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    EAttribute STUDENT__NUMBER = eINSTANCE.getStudent_Number();

    /**
     * The meta object literal for the '{@link
     * models.project.allocation.ecore.StudentProjectMarkerAllocation.impl.MarkingTeamImpl
     * <em>Marking Team</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see models.project.allocation.ecore.StudentProjectMarkerAllocation.impl.MarkingTeamImpl
     * @see
     *     models.project.allocation.ecore.StudentProjectMarkerAllocation.impl.StudentProjectMarkerAllocationPackageImpl#getMarkingTeam()
     * @generated
     */
    EClass MARKING_TEAM = eINSTANCE.getMarkingTeam();

    /**
     * The meta object literal for the '<em><b>Number</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    EAttribute MARKING_TEAM__NUMBER = eINSTANCE.getMarkingTeam_Number();

    /**
     * The meta object literal for the '<em><b>Academics</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    EReference MARKING_TEAM__ACADEMICS = eINSTANCE.getMarkingTeam_Academics();

    /**
     * The meta object literal for the '{@link
     * models.project.allocation.ecore.StudentProjectMarkerAllocation.impl.MarkerAllocationModelImpl
     * <em>Marker Allocation Model</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see
     *     models.project.allocation.ecore.StudentProjectMarkerAllocation.impl.MarkerAllocationModelImpl
     * @see
     *     models.project.allocation.ecore.StudentProjectMarkerAllocation.impl.StudentProjectMarkerAllocationPackageImpl#getMarkerAllocationModel()
     * @generated
     */
    EClass MARKER_ALLOCATION_MODEL = eINSTANCE.getMarkerAllocationModel();

    /**
     * The meta object literal for the '<em><b>Students</b></em>' containment reference list
     * feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    EReference MARKER_ALLOCATION_MODEL__STUDENTS = eINSTANCE.getMarkerAllocationModel_Students();

    /**
     * The meta object literal for the '<em><b>Projects</b></em>' containment reference list
     * feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    EReference MARKER_ALLOCATION_MODEL__PROJECTS = eINSTANCE.getMarkerAllocationModel_Projects();

    /**
     * The meta object literal for the '<em><b>Academics</b></em>' containment reference list
     * feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    EReference MARKER_ALLOCATION_MODEL__ACADEMICS = eINSTANCE.getMarkerAllocationModel_Academics();

    /**
     * The meta object literal for the '<em><b>Marking Teams</b></em>' containment reference list
     * feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    EReference MARKER_ALLOCATION_MODEL__MARKING_TEAMS =
        eINSTANCE.getMarkerAllocationModel_MarkingTeams();
  }
} // StudentProjectMarkerAllocationPackage
