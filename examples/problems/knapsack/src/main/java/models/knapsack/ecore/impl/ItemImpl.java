/** */
package models.knapsack.ecore.impl;

import models.knapsack.ecore.EcorePackage;
import models.knapsack.ecore.Item;
import models.knapsack.ecore.Knapsack;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 *
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Item</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>The following features are implemented:
 *
 * <ul>
 *   <li>{@link models.knapsack.ecore.impl.ItemImpl#getWeight <em>Weight</em>}
 *   <li>{@link models.knapsack.ecore.impl.ItemImpl#getGain <em>Gain</em>}
 *   <li>{@link models.knapsack.ecore.impl.ItemImpl#getIsContainedBy <em>Is Contained By</em>}
 * </ul>
 *
 * @generated
 */
public class ItemImpl extends NamedElementImpl implements Item {
  /**
   * The default value of the '{@link #getWeight() <em>Weight</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getWeight()
   * @generated
   * @ordered
   */
  protected static final Double WEIGHT_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getWeight() <em>Weight</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getWeight()
   * @generated
   * @ordered
   */
  protected Double weight = WEIGHT_EDEFAULT;

  /**
   * The default value of the '{@link #getGain() <em>Gain</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getGain()
   * @generated
   * @ordered
   */
  protected static final Double GAIN_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getGain() <em>Gain</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getGain()
   * @generated
   * @ordered
   */
  protected Double gain = GAIN_EDEFAULT;

  /**
   * The cached value of the '{@link #getIsContainedBy() <em>Is Contained By</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getIsContainedBy()
   * @generated
   * @ordered
   */
  protected Knapsack isContainedBy;

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  protected ItemImpl() {
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
    return EcorePackage.Literals.ITEM;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public Double getWeight() {
    return weight;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public void setWeight(Double newWeight) {
    Double oldWeight = weight;
    weight = newWeight;
    if (eNotificationRequired())
      eNotify(
          new ENotificationImpl(
              this, Notification.SET, EcorePackage.ITEM__WEIGHT, oldWeight, weight));
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public Double getGain() {
    return gain;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public void setGain(Double newGain) {
    Double oldGain = gain;
    gain = newGain;
    if (eNotificationRequired())
      eNotify(
          new ENotificationImpl(this, Notification.SET, EcorePackage.ITEM__GAIN, oldGain, gain));
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public Knapsack getIsContainedBy() {
    if (isContainedBy != null && isContainedBy.eIsProxy()) {
      InternalEObject oldIsContainedBy = (InternalEObject) isContainedBy;
      isContainedBy = (Knapsack) eResolveProxy(oldIsContainedBy);
      if (isContainedBy != oldIsContainedBy) {
        if (eNotificationRequired())
          eNotify(
              new ENotificationImpl(
                  this,
                  Notification.RESOLVE,
                  EcorePackage.ITEM__IS_CONTAINED_BY,
                  oldIsContainedBy,
                  isContainedBy));
      }
    }
    return isContainedBy;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public Knapsack basicGetIsContainedBy() {
    return isContainedBy;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public NotificationChain basicSetIsContainedBy(
      Knapsack newIsContainedBy, NotificationChain msgs) {
    Knapsack oldIsContainedBy = isContainedBy;
    isContainedBy = newIsContainedBy;
    if (eNotificationRequired()) {
      ENotificationImpl notification =
          new ENotificationImpl(
              this,
              Notification.SET,
              EcorePackage.ITEM__IS_CONTAINED_BY,
              oldIsContainedBy,
              newIsContainedBy);
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
  public void setIsContainedBy(Knapsack newIsContainedBy) {
    if (newIsContainedBy != isContainedBy) {
      NotificationChain msgs = null;
      if (isContainedBy != null)
        msgs =
            ((InternalEObject) isContainedBy)
                .eInverseRemove(this, EcorePackage.KNAPSACK__CONTAINS, Knapsack.class, msgs);
      if (newIsContainedBy != null)
        msgs =
            ((InternalEObject) newIsContainedBy)
                .eInverseAdd(this, EcorePackage.KNAPSACK__CONTAINS, Knapsack.class, msgs);
      msgs = basicSetIsContainedBy(newIsContainedBy, msgs);
      if (msgs != null) msgs.dispatch();
    } else if (eNotificationRequired())
      eNotify(
          new ENotificationImpl(
              this,
              Notification.SET,
              EcorePackage.ITEM__IS_CONTAINED_BY,
              newIsContainedBy,
              newIsContainedBy));
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
      case EcorePackage.ITEM__IS_CONTAINED_BY:
        if (isContainedBy != null)
          msgs =
              ((InternalEObject) isContainedBy)
                  .eInverseRemove(this, EcorePackage.KNAPSACK__CONTAINS, Knapsack.class, msgs);
        return basicSetIsContainedBy((Knapsack) otherEnd, msgs);
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
      case EcorePackage.ITEM__IS_CONTAINED_BY:
        return basicSetIsContainedBy(null, msgs);
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
      case EcorePackage.ITEM__WEIGHT:
        return getWeight();
      case EcorePackage.ITEM__GAIN:
        return getGain();
      case EcorePackage.ITEM__IS_CONTAINED_BY:
        if (resolve) return getIsContainedBy();
        return basicGetIsContainedBy();
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
      case EcorePackage.ITEM__WEIGHT:
        setWeight((Double) newValue);
        return;
      case EcorePackage.ITEM__GAIN:
        setGain((Double) newValue);
        return;
      case EcorePackage.ITEM__IS_CONTAINED_BY:
        setIsContainedBy((Knapsack) newValue);
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
      case EcorePackage.ITEM__WEIGHT:
        setWeight(WEIGHT_EDEFAULT);
        return;
      case EcorePackage.ITEM__GAIN:
        setGain(GAIN_EDEFAULT);
        return;
      case EcorePackage.ITEM__IS_CONTAINED_BY:
        setIsContainedBy((Knapsack) null);
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
      case EcorePackage.ITEM__WEIGHT:
        return WEIGHT_EDEFAULT == null ? weight != null : !WEIGHT_EDEFAULT.equals(weight);
      case EcorePackage.ITEM__GAIN:
        return GAIN_EDEFAULT == null ? gain != null : !GAIN_EDEFAULT.equals(gain);
      case EcorePackage.ITEM__IS_CONTAINED_BY:
        return isContainedBy != null;
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
    result.append(" (weight: ");
    result.append(weight);
    result.append(", gain: ");
    result.append(gain);
    result.append(')');
    return result.toString();
  }
} // ItemImpl
