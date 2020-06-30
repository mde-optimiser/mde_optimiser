/** */
package models.project.allocation.ecore.StudentProjectMarkerAllocation.impl;

import java.util.Collection;
import models.project.allocation.ecore.StudentProjectMarkerAllocation.Academic;
import models.project.allocation.ecore.StudentProjectMarkerAllocation.MarkingTeam;
import models.project.allocation.ecore.StudentProjectMarkerAllocation.Project;
import models.project.allocation.ecore.StudentProjectMarkerAllocation.StudentProjectMarkerAllocationPackage;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 *
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Academic</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>The following features are implemented:
 *
 * <ul>
 *   <li>{@link
 *       models.project.allocation.ecore.StudentProjectMarkerAllocation.impl.AcademicImpl#getProjects
 *       <em>Projects</em>}
 *   <li>{@link
 *       models.project.allocation.ecore.StudentProjectMarkerAllocation.impl.AcademicImpl#getSecondMarker
 *       <em>Second Marker</em>}
 *   <li>{@link
 *       models.project.allocation.ecore.StudentProjectMarkerAllocation.impl.AcademicImpl#getThirdMarker
 *       <em>Third Marker</em>}
 *   <li>{@link
 *       models.project.allocation.ecore.StudentProjectMarkerAllocation.impl.AcademicImpl#getMarkingTeam
 *       <em>Marking Team</em>}
 *   <li>{@link
 *       models.project.allocation.ecore.StudentProjectMarkerAllocation.impl.AcademicImpl#getName
 *       <em>Name</em>}
 *   <li>{@link
 *       models.project.allocation.ecore.StudentProjectMarkerAllocation.impl.AcademicImpl#getNumber
 *       <em>Number</em>}
 *   <li>{@link
 *       models.project.allocation.ecore.StudentProjectMarkerAllocation.impl.AcademicImpl#getEmail
 *       <em>Email</em>}
 * </ul>
 *
 * @generated
 */
public class AcademicImpl extends MinimalEObjectImpl.Container implements Academic {
  /**
   * The cached value of the '{@link #getProjects() <em>Projects</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getProjects()
   * @generated
   * @ordered
   */
  protected EList<Project> projects;

  /**
   * The cached value of the '{@link #getSecondMarker() <em>Second Marker</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getSecondMarker()
   * @generated
   * @ordered
   */
  protected EList<Project> secondMarker;

  /**
   * The cached value of the '{@link #getThirdMarker() <em>Third Marker</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getThirdMarker()
   * @generated
   * @ordered
   */
  protected EList<Project> thirdMarker;

  /**
   * The cached value of the '{@link #getMarkingTeam() <em>Marking Team</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getMarkingTeam()
   * @generated
   * @ordered
   */
  protected EList<MarkingTeam> markingTeam;

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
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  protected AcademicImpl() {
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
    return StudentProjectMarkerAllocationPackage.Literals.ACADEMIC;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public EList<Project> getProjects() {
    if (projects == null) {
      projects =
          new EObjectWithInverseResolvingEList<Project>(
              Project.class,
              this,
              StudentProjectMarkerAllocationPackage.ACADEMIC__PROJECTS,
              StudentProjectMarkerAllocationPackage.PROJECT__SUPERVISOR);
    }
    return projects;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public EList<Project> getSecondMarker() {
    if (secondMarker == null) {
      secondMarker =
          new EObjectWithInverseResolvingEList<Project>(
              Project.class,
              this,
              StudentProjectMarkerAllocationPackage.ACADEMIC__SECOND_MARKER,
              StudentProjectMarkerAllocationPackage.PROJECT__SECOND_MARKER);
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
  @Override
  public EList<Project> getThirdMarker() {
    if (thirdMarker == null) {
      thirdMarker =
          new EObjectWithInverseResolvingEList<Project>(
              Project.class,
              this,
              StudentProjectMarkerAllocationPackage.ACADEMIC__THIRD_MARKER,
              StudentProjectMarkerAllocationPackage.PROJECT__THIRD_MARKER);
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
  @Override
  public EList<MarkingTeam> getMarkingTeam() {
    if (markingTeam == null) {
      markingTeam =
          new EObjectWithInverseResolvingEList.ManyInverse<MarkingTeam>(
              MarkingTeam.class,
              this,
              StudentProjectMarkerAllocationPackage.ACADEMIC__MARKING_TEAM,
              StudentProjectMarkerAllocationPackage.MARKING_TEAM__ACADEMICS);
    }
    return markingTeam;
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
              StudentProjectMarkerAllocationPackage.ACADEMIC__NAME,
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
              StudentProjectMarkerAllocationPackage.ACADEMIC__NUMBER,
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
              StudentProjectMarkerAllocationPackage.ACADEMIC__EMAIL,
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
  @SuppressWarnings("unchecked")
  @Override
  public NotificationChain eInverseAdd(
      InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID) {
      case StudentProjectMarkerAllocationPackage.ACADEMIC__PROJECTS:
        return ((InternalEList<InternalEObject>) (InternalEList<?>) getProjects())
            .basicAdd(otherEnd, msgs);
      case StudentProjectMarkerAllocationPackage.ACADEMIC__SECOND_MARKER:
        return ((InternalEList<InternalEObject>) (InternalEList<?>) getSecondMarker())
            .basicAdd(otherEnd, msgs);
      case StudentProjectMarkerAllocationPackage.ACADEMIC__THIRD_MARKER:
        return ((InternalEList<InternalEObject>) (InternalEList<?>) getThirdMarker())
            .basicAdd(otherEnd, msgs);
      case StudentProjectMarkerAllocationPackage.ACADEMIC__MARKING_TEAM:
        return ((InternalEList<InternalEObject>) (InternalEList<?>) getMarkingTeam())
            .basicAdd(otherEnd, msgs);
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
      case StudentProjectMarkerAllocationPackage.ACADEMIC__PROJECTS:
        return ((InternalEList<?>) getProjects()).basicRemove(otherEnd, msgs);
      case StudentProjectMarkerAllocationPackage.ACADEMIC__SECOND_MARKER:
        return ((InternalEList<?>) getSecondMarker()).basicRemove(otherEnd, msgs);
      case StudentProjectMarkerAllocationPackage.ACADEMIC__THIRD_MARKER:
        return ((InternalEList<?>) getThirdMarker()).basicRemove(otherEnd, msgs);
      case StudentProjectMarkerAllocationPackage.ACADEMIC__MARKING_TEAM:
        return ((InternalEList<?>) getMarkingTeam()).basicRemove(otherEnd, msgs);
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
      case StudentProjectMarkerAllocationPackage.ACADEMIC__PROJECTS:
        return getProjects();
      case StudentProjectMarkerAllocationPackage.ACADEMIC__SECOND_MARKER:
        return getSecondMarker();
      case StudentProjectMarkerAllocationPackage.ACADEMIC__THIRD_MARKER:
        return getThirdMarker();
      case StudentProjectMarkerAllocationPackage.ACADEMIC__MARKING_TEAM:
        return getMarkingTeam();
      case StudentProjectMarkerAllocationPackage.ACADEMIC__NAME:
        return getName();
      case StudentProjectMarkerAllocationPackage.ACADEMIC__NUMBER:
        return getNumber();
      case StudentProjectMarkerAllocationPackage.ACADEMIC__EMAIL:
        return getEmail();
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
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue) {
    switch (featureID) {
      case StudentProjectMarkerAllocationPackage.ACADEMIC__PROJECTS:
        getProjects().clear();
        getProjects().addAll((Collection<? extends Project>) newValue);
        return;
      case StudentProjectMarkerAllocationPackage.ACADEMIC__SECOND_MARKER:
        getSecondMarker().clear();
        getSecondMarker().addAll((Collection<? extends Project>) newValue);
        return;
      case StudentProjectMarkerAllocationPackage.ACADEMIC__THIRD_MARKER:
        getThirdMarker().clear();
        getThirdMarker().addAll((Collection<? extends Project>) newValue);
        return;
      case StudentProjectMarkerAllocationPackage.ACADEMIC__MARKING_TEAM:
        getMarkingTeam().clear();
        getMarkingTeam().addAll((Collection<? extends MarkingTeam>) newValue);
        return;
      case StudentProjectMarkerAllocationPackage.ACADEMIC__NAME:
        setName((String) newValue);
        return;
      case StudentProjectMarkerAllocationPackage.ACADEMIC__NUMBER:
        setNumber((String) newValue);
        return;
      case StudentProjectMarkerAllocationPackage.ACADEMIC__EMAIL:
        setEmail((String) newValue);
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
      case StudentProjectMarkerAllocationPackage.ACADEMIC__PROJECTS:
        getProjects().clear();
        return;
      case StudentProjectMarkerAllocationPackage.ACADEMIC__SECOND_MARKER:
        getSecondMarker().clear();
        return;
      case StudentProjectMarkerAllocationPackage.ACADEMIC__THIRD_MARKER:
        getThirdMarker().clear();
        return;
      case StudentProjectMarkerAllocationPackage.ACADEMIC__MARKING_TEAM:
        getMarkingTeam().clear();
        return;
      case StudentProjectMarkerAllocationPackage.ACADEMIC__NAME:
        setName(NAME_EDEFAULT);
        return;
      case StudentProjectMarkerAllocationPackage.ACADEMIC__NUMBER:
        setNumber(NUMBER_EDEFAULT);
        return;
      case StudentProjectMarkerAllocationPackage.ACADEMIC__EMAIL:
        setEmail(EMAIL_EDEFAULT);
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
      case StudentProjectMarkerAllocationPackage.ACADEMIC__PROJECTS:
        return projects != null && !projects.isEmpty();
      case StudentProjectMarkerAllocationPackage.ACADEMIC__SECOND_MARKER:
        return secondMarker != null && !secondMarker.isEmpty();
      case StudentProjectMarkerAllocationPackage.ACADEMIC__THIRD_MARKER:
        return thirdMarker != null && !thirdMarker.isEmpty();
      case StudentProjectMarkerAllocationPackage.ACADEMIC__MARKING_TEAM:
        return markingTeam != null && !markingTeam.isEmpty();
      case StudentProjectMarkerAllocationPackage.ACADEMIC__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case StudentProjectMarkerAllocationPackage.ACADEMIC__NUMBER:
        return NUMBER_EDEFAULT == null ? number != null : !NUMBER_EDEFAULT.equals(number);
      case StudentProjectMarkerAllocationPackage.ACADEMIC__EMAIL:
        return EMAIL_EDEFAULT == null ? email != null : !EMAIL_EDEFAULT.equals(email);
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
    result.append(" (name: ");
    result.append(name);
    result.append(", number: ");
    result.append(number);
    result.append(", email: ");
    result.append(email);
    result.append(')');
    return result.toString();
  }
} // AcademicImpl
