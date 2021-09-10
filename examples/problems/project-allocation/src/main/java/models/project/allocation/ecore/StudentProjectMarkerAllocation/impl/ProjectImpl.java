/** */
package models.project.allocation.ecore.StudentProjectMarkerAllocation.impl;

import models.project.allocation.ecore.StudentProjectMarkerAllocation.Academic;
import models.project.allocation.ecore.StudentProjectMarkerAllocation.Project;
import models.project.allocation.ecore.StudentProjectMarkerAllocation.Student;
import models.project.allocation.ecore.StudentProjectMarkerAllocation.StudentProjectMarkerAllocationPackage;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 *
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Project</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>The following features are implemented:
 *
 * <ul>
 *   <li>{@link
 *       models.project.allocation.ecore.StudentProjectMarkerAllocation.impl.ProjectImpl#getSupervisor
 *       <em>Supervisor</em>}
 *   <li>{@link
 *       models.project.allocation.ecore.StudentProjectMarkerAllocation.impl.ProjectImpl#getStudent
 *       <em>Student</em>}
 *   <li>{@link
 *       models.project.allocation.ecore.StudentProjectMarkerAllocation.impl.ProjectImpl#getSecondMarker
 *       <em>Second Marker</em>}
 *   <li>{@link
 *       models.project.allocation.ecore.StudentProjectMarkerAllocation.impl.ProjectImpl#getThirdMarker
 *       <em>Third Marker</em>}
 *   <li>{@link
 *       models.project.allocation.ecore.StudentProjectMarkerAllocation.impl.ProjectImpl#getTitle
 *       <em>Title</em>}
 *   <li>{@link
 *       models.project.allocation.ecore.StudentProjectMarkerAllocation.impl.ProjectImpl#getStage
 *       <em>Stage</em>}
 * </ul>
 *
 * @generated
 */
public class ProjectImpl extends MinimalEObjectImpl.Container implements Project {
  /**
   * The cached value of the '{@link #getSupervisor() <em>Supervisor</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getSupervisor()
   * @generated
   * @ordered
   */
  protected Academic supervisor;

  /**
   * The cached value of the '{@link #getStudent() <em>Student</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getStudent()
   * @generated
   * @ordered
   */
  protected Student student;

  /**
   * The cached value of the '{@link #getSecondMarker() <em>Second Marker</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getSecondMarker()
   * @generated
   * @ordered
   */
  protected Academic secondMarker;

  /**
   * The cached value of the '{@link #getThirdMarker() <em>Third Marker</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getThirdMarker()
   * @generated
   * @ordered
   */
  protected Academic thirdMarker;

  /**
   * The default value of the '{@link #getTitle() <em>Title</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getTitle()
   * @generated
   * @ordered
   */
  protected static final String TITLE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getTitle() <em>Title</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getTitle()
   * @generated
   * @ordered
   */
  protected String title = TITLE_EDEFAULT;

  /**
   * The default value of the '{@link #getStage() <em>Stage</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getStage()
   * @generated
   * @ordered
   */
  protected static final String STAGE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getStage() <em>Stage</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getStage()
   * @generated
   * @ordered
   */
  protected String stage = STAGE_EDEFAULT;

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  protected ProjectImpl() {
    super();
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return StudentProjectMarkerAllocationPackage.Literals.PROJECT;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public Academic getSupervisor() {
    if (supervisor != null && supervisor.eIsProxy()) {
      InternalEObject oldSupervisor = (InternalEObject) supervisor;
      supervisor = (Academic) eResolveProxy(oldSupervisor);
      if (supervisor != oldSupervisor) {
        if (eNotificationRequired())
          eNotify(
              new ENotificationImpl(
                  this,
                  Notification.RESOLVE,
                  StudentProjectMarkerAllocationPackage.PROJECT__SUPERVISOR,
                  oldSupervisor,
                  supervisor));
      }
    }
    return supervisor;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public Academic basicGetSupervisor() {
    return supervisor;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public NotificationChain basicSetSupervisor(Academic newSupervisor, NotificationChain msgs) {
    Academic oldSupervisor = supervisor;
    supervisor = newSupervisor;
    if (eNotificationRequired()) {
      ENotificationImpl notification =
          new ENotificationImpl(
              this,
              Notification.SET,
              StudentProjectMarkerAllocationPackage.PROJECT__SUPERVISOR,
              oldSupervisor,
              newSupervisor);
      if (msgs == null) msgs = notification;
      else msgs.add(notification);
    }
    return msgs;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public void setSupervisor(Academic newSupervisor) {
    if (newSupervisor != supervisor) {
      NotificationChain msgs = null;
      if (supervisor != null)
        msgs =
            ((InternalEObject) supervisor)
                .eInverseRemove(
                    this,
                    StudentProjectMarkerAllocationPackage.ACADEMIC__PROJECTS,
                    Academic.class,
                    msgs);
      if (newSupervisor != null)
        msgs =
            ((InternalEObject) newSupervisor)
                .eInverseAdd(
                    this,
                    StudentProjectMarkerAllocationPackage.ACADEMIC__PROJECTS,
                    Academic.class,
                    msgs);
      msgs = basicSetSupervisor(newSupervisor, msgs);
      if (msgs != null) msgs.dispatch();
    } else if (eNotificationRequired())
      eNotify(
          new ENotificationImpl(
              this,
              Notification.SET,
              StudentProjectMarkerAllocationPackage.PROJECT__SUPERVISOR,
              newSupervisor,
              newSupervisor));
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public Student getStudent() {
    if (student != null && student.eIsProxy()) {
      InternalEObject oldStudent = (InternalEObject) student;
      student = (Student) eResolveProxy(oldStudent);
      if (student != oldStudent) {
        if (eNotificationRequired())
          eNotify(
              new ENotificationImpl(
                  this,
                  Notification.RESOLVE,
                  StudentProjectMarkerAllocationPackage.PROJECT__STUDENT,
                  oldStudent,
                  student));
      }
    }
    return student;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public Student basicGetStudent() {
    return student;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public NotificationChain basicSetStudent(Student newStudent, NotificationChain msgs) {
    Student oldStudent = student;
    student = newStudent;
    if (eNotificationRequired()) {
      ENotificationImpl notification =
          new ENotificationImpl(
              this,
              Notification.SET,
              StudentProjectMarkerAllocationPackage.PROJECT__STUDENT,
              oldStudent,
              newStudent);
      if (msgs == null) msgs = notification;
      else msgs.add(notification);
    }
    return msgs;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public void setStudent(Student newStudent) {
    if (newStudent != student) {
      NotificationChain msgs = null;
      if (student != null)
        msgs =
            ((InternalEObject) student)
                .eInverseRemove(
                    this,
                    StudentProjectMarkerAllocationPackage.STUDENT__PROJECT,
                    Student.class,
                    msgs);
      if (newStudent != null)
        msgs =
            ((InternalEObject) newStudent)
                .eInverseAdd(
                    this,
                    StudentProjectMarkerAllocationPackage.STUDENT__PROJECT,
                    Student.class,
                    msgs);
      msgs = basicSetStudent(newStudent, msgs);
      if (msgs != null) msgs.dispatch();
    } else if (eNotificationRequired())
      eNotify(
          new ENotificationImpl(
              this,
              Notification.SET,
              StudentProjectMarkerAllocationPackage.PROJECT__STUDENT,
              newStudent,
              newStudent));
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public Academic getSecondMarker() {
    if (secondMarker != null && secondMarker.eIsProxy()) {
      InternalEObject oldSecondMarker = (InternalEObject) secondMarker;
      secondMarker = (Academic) eResolveProxy(oldSecondMarker);
      if (secondMarker != oldSecondMarker) {
        if (eNotificationRequired())
          eNotify(
              new ENotificationImpl(
                  this,
                  Notification.RESOLVE,
                  StudentProjectMarkerAllocationPackage.PROJECT__SECOND_MARKER,
                  oldSecondMarker,
                  secondMarker));
      }
    }
    return secondMarker;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public Academic basicGetSecondMarker() {
    return secondMarker;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public NotificationChain basicSetSecondMarker(Academic newSecondMarker, NotificationChain msgs) {
    Academic oldSecondMarker = secondMarker;
    secondMarker = newSecondMarker;
    if (eNotificationRequired()) {
      ENotificationImpl notification =
          new ENotificationImpl(
              this,
              Notification.SET,
              StudentProjectMarkerAllocationPackage.PROJECT__SECOND_MARKER,
              oldSecondMarker,
              newSecondMarker);
      if (msgs == null) msgs = notification;
      else msgs.add(notification);
    }
    return msgs;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public void setSecondMarker(Academic newSecondMarker) {
    if (newSecondMarker != secondMarker) {
      NotificationChain msgs = null;
      if (secondMarker != null)
        msgs =
            ((InternalEObject) secondMarker)
                .eInverseRemove(
                    this,
                    StudentProjectMarkerAllocationPackage.ACADEMIC__SECOND_MARKER,
                    Academic.class,
                    msgs);
      if (newSecondMarker != null)
        msgs =
            ((InternalEObject) newSecondMarker)
                .eInverseAdd(
                    this,
                    StudentProjectMarkerAllocationPackage.ACADEMIC__SECOND_MARKER,
                    Academic.class,
                    msgs);
      msgs = basicSetSecondMarker(newSecondMarker, msgs);
      if (msgs != null) msgs.dispatch();
    } else if (eNotificationRequired())
      eNotify(
          new ENotificationImpl(
              this,
              Notification.SET,
              StudentProjectMarkerAllocationPackage.PROJECT__SECOND_MARKER,
              newSecondMarker,
              newSecondMarker));
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public Academic getThirdMarker() {
    if (thirdMarker != null && thirdMarker.eIsProxy()) {
      InternalEObject oldThirdMarker = (InternalEObject) thirdMarker;
      thirdMarker = (Academic) eResolveProxy(oldThirdMarker);
      if (thirdMarker != oldThirdMarker) {
        if (eNotificationRequired())
          eNotify(
              new ENotificationImpl(
                  this,
                  Notification.RESOLVE,
                  StudentProjectMarkerAllocationPackage.PROJECT__THIRD_MARKER,
                  oldThirdMarker,
                  thirdMarker));
      }
    }
    return thirdMarker;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public Academic basicGetThirdMarker() {
    return thirdMarker;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public NotificationChain basicSetThirdMarker(Academic newThirdMarker, NotificationChain msgs) {
    Academic oldThirdMarker = thirdMarker;
    thirdMarker = newThirdMarker;
    if (eNotificationRequired()) {
      ENotificationImpl notification =
          new ENotificationImpl(
              this,
              Notification.SET,
              StudentProjectMarkerAllocationPackage.PROJECT__THIRD_MARKER,
              oldThirdMarker,
              newThirdMarker);
      if (msgs == null) msgs = notification;
      else msgs.add(notification);
    }
    return msgs;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public void setThirdMarker(Academic newThirdMarker) {
    if (newThirdMarker != thirdMarker) {
      NotificationChain msgs = null;
      if (thirdMarker != null)
        msgs =
            ((InternalEObject) thirdMarker)
                .eInverseRemove(
                    this,
                    StudentProjectMarkerAllocationPackage.ACADEMIC__THIRD_MARKER,
                    Academic.class,
                    msgs);
      if (newThirdMarker != null)
        msgs =
            ((InternalEObject) newThirdMarker)
                .eInverseAdd(
                    this,
                    StudentProjectMarkerAllocationPackage.ACADEMIC__THIRD_MARKER,
                    Academic.class,
                    msgs);
      msgs = basicSetThirdMarker(newThirdMarker, msgs);
      if (msgs != null) msgs.dispatch();
    } else if (eNotificationRequired())
      eNotify(
          new ENotificationImpl(
              this,
              Notification.SET,
              StudentProjectMarkerAllocationPackage.PROJECT__THIRD_MARKER,
              newThirdMarker,
              newThirdMarker));
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public String getTitle() {
    return title;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public void setTitle(String newTitle) {
    String oldTitle = title;
    title = newTitle;
    if (eNotificationRequired())
      eNotify(
          new ENotificationImpl(
              this,
              Notification.SET,
              StudentProjectMarkerAllocationPackage.PROJECT__TITLE,
              oldTitle,
              title));
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public String getStage() {
    return stage;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public void setStage(String newStage) {
    String oldStage = stage;
    stage = newStage;
    if (eNotificationRequired())
      eNotify(
          new ENotificationImpl(
              this,
              Notification.SET,
              StudentProjectMarkerAllocationPackage.PROJECT__STAGE,
              oldStage,
              stage));
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public NotificationChain eInverseAdd(
      InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID) {
      case StudentProjectMarkerAllocationPackage.PROJECT__SUPERVISOR:
        if (supervisor != null)
          msgs =
              ((InternalEObject) supervisor)
                  .eInverseRemove(
                      this,
                      StudentProjectMarkerAllocationPackage.ACADEMIC__PROJECTS,
                      Academic.class,
                      msgs);
        return basicSetSupervisor((Academic) otherEnd, msgs);
      case StudentProjectMarkerAllocationPackage.PROJECT__STUDENT:
        if (student != null)
          msgs =
              ((InternalEObject) student)
                  .eInverseRemove(
                      this,
                      StudentProjectMarkerAllocationPackage.STUDENT__PROJECT,
                      Student.class,
                      msgs);
        return basicSetStudent((Student) otherEnd, msgs);
      case StudentProjectMarkerAllocationPackage.PROJECT__SECOND_MARKER:
        if (secondMarker != null)
          msgs =
              ((InternalEObject) secondMarker)
                  .eInverseRemove(
                      this,
                      StudentProjectMarkerAllocationPackage.ACADEMIC__SECOND_MARKER,
                      Academic.class,
                      msgs);
        return basicSetSecondMarker((Academic) otherEnd, msgs);
      case StudentProjectMarkerAllocationPackage.PROJECT__THIRD_MARKER:
        if (thirdMarker != null)
          msgs =
              ((InternalEObject) thirdMarker)
                  .eInverseRemove(
                      this,
                      StudentProjectMarkerAllocationPackage.ACADEMIC__THIRD_MARKER,
                      Academic.class,
                      msgs);
        return basicSetThirdMarker((Academic) otherEnd, msgs);
    }
    return super.eInverseAdd(otherEnd, featureID, msgs);
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(
      InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID) {
      case StudentProjectMarkerAllocationPackage.PROJECT__SUPERVISOR:
        return basicSetSupervisor(null, msgs);
      case StudentProjectMarkerAllocationPackage.PROJECT__STUDENT:
        return basicSetStudent(null, msgs);
      case StudentProjectMarkerAllocationPackage.PROJECT__SECOND_MARKER:
        return basicSetSecondMarker(null, msgs);
      case StudentProjectMarkerAllocationPackage.PROJECT__THIRD_MARKER:
        return basicSetThirdMarker(null, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
      case StudentProjectMarkerAllocationPackage.PROJECT__SUPERVISOR:
        if (resolve) return getSupervisor();
        return basicGetSupervisor();
      case StudentProjectMarkerAllocationPackage.PROJECT__STUDENT:
        if (resolve) return getStudent();
        return basicGetStudent();
      case StudentProjectMarkerAllocationPackage.PROJECT__SECOND_MARKER:
        if (resolve) return getSecondMarker();
        return basicGetSecondMarker();
      case StudentProjectMarkerAllocationPackage.PROJECT__THIRD_MARKER:
        if (resolve) return getThirdMarker();
        return basicGetThirdMarker();
      case StudentProjectMarkerAllocationPackage.PROJECT__TITLE:
        return getTitle();
      case StudentProjectMarkerAllocationPackage.PROJECT__STAGE:
        return getStage();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue) {
    switch (featureID) {
      case StudentProjectMarkerAllocationPackage.PROJECT__SUPERVISOR:
        setSupervisor((Academic) newValue);
        return;
      case StudentProjectMarkerAllocationPackage.PROJECT__STUDENT:
        setStudent((Student) newValue);
        return;
      case StudentProjectMarkerAllocationPackage.PROJECT__SECOND_MARKER:
        setSecondMarker((Academic) newValue);
        return;
      case StudentProjectMarkerAllocationPackage.PROJECT__THIRD_MARKER:
        setThirdMarker((Academic) newValue);
        return;
      case StudentProjectMarkerAllocationPackage.PROJECT__TITLE:
        setTitle((String) newValue);
        return;
      case StudentProjectMarkerAllocationPackage.PROJECT__STAGE:
        setStage((String) newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public void eUnset(int featureID) {
    switch (featureID) {
      case StudentProjectMarkerAllocationPackage.PROJECT__SUPERVISOR:
        setSupervisor((Academic) null);
        return;
      case StudentProjectMarkerAllocationPackage.PROJECT__STUDENT:
        setStudent((Student) null);
        return;
      case StudentProjectMarkerAllocationPackage.PROJECT__SECOND_MARKER:
        setSecondMarker((Academic) null);
        return;
      case StudentProjectMarkerAllocationPackage.PROJECT__THIRD_MARKER:
        setThirdMarker((Academic) null);
        return;
      case StudentProjectMarkerAllocationPackage.PROJECT__TITLE:
        setTitle(TITLE_EDEFAULT);
        return;
      case StudentProjectMarkerAllocationPackage.PROJECT__STAGE:
        setStage(STAGE_EDEFAULT);
        return;
    }
    super.eUnset(featureID);
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID) {
    switch (featureID) {
      case StudentProjectMarkerAllocationPackage.PROJECT__SUPERVISOR:
        return supervisor != null;
      case StudentProjectMarkerAllocationPackage.PROJECT__STUDENT:
        return student != null;
      case StudentProjectMarkerAllocationPackage.PROJECT__SECOND_MARKER:
        return secondMarker != null;
      case StudentProjectMarkerAllocationPackage.PROJECT__THIRD_MARKER:
        return thirdMarker != null;
      case StudentProjectMarkerAllocationPackage.PROJECT__TITLE:
        return TITLE_EDEFAULT == null ? title != null : !TITLE_EDEFAULT.equals(title);
      case StudentProjectMarkerAllocationPackage.PROJECT__STAGE:
        return STAGE_EDEFAULT == null ? stage != null : !STAGE_EDEFAULT.equals(stage);
    }
    return super.eIsSet(featureID);
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public String toString() {
    if (eIsProxy()) return super.toString();

    StringBuilder result = new StringBuilder(super.toString());
    result.append(" (title: ");
    result.append(title);
    result.append(", stage: ");
    result.append(stage);
    result.append(')');
    return result.toString();
  }
} // ProjectImpl
