/** */
package models.knapsack.ecore.impl;

import java.util.Collection;
import models.knapsack.ecore.EcorePackage;
import models.knapsack.ecore.Item;
import models.knapsack.ecore.Knapsack;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 *
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Knapsack</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>The following features are implemented:
 *
 * <ul>
 *   <li>{@link models.knapsack.ecore.impl.KnapsackImpl#getCapacity <em>Capacity</em>}
 *   <li>{@link models.knapsack.ecore.impl.KnapsackImpl#getContains <em>Contains</em>}
 * </ul>
 *
 * @generated
 */
public class KnapsackImpl extends NamedElementImpl implements Knapsack {
  /**
   * The default value of the '{@link #getCapacity() <em>Capacity</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getCapacity()
   * @generated
   * @ordered
   */
  protected static final Double CAPACITY_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getCapacity() <em>Capacity</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getCapacity()
   * @generated
   * @ordered
   */
  protected Double capacity = CAPACITY_EDEFAULT;

  /**
   * The cached value of the '{@link #getContains() <em>Contains</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getContains()
   * @generated
   * @ordered
   */
  protected EList<Item> contains;

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  protected KnapsackImpl() {
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
    return EcorePackage.Literals.KNAPSACK;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public Double getCapacity() {
    return capacity;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public void setCapacity(Double newCapacity) {
    Double oldCapacity = capacity;
    capacity = newCapacity;
    if (eNotificationRequired())
      eNotify(
          new ENotificationImpl(
              this, Notification.SET, EcorePackage.KNAPSACK__CAPACITY, oldCapacity, capacity));
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public EList<Item> getContains() {
    if (contains == null) {
      contains =
          new EObjectWithInverseResolvingEList<Item>(
              Item.class,
              this,
              EcorePackage.KNAPSACK__CONTAINS,
              EcorePackage.ITEM__IS_CONTAINED_BY);
    }
    return contains;
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
      case EcorePackage.KNAPSACK__CONTAINS:
        return ((InternalEList<InternalEObject>) (InternalEList<?>) getContains())
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
      case EcorePackage.KNAPSACK__CONTAINS:
        return ((InternalEList<?>) getContains()).basicRemove(otherEnd, msgs);
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
      case EcorePackage.KNAPSACK__CAPACITY:
        return getCapacity();
      case EcorePackage.KNAPSACK__CONTAINS:
        return getContains();
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
      case EcorePackage.KNAPSACK__CAPACITY:
        setCapacity((Double) newValue);
        return;
      case EcorePackage.KNAPSACK__CONTAINS:
        getContains().clear();
        getContains().addAll((Collection<? extends Item>) newValue);
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
      case EcorePackage.KNAPSACK__CAPACITY:
        setCapacity(CAPACITY_EDEFAULT);
        return;
      case EcorePackage.KNAPSACK__CONTAINS:
        getContains().clear();
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
      case EcorePackage.KNAPSACK__CAPACITY:
        return CAPACITY_EDEFAULT == null ? capacity != null : !CAPACITY_EDEFAULT.equals(capacity);
      case EcorePackage.KNAPSACK__CONTAINS:
        return contains != null && !contains.isEmpty();
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
    result.append(" (capacity: ");
    result.append(capacity);
    result.append(')');
    return result.toString();
  }
} // KnapsackImpl
