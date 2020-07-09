/** */
package models.stack.ecore.stack.impl;

import java.util.Collection;
import models.stack.ecore.stack.Stack;
import models.stack.ecore.stack.StackModel;
import models.stack.ecore.stack.StackPackage;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 *
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>The following features are implemented:
 *
 * <ul>
 *   <li>{@link models.stack.ecore.stack.impl.StackModelImpl#getStacks <em>Stacks</em>}
 * </ul>
 *
 * @generated
 */
public class StackModelImpl extends MinimalEObjectImpl.Container implements StackModel {
  /**
   * The cached value of the '{@link #getStacks() <em>Stacks</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getStacks()
   * @generated
   * @ordered
   */
  protected EList<Stack> stacks;

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  protected StackModelImpl() {
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
    return StackPackage.Literals.STACK_MODEL;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public EList<Stack> getStacks() {
    if (stacks == null) {
      stacks =
          new EObjectContainmentEList<Stack>(Stack.class, this, StackPackage.STACK_MODEL__STACKS);
    }
    return stacks;
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
      case StackPackage.STACK_MODEL__STACKS:
        return ((InternalEList<?>) getStacks()).basicRemove(otherEnd, msgs);
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
      case StackPackage.STACK_MODEL__STACKS:
        return getStacks();
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
      case StackPackage.STACK_MODEL__STACKS:
        getStacks().clear();
        getStacks().addAll((Collection<? extends Stack>) newValue);
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
      case StackPackage.STACK_MODEL__STACKS:
        getStacks().clear();
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
      case StackPackage.STACK_MODEL__STACKS:
        return stacks != null && !stacks.isEmpty();
    }
    return super.eIsSet(featureID);
  }
} // StackModelImpl
