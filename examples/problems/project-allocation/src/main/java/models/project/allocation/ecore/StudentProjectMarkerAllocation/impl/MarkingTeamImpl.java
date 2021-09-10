/** */
package models.project.allocation.ecore.StudentProjectMarkerAllocation.impl;

import java.math.BigInteger;
import java.util.Collection;
import models.project.allocation.ecore.StudentProjectMarkerAllocation.Academic;
import models.project.allocation.ecore.StudentProjectMarkerAllocation.MarkingTeam;
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
 * An implementation of the model object '<em><b>Marking Team</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>The following features are implemented:
 *
 * <ul>
 *   <li>{@link
 *       models.project.allocation.ecore.StudentProjectMarkerAllocation.impl.MarkingTeamImpl#getNumber
 *       <em>Number</em>}
 *   <li>{@link
 *       models.project.allocation.ecore.StudentProjectMarkerAllocation.impl.MarkingTeamImpl#getAcademics
 *       <em>Academics</em>}
 * </ul>
 *
 * @generated
 */
public class MarkingTeamImpl extends MinimalEObjectImpl.Container implements MarkingTeam {
  /**
   * The default value of the '{@link #getNumber() <em>Number</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getNumber()
   * @generated
   * @ordered
   */
  protected static final BigInteger NUMBER_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getNumber() <em>Number</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getNumber()
   * @generated
   * @ordered
   */
  protected BigInteger number = NUMBER_EDEFAULT;

  /**
   * The cached value of the '{@link #getAcademics() <em>Academics</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getAcademics()
   * @generated
   * @ordered
   */
  protected EList<Academic> academics;

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  protected MarkingTeamImpl() {
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
    return StudentProjectMarkerAllocationPackage.Literals.MARKING_TEAM;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public BigInteger getNumber() {
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
  public void setNumber(BigInteger newNumber) {
    BigInteger oldNumber = number;
    number = newNumber;
    if (eNotificationRequired())
      eNotify(
          new ENotificationImpl(
              this,
              Notification.SET,
              StudentProjectMarkerAllocationPackage.MARKING_TEAM__NUMBER,
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
  public EList<Academic> getAcademics() {
    if (academics == null) {
      academics =
          new EObjectWithInverseResolvingEList.ManyInverse<Academic>(
              Academic.class,
              this,
              StudentProjectMarkerAllocationPackage.MARKING_TEAM__ACADEMICS,
              StudentProjectMarkerAllocationPackage.ACADEMIC__MARKING_TEAM);
    }
    return academics;
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
      case StudentProjectMarkerAllocationPackage.MARKING_TEAM__ACADEMICS:
        return ((InternalEList<InternalEObject>) (InternalEList<?>) getAcademics())
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
      case StudentProjectMarkerAllocationPackage.MARKING_TEAM__ACADEMICS:
        return ((InternalEList<?>) getAcademics()).basicRemove(otherEnd, msgs);
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
      case StudentProjectMarkerAllocationPackage.MARKING_TEAM__NUMBER:
        return getNumber();
      case StudentProjectMarkerAllocationPackage.MARKING_TEAM__ACADEMICS:
        return getAcademics();
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
      case StudentProjectMarkerAllocationPackage.MARKING_TEAM__NUMBER:
        setNumber((BigInteger) newValue);
        return;
      case StudentProjectMarkerAllocationPackage.MARKING_TEAM__ACADEMICS:
        getAcademics().clear();
        getAcademics().addAll((Collection<? extends Academic>) newValue);
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
      case StudentProjectMarkerAllocationPackage.MARKING_TEAM__NUMBER:
        setNumber(NUMBER_EDEFAULT);
        return;
      case StudentProjectMarkerAllocationPackage.MARKING_TEAM__ACADEMICS:
        getAcademics().clear();
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
      case StudentProjectMarkerAllocationPackage.MARKING_TEAM__NUMBER:
        return NUMBER_EDEFAULT == null ? number != null : !NUMBER_EDEFAULT.equals(number);
      case StudentProjectMarkerAllocationPackage.MARKING_TEAM__ACADEMICS:
        return academics != null && !academics.isEmpty();
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
    result.append(" (number: ");
    result.append(number);
    result.append(')');
    return result.toString();
  }
} // MarkingTeamImpl
