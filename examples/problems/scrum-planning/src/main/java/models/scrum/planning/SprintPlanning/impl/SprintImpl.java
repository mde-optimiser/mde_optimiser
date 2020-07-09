/** */
package models.scrum.planning.SprintPlanning.impl;

import java.util.Collection;
import models.scrum.planning.SprintPlanning.Sprint;
import models.scrum.planning.SprintPlanning.SprintPlanningPackage;
import models.scrum.planning.SprintPlanning.WorkItem;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 *
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Sprint</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>The following features are implemented:
 *
 * <ul>
 *   <li>{@link models.scrum.planning.SprintPlanning.impl.SprintImpl#getCommittedItem <em>Committed
 *       Item</em>}
 * </ul>
 *
 * @generated
 */
public class SprintImpl extends MinimalEObjectImpl.Container implements Sprint {
  /**
   * The cached value of the '{@link #getCommittedItem() <em>Committed Item</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getCommittedItem()
   * @generated
   * @ordered
   */
  protected EList<WorkItem> committedItem;

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  protected SprintImpl() {
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
    return SprintPlanningPackage.Literals.SPRINT;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public EList<WorkItem> getCommittedItem() {
    if (committedItem == null) {
      committedItem =
          new EObjectWithInverseResolvingEList<WorkItem>(
              WorkItem.class,
              this,
              SprintPlanningPackage.SPRINT__COMMITTED_ITEM,
              SprintPlanningPackage.WORK_ITEM__IS_PLANNED_FOR);
    }
    return committedItem;
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
      case SprintPlanningPackage.SPRINT__COMMITTED_ITEM:
        return ((InternalEList<InternalEObject>) (InternalEList<?>) getCommittedItem())
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
      case SprintPlanningPackage.SPRINT__COMMITTED_ITEM:
        return ((InternalEList<?>) getCommittedItem()).basicRemove(otherEnd, msgs);
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
      case SprintPlanningPackage.SPRINT__COMMITTED_ITEM:
        return getCommittedItem();
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
      case SprintPlanningPackage.SPRINT__COMMITTED_ITEM:
        getCommittedItem().clear();
        getCommittedItem().addAll((Collection<? extends WorkItem>) newValue);
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
      case SprintPlanningPackage.SPRINT__COMMITTED_ITEM:
        getCommittedItem().clear();
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
      case SprintPlanningPackage.SPRINT__COMMITTED_ITEM:
        return committedItem != null && !committedItem.isEmpty();
    }
    return super.eIsSet(featureID);
  }
} // SprintImpl
