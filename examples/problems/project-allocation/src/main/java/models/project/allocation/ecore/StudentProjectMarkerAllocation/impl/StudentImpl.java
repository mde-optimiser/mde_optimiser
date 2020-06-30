/** */
package models.project.allocation.ecore.StudentProjectMarkerAllocation.impl;

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
 * An implementation of the model object '<em><b>Student</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>The following features are implemented:
 *
 * <ul>
 *   <li>{@link
 *       models.project.allocation.ecore.StudentProjectMarkerAllocation.impl.StudentImpl#getProject
 *       <em>Project</em>}
 *   <li>{@link
 *       models.project.allocation.ecore.StudentProjectMarkerAllocation.impl.StudentImpl#getEmail
 *       <em>Email</em>}
 *   <li>{@link
 *       models.project.allocation.ecore.StudentProjectMarkerAllocation.impl.StudentImpl#getName
 *       <em>Name</em>}
 *   <li>{@link
 *       models.project.allocation.ecore.StudentProjectMarkerAllocation.impl.StudentImpl#getNumber
 *       <em>Number</em>}
 * </ul>
 *
 * @generated
 */
public class StudentImpl extends MinimalEObjectImpl.Container implements Student {
  /**
   * The cached value of the '{@link #getProject() <em>Project</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getProject()
   * @generated
   * @ordered
   */
  protected Project project;

  /**
   * The default value of the '{@link #getEmail() <em>Email</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getEmail()
   * @generated
   * @ordered
   */
  protected static final String EMAIL_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getEmail() <em>Email</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getEmail()
   * @generated
   * @ordered
   */
  protected String email = EMAIL_EDEFAULT;

  /**
   * The default value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getName()
   * @generated
   * @ordered
   */
  protected static final String NAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getName()
   * @generated
   * @ordered
   */
  protected String name = NAME_EDEFAULT;

  /**
   * The default value of the '{@link #getNumber() <em>Number</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getNumber()
   * @generated
   * @ordered
   */
  protected static final String NUMBER_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getNumber() <em>Number</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getNumber()
   * @generated
   * @ordered
   */
  protected String number = NUMBER_EDEFAULT;

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  protected StudentImpl() {
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
    return StudentProjectMarkerAllocationPackage.Literals.STUDENT;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public Project getProject() {
    if (project != null && project.eIsProxy()) {
      InternalEObject oldProject = (InternalEObject) project;
      project = (Project) eResolveProxy(oldProject);
      if (project != oldProject) {
        if (eNotificationRequired())
          eNotify(
              new ENotificationImpl(
                  this,
                  Notification.RESOLVE,
                  StudentProjectMarkerAllocationPackage.STUDENT__PROJECT,
                  oldProject,
                  project));
      }
    }
    return project;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public Project basicGetProject() {
    return project;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public NotificationChain basicSetProject(Project newProject, NotificationChain msgs) {
    Project oldProject = project;
    project = newProject;
    if (eNotificationRequired()) {
      ENotificationImpl notification =
          new ENotificationImpl(
              this,
              Notification.SET,
              StudentProjectMarkerAllocationPackage.STUDENT__PROJECT,
              oldProject,
              newProject);
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
  public void setProject(Project newProject) {
    if (newProject != project) {
      NotificationChain msgs = null;
      if (project != null)
        msgs =
            ((InternalEObject) project)
                .eInverseRemove(
                    this,
                    StudentProjectMarkerAllocationPackage.PROJECT__STUDENT,
                    Project.class,
                    msgs);
      if (newProject != null)
        msgs =
            ((InternalEObject) newProject)
                .eInverseAdd(
                    this,
                    StudentProjectMarkerAllocationPackage.PROJECT__STUDENT,
                    Project.class,
                    msgs);
      msgs = basicSetProject(newProject, msgs);
      if (msgs != null) msgs.dispatch();
    } else if (eNotificationRequired())
      eNotify(
          new ENotificationImpl(
              this,
              Notification.SET,
              StudentProjectMarkerAllocationPackage.STUDENT__PROJECT,
              newProject,
              newProject));
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public String getEmail() {
    return email;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public void setEmail(String newEmail) {
    String oldEmail = email;
    email = newEmail;
    if (eNotificationRequired())
      eNotify(
          new ENotificationImpl(
              this,
              Notification.SET,
              StudentProjectMarkerAllocationPackage.STUDENT__EMAIL,
              oldEmail,
              email));
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public String getName() {
    return name;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public void setName(String newName) {
    String oldName = name;
    name = newName;
    if (eNotificationRequired())
      eNotify(
          new ENotificationImpl(
              this,
              Notification.SET,
              StudentProjectMarkerAllocationPackage.STUDENT__NAME,
              oldName,
              name));
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public String getNumber() {
    return number;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public void setNumber(String newNumber) {
    String oldNumber = number;
    number = newNumber;
    if (eNotificationRequired())
      eNotify(
          new ENotificationImpl(
              this,
              Notification.SET,
              StudentProjectMarkerAllocationPackage.STUDENT__NUMBER,
              oldNumber,
              number));
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
      case StudentProjectMarkerAllocationPackage.STUDENT__PROJECT:
        if (project != null)
          msgs =
              ((InternalEObject) project)
                  .eInverseRemove(
                      this,
                      StudentProjectMarkerAllocationPackage.PROJECT__STUDENT,
                      Project.class,
                      msgs);
        return basicSetProject((Project) otherEnd, msgs);
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
      case StudentProjectMarkerAllocationPackage.STUDENT__PROJECT:
        return basicSetProject(null, msgs);
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
      case StudentProjectMarkerAllocationPackage.STUDENT__PROJECT:
        if (resolve) return getProject();
        return basicGetProject();
      case StudentProjectMarkerAllocationPackage.STUDENT__EMAIL:
        return getEmail();
      case StudentProjectMarkerAllocationPackage.STUDENT__NAME:
        return getName();
      case StudentProjectMarkerAllocationPackage.STUDENT__NUMBER:
        return getNumber();
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
      case StudentProjectMarkerAllocationPackage.STUDENT__PROJECT:
        setProject((Project) newValue);
        return;
      case StudentProjectMarkerAllocationPackage.STUDENT__EMAIL:
        setEmail((String) newValue);
        return;
      case StudentProjectMarkerAllocationPackage.STUDENT__NAME:
        setName((String) newValue);
        return;
      case StudentProjectMarkerAllocationPackage.STUDENT__NUMBER:
        setNumber((String) newValue);
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
      case StudentProjectMarkerAllocationPackage.STUDENT__PROJECT:
        setProject((Project) null);
        return;
      case StudentProjectMarkerAllocationPackage.STUDENT__EMAIL:
        setEmail(EMAIL_EDEFAULT);
        return;
      case StudentProjectMarkerAllocationPackage.STUDENT__NAME:
        setName(NAME_EDEFAULT);
        return;
      case StudentProjectMarkerAllocationPackage.STUDENT__NUMBER:
        setNumber(NUMBER_EDEFAULT);
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
      case StudentProjectMarkerAllocationPackage.STUDENT__PROJECT:
        return project != null;
      case StudentProjectMarkerAllocationPackage.STUDENT__EMAIL:
        return EMAIL_EDEFAULT == null ? email != null : !EMAIL_EDEFAULT.equals(email);
      case StudentProjectMarkerAllocationPackage.STUDENT__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case StudentProjectMarkerAllocationPackage.STUDENT__NUMBER:
        return NUMBER_EDEFAULT == null ? number != null : !NUMBER_EDEFAULT.equals(number);
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
    result.append(" (email: ");
    result.append(email);
    result.append(", name: ");
    result.append(name);
    result.append(", number: ");
    result.append(number);
    result.append(')');
    return result.toString();
  }
} // StudentImpl
