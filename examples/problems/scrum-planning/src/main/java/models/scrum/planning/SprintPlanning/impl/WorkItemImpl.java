/** */
package models.scrum.planning.SprintPlanning.impl;

import models.scrum.planning.SprintPlanning.Sprint;
import models.scrum.planning.SprintPlanning.SprintPlanningPackage;
import models.scrum.planning.SprintPlanning.Stakeholder;
import models.scrum.planning.SprintPlanning.WorkItem;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 *
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Work Item</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>The following features are implemented:
 *
 * <ul>
 *   <li>{@link models.scrum.planning.SprintPlanning.impl.WorkItemImpl#getIsPlannedFor <em>Is
 *       Planned For</em>}
 *   <li>{@link models.scrum.planning.SprintPlanning.impl.WorkItemImpl#getImportance
 *       <em>Importance</em>}
 *   <li>{@link models.scrum.planning.SprintPlanning.impl.WorkItemImpl#getEffort <em>Effort</em>}
 *   <li>{@link models.scrum.planning.SprintPlanning.impl.WorkItemImpl#getStakeholder
 *       <em>Stakeholder</em>}
 * </ul>
 *
 * @generated
 */
public class WorkItemImpl extends MinimalEObjectImpl.Container implements WorkItem {
  /**
   * The cached value of the '{@link #getIsPlannedFor() <em>Is Planned For</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getIsPlannedFor()
   * @generated
   * @ordered
   */
  protected Sprint isPlannedFor;

  /**
   * The default value of the '{@link #getImportance() <em>Importance</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getImportance()
   * @generated
   * @ordered
   */
  protected static final int IMPORTANCE_EDEFAULT = 0;

  /**
   * The cached value of the '{@link #getImportance() <em>Importance</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getImportance()
   * @generated
   * @ordered
   */
  protected int importance = IMPORTANCE_EDEFAULT;

  /**
   * The default value of the '{@link #getEffort() <em>Effort</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getEffort()
   * @generated
   * @ordered
   */
  protected static final int EFFORT_EDEFAULT = 0;

  /**
   * The cached value of the '{@link #getEffort() <em>Effort</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getEffort()
   * @generated
   * @ordered
   */
  protected int effort = EFFORT_EDEFAULT;

  /**
   * The cached value of the '{@link #getStakeholder() <em>Stakeholder</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getStakeholder()
   * @generated
   * @ordered
   */
  protected Stakeholder stakeholder;

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  protected WorkItemImpl() {
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
    return SprintPlanningPackage.Literals.WORK_ITEM;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public Sprint getIsPlannedFor() {
    if (isPlannedFor != null && isPlannedFor.eIsProxy()) {
      InternalEObject oldIsPlannedFor = (InternalEObject) isPlannedFor;
      isPlannedFor = (Sprint) eResolveProxy(oldIsPlannedFor);
      if (isPlannedFor != oldIsPlannedFor) {
        if (eNotificationRequired())
          eNotify(
              new ENotificationImpl(
                  this,
                  Notification.RESOLVE,
                  SprintPlanningPackage.WORK_ITEM__IS_PLANNED_FOR,
                  oldIsPlannedFor,
                  isPlannedFor));
      }
    }
    return isPlannedFor;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public Sprint basicGetIsPlannedFor() {
    return isPlannedFor;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public NotificationChain basicSetIsPlannedFor(Sprint newIsPlannedFor, NotificationChain msgs) {
    Sprint oldIsPlannedFor = isPlannedFor;
    isPlannedFor = newIsPlannedFor;
    if (eNotificationRequired()) {
      ENotificationImpl notification =
          new ENotificationImpl(
              this,
              Notification.SET,
              SprintPlanningPackage.WORK_ITEM__IS_PLANNED_FOR,
              oldIsPlannedFor,
              newIsPlannedFor);
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
  public void setIsPlannedFor(Sprint newIsPlannedFor) {
    if (newIsPlannedFor != isPlannedFor) {
      NotificationChain msgs = null;
      if (isPlannedFor != null)
        msgs =
            ((InternalEObject) isPlannedFor)
                .eInverseRemove(
                    this, SprintPlanningPackage.SPRINT__COMMITTED_ITEM, Sprint.class, msgs);
      if (newIsPlannedFor != null)
        msgs =
            ((InternalEObject) newIsPlannedFor)
                .eInverseAdd(
                    this, SprintPlanningPackage.SPRINT__COMMITTED_ITEM, Sprint.class, msgs);
      msgs = basicSetIsPlannedFor(newIsPlannedFor, msgs);
      if (msgs != null) msgs.dispatch();
    } else if (eNotificationRequired())
      eNotify(
          new ENotificationImpl(
              this,
              Notification.SET,
              SprintPlanningPackage.WORK_ITEM__IS_PLANNED_FOR,
              newIsPlannedFor,
              newIsPlannedFor));
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public int getImportance() {
    return importance;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public void setImportance(int newImportance) {
    int oldImportance = importance;
    importance = newImportance;
    if (eNotificationRequired())
      eNotify(
          new ENotificationImpl(
              this,
              Notification.SET,
              SprintPlanningPackage.WORK_ITEM__IMPORTANCE,
              oldImportance,
              importance));
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public int getEffort() {
    return effort;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public void setEffort(int newEffort) {
    int oldEffort = effort;
    effort = newEffort;
    if (eNotificationRequired())
      eNotify(
          new ENotificationImpl(
              this, Notification.SET, SprintPlanningPackage.WORK_ITEM__EFFORT, oldEffort, effort));
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public Stakeholder getStakeholder() {
    if (stakeholder != null && stakeholder.eIsProxy()) {
      InternalEObject oldStakeholder = (InternalEObject) stakeholder;
      stakeholder = (Stakeholder) eResolveProxy(oldStakeholder);
      if (stakeholder != oldStakeholder) {
        if (eNotificationRequired())
          eNotify(
              new ENotificationImpl(
                  this,
                  Notification.RESOLVE,
                  SprintPlanningPackage.WORK_ITEM__STAKEHOLDER,
                  oldStakeholder,
                  stakeholder));
      }
    }
    return stakeholder;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public Stakeholder basicGetStakeholder() {
    return stakeholder;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public NotificationChain basicSetStakeholder(Stakeholder newStakeholder, NotificationChain msgs) {
    Stakeholder oldStakeholder = stakeholder;
    stakeholder = newStakeholder;
    if (eNotificationRequired()) {
      ENotificationImpl notification =
          new ENotificationImpl(
              this,
              Notification.SET,
              SprintPlanningPackage.WORK_ITEM__STAKEHOLDER,
              oldStakeholder,
              newStakeholder);
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
  public void setStakeholder(Stakeholder newStakeholder) {
    if (newStakeholder != stakeholder) {
      NotificationChain msgs = null;
      if (stakeholder != null)
        msgs =
            ((InternalEObject) stakeholder)
                .eInverseRemove(
                    this, SprintPlanningPackage.STAKEHOLDER__WORKITEM, Stakeholder.class, msgs);
      if (newStakeholder != null)
        msgs =
            ((InternalEObject) newStakeholder)
                .eInverseAdd(
                    this, SprintPlanningPackage.STAKEHOLDER__WORKITEM, Stakeholder.class, msgs);
      msgs = basicSetStakeholder(newStakeholder, msgs);
      if (msgs != null) msgs.dispatch();
    } else if (eNotificationRequired())
      eNotify(
          new ENotificationImpl(
              this,
              Notification.SET,
              SprintPlanningPackage.WORK_ITEM__STAKEHOLDER,
              newStakeholder,
              newStakeholder));
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
      case SprintPlanningPackage.WORK_ITEM__IS_PLANNED_FOR:
        if (isPlannedFor != null)
          msgs =
              ((InternalEObject) isPlannedFor)
                  .eInverseRemove(
                      this, SprintPlanningPackage.SPRINT__COMMITTED_ITEM, Sprint.class, msgs);
        return basicSetIsPlannedFor((Sprint) otherEnd, msgs);
      case SprintPlanningPackage.WORK_ITEM__STAKEHOLDER:
        if (stakeholder != null)
          msgs =
              ((InternalEObject) stakeholder)
                  .eInverseRemove(
                      this, SprintPlanningPackage.STAKEHOLDER__WORKITEM, Stakeholder.class, msgs);
        return basicSetStakeholder((Stakeholder) otherEnd, msgs);
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
      case SprintPlanningPackage.WORK_ITEM__IS_PLANNED_FOR:
        return basicSetIsPlannedFor(null, msgs);
      case SprintPlanningPackage.WORK_ITEM__STAKEHOLDER:
        return basicSetStakeholder(null, msgs);
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
      case SprintPlanningPackage.WORK_ITEM__IS_PLANNED_FOR:
        if (resolve) return getIsPlannedFor();
        return basicGetIsPlannedFor();
      case SprintPlanningPackage.WORK_ITEM__IMPORTANCE:
        return getImportance();
      case SprintPlanningPackage.WORK_ITEM__EFFORT:
        return getEffort();
      case SprintPlanningPackage.WORK_ITEM__STAKEHOLDER:
        if (resolve) return getStakeholder();
        return basicGetStakeholder();
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
      case SprintPlanningPackage.WORK_ITEM__IS_PLANNED_FOR:
        setIsPlannedFor((Sprint) newValue);
        return;
      case SprintPlanningPackage.WORK_ITEM__IMPORTANCE:
        setImportance((Integer) newValue);
        return;
      case SprintPlanningPackage.WORK_ITEM__EFFORT:
        setEffort((Integer) newValue);
        return;
      case SprintPlanningPackage.WORK_ITEM__STAKEHOLDER:
        setStakeholder((Stakeholder) newValue);
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
      case SprintPlanningPackage.WORK_ITEM__IS_PLANNED_FOR:
        setIsPlannedFor((Sprint) null);
        return;
      case SprintPlanningPackage.WORK_ITEM__IMPORTANCE:
        setImportance(IMPORTANCE_EDEFAULT);
        return;
      case SprintPlanningPackage.WORK_ITEM__EFFORT:
        setEffort(EFFORT_EDEFAULT);
        return;
      case SprintPlanningPackage.WORK_ITEM__STAKEHOLDER:
        setStakeholder((Stakeholder) null);
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
      case SprintPlanningPackage.WORK_ITEM__IS_PLANNED_FOR:
        return isPlannedFor != null;
      case SprintPlanningPackage.WORK_ITEM__IMPORTANCE:
        return importance != IMPORTANCE_EDEFAULT;
      case SprintPlanningPackage.WORK_ITEM__EFFORT:
        return effort != EFFORT_EDEFAULT;
      case SprintPlanningPackage.WORK_ITEM__STAKEHOLDER:
        return stakeholder != null;
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
    result.append(" (Importance: ");
    result.append(importance);
    result.append(", Effort: ");
    result.append(effort);
    result.append(')');
    return result.toString();
  }
} // WorkItemImpl
