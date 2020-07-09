/** */
package models.cra.fitness.architectureCRA.impl;

import java.util.Collection;
import models.cra.fitness.architectureCRA.ArchitectureCRAPackage;
import models.cra.fitness.architectureCRA.Feature;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 *
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Class</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>The following features are implemented:
 *
 * <ul>
 *   <li>{@link models.cra.fitness.architectureCRA.impl.ClassImpl#getEncapsulates
 *       <em>Encapsulates</em>}
 * </ul>
 *
 * @generated
 */
public class ClassImpl extends NamedElementImpl
    implements models.cra.fitness.architectureCRA.Class {
  /**
   * The cached value of the '{@link #getEncapsulates() <em>Encapsulates</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getEncapsulates()
   * @generated
   * @ordered
   */
  protected EList<Feature> encapsulates;

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  protected ClassImpl() {
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
    return ArchitectureCRAPackage.Literals.CLASS;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public EList<Feature> getEncapsulates() {
    if (encapsulates == null) {
      encapsulates =
          new EObjectWithInverseResolvingEList<Feature>(
              Feature.class,
              this,
              ArchitectureCRAPackage.CLASS__ENCAPSULATES,
              ArchitectureCRAPackage.FEATURE__IS_ENCAPSULATED_BY);
    }
    return encapsulates;
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
      case ArchitectureCRAPackage.CLASS__ENCAPSULATES:
        return ((InternalEList<InternalEObject>) (InternalEList<?>) getEncapsulates())
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
      case ArchitectureCRAPackage.CLASS__ENCAPSULATES:
        return ((InternalEList<?>) getEncapsulates()).basicRemove(otherEnd, msgs);
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
      case ArchitectureCRAPackage.CLASS__ENCAPSULATES:
        return getEncapsulates();
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
      case ArchitectureCRAPackage.CLASS__ENCAPSULATES:
        getEncapsulates().clear();
        getEncapsulates().addAll((Collection<? extends Feature>) newValue);
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
      case ArchitectureCRAPackage.CLASS__ENCAPSULATES:
        getEncapsulates().clear();
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
      case ArchitectureCRAPackage.CLASS__ENCAPSULATES:
        return encapsulates != null && !encapsulates.isEmpty();
    }
    return super.eIsSet(featureID);
  }
} // ClassImpl
