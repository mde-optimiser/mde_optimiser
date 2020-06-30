/** */
package models.nrp.nextReleaseProblem.impl;

import java.util.Collection;
import models.nrp.nextReleaseProblem.Customer;
import models.nrp.nextReleaseProblem.EcorePackage;
import models.nrp.nextReleaseProblem.Requirement;
import models.nrp.nextReleaseProblem.Valuation;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 *
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Valuation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>The following features are implemented:
 *
 * <ul>
 *   <li>{@link models.nrp.nextReleaseProblem.impl.ValuationImpl#getRequirement
 *       <em>Requirement</em>}
 *   <li>{@link models.nrp.nextReleaseProblem.impl.ValuationImpl#getValue <em>Value</em>}
 *   <li>{@link models.nrp.nextReleaseProblem.impl.ValuationImpl#getAssignedBy <em>Assigned By</em>}
 *   <li>{@link models.nrp.nextReleaseProblem.impl.ValuationImpl#getContributesTo <em>Contributes
 *       To</em>}
 * </ul>
 *
 * @generated
 */
public class ValuationImpl extends MinimalEObjectImpl.Container implements Valuation {
  /**
   * The default value of the '{@link #getValue() <em>Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getValue()
   * @generated
   * @ordered
   */
  protected static final double VALUE_EDEFAULT = 0.0;

  /**
   * The cached value of the '{@link #getValue() <em>Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getValue()
   * @generated
   * @ordered
   */
  protected double value = VALUE_EDEFAULT;

  /**
   * The cached value of the '{@link #getAssignedBy() <em>Assigned By</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getAssignedBy()
   * @generated
   * @ordered
   */
  protected EList<Customer> assignedBy;

  /**
   * The cached value of the '{@link #getContributesTo() <em>Contributes To</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getContributesTo()
   * @generated
   * @ordered
   */
  protected EList<Requirement> contributesTo;

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  protected ValuationImpl() {
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
    return EcorePackage.Literals.VALUATION;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public Requirement getRequirement() {
    if (eContainerFeatureID() != EcorePackage.VALUATION__REQUIREMENT) return null;
    return (Requirement) eInternalContainer();
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public NotificationChain basicSetRequirement(Requirement newRequirement, NotificationChain msgs) {
    msgs =
        eBasicSetContainer(
            (InternalEObject) newRequirement, EcorePackage.VALUATION__REQUIREMENT, msgs);
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
  public void setRequirement(Requirement newRequirement) {
    if (newRequirement != eInternalContainer()
        || (eContainerFeatureID() != EcorePackage.VALUATION__REQUIREMENT
            && newRequirement != null)) {
      if (EcoreUtil.isAncestor(this, newRequirement))
        throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
      NotificationChain msgs = null;
      if (eInternalContainer() != null) msgs = eBasicRemoveFromContainer(msgs);
      if (newRequirement != null)
        msgs =
            ((InternalEObject) newRequirement)
                .eInverseAdd(this, EcorePackage.REQUIREMENT__VALUATIONS, Requirement.class, msgs);
      msgs = basicSetRequirement(newRequirement, msgs);
      if (msgs != null) msgs.dispatch();
    } else if (eNotificationRequired())
      eNotify(
          new ENotificationImpl(
              this,
              Notification.SET,
              EcorePackage.VALUATION__REQUIREMENT,
              newRequirement,
              newRequirement));
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public double getValue() {
    return value;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public void setValue(double newValue) {
    double oldValue = value;
    value = newValue;
    if (eNotificationRequired())
      eNotify(
          new ENotificationImpl(
              this, Notification.SET, EcorePackage.VALUATION__VALUE, oldValue, value));
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public EList<Customer> getAssignedBy() {
    if (assignedBy == null) {
      assignedBy =
          new EObjectWithInverseResolvingEList.ManyInverse<Customer>(
              Customer.class,
              this,
              EcorePackage.VALUATION__ASSIGNED_BY,
              EcorePackage.CUSTOMER__ASSIGNS);
    }
    return assignedBy;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public EList<Requirement> getContributesTo() {
    if (contributesTo == null) {
      contributesTo =
          new EObjectWithInverseResolvingEList.ManyInverse<Requirement>(
              Requirement.class,
              this,
              EcorePackage.VALUATION__CONTRIBUTES_TO,
              EcorePackage.REQUIREMENT__COMBINES);
    }
    return contributesTo;
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
      case EcorePackage.VALUATION__REQUIREMENT:
        if (eInternalContainer() != null) msgs = eBasicRemoveFromContainer(msgs);
        return basicSetRequirement((Requirement) otherEnd, msgs);
      case EcorePackage.VALUATION__ASSIGNED_BY:
        return ((InternalEList<InternalEObject>) (InternalEList<?>) getAssignedBy())
            .basicAdd(otherEnd, msgs);
      case EcorePackage.VALUATION__CONTRIBUTES_TO:
        return ((InternalEList<InternalEObject>) (InternalEList<?>) getContributesTo())
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
      case EcorePackage.VALUATION__REQUIREMENT:
        return basicSetRequirement(null, msgs);
      case EcorePackage.VALUATION__ASSIGNED_BY:
        return ((InternalEList<?>) getAssignedBy()).basicRemove(otherEnd, msgs);
      case EcorePackage.VALUATION__CONTRIBUTES_TO:
        return ((InternalEList<?>) getContributesTo()).basicRemove(otherEnd, msgs);
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
  public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
    switch (eContainerFeatureID()) {
      case EcorePackage.VALUATION__REQUIREMENT:
        return eInternalContainer()
            .eInverseRemove(this, EcorePackage.REQUIREMENT__VALUATIONS, Requirement.class, msgs);
    }
    return super.eBasicRemoveFromContainerFeature(msgs);
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
      case EcorePackage.VALUATION__REQUIREMENT:
        return getRequirement();
      case EcorePackage.VALUATION__VALUE:
        return getValue();
      case EcorePackage.VALUATION__ASSIGNED_BY:
        return getAssignedBy();
      case EcorePackage.VALUATION__CONTRIBUTES_TO:
        return getContributesTo();
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
      case EcorePackage.VALUATION__REQUIREMENT:
        setRequirement((Requirement) newValue);
        return;
      case EcorePackage.VALUATION__VALUE:
        setValue((Double) newValue);
        return;
      case EcorePackage.VALUATION__ASSIGNED_BY:
        getAssignedBy().clear();
        getAssignedBy().addAll((Collection<? extends Customer>) newValue);
        return;
      case EcorePackage.VALUATION__CONTRIBUTES_TO:
        getContributesTo().clear();
        getContributesTo().addAll((Collection<? extends Requirement>) newValue);
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
      case EcorePackage.VALUATION__REQUIREMENT:
        setRequirement((Requirement) null);
        return;
      case EcorePackage.VALUATION__VALUE:
        setValue(VALUE_EDEFAULT);
        return;
      case EcorePackage.VALUATION__ASSIGNED_BY:
        getAssignedBy().clear();
        return;
      case EcorePackage.VALUATION__CONTRIBUTES_TO:
        getContributesTo().clear();
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
      case EcorePackage.VALUATION__REQUIREMENT:
        return getRequirement() != null;
      case EcorePackage.VALUATION__VALUE:
        return value != VALUE_EDEFAULT;
      case EcorePackage.VALUATION__ASSIGNED_BY:
        return assignedBy != null && !assignedBy.isEmpty();
      case EcorePackage.VALUATION__CONTRIBUTES_TO:
        return contributesTo != null && !contributesTo.isEmpty();
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
    result.append(" (value: ");
    result.append(value);
    result.append(')');
    return result.toString();
  }
} // ValuationImpl
