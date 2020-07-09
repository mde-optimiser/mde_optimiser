/** */
package models.knapsack.ecore.impl;

import java.util.Collection;
import models.knapsack.ecore.EcorePackage;
import models.knapsack.ecore.Item;
import models.knapsack.ecore.Knapsack;
import models.knapsack.ecore.Plan;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 *
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Plan</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>The following features are implemented:
 *
 * <ul>
 *   <li>{@link models.knapsack.ecore.impl.PlanImpl#getItems <em>Items</em>}
 *   <li>{@link models.knapsack.ecore.impl.PlanImpl#getKnapsacks <em>Knapsacks</em>}
 * </ul>
 *
 * @generated
 */
public class PlanImpl extends NamedElementImpl implements Plan {
  /**
   * The cached value of the '{@link #getItems() <em>Items</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getItems()
   * @generated
   * @ordered
   */
  protected EList<Item> items;

  /**
   * The cached value of the '{@link #getKnapsacks() <em>Knapsacks</em>}' containment reference
   * list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getKnapsacks()
   * @generated
   * @ordered
   */
  protected EList<Knapsack> knapsacks;

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  protected PlanImpl() {
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
    return EcorePackage.Literals.PLAN;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public EList<Item> getItems() {
    if (items == null) {
      items = new EObjectContainmentEList<Item>(Item.class, this, EcorePackage.PLAN__ITEMS);
    }
    return items;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public EList<Knapsack> getKnapsacks() {
    if (knapsacks == null) {
      knapsacks =
          new EObjectContainmentEList<Knapsack>(Knapsack.class, this, EcorePackage.PLAN__KNAPSACKS);
    }
    return knapsacks;
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
      case EcorePackage.PLAN__ITEMS:
        return ((InternalEList<?>) getItems()).basicRemove(otherEnd, msgs);
      case EcorePackage.PLAN__KNAPSACKS:
        return ((InternalEList<?>) getKnapsacks()).basicRemove(otherEnd, msgs);
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
      case EcorePackage.PLAN__ITEMS:
        return getItems();
      case EcorePackage.PLAN__KNAPSACKS:
        return getKnapsacks();
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
      case EcorePackage.PLAN__ITEMS:
        getItems().clear();
        getItems().addAll((Collection<? extends Item>) newValue);
        return;
      case EcorePackage.PLAN__KNAPSACKS:
        getKnapsacks().clear();
        getKnapsacks().addAll((Collection<? extends Knapsack>) newValue);
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
      case EcorePackage.PLAN__ITEMS:
        getItems().clear();
        return;
      case EcorePackage.PLAN__KNAPSACKS:
        getKnapsacks().clear();
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
      case EcorePackage.PLAN__ITEMS:
        return items != null && !items.isEmpty();
      case EcorePackage.PLAN__KNAPSACKS:
        return knapsacks != null && !knapsacks.isEmpty();
    }
    return super.eIsSet(featureID);
  }
} // PlanImpl
