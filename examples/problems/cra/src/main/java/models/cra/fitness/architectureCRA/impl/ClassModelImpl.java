/** */
package models.cra.fitness.architectureCRA.impl;

import java.util.Collection;
import models.cra.fitness.architectureCRA.ArchitectureCRAPackage;
import models.cra.fitness.architectureCRA.ClassModel;
import models.cra.fitness.architectureCRA.Feature;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 *
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Class Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>The following features are implemented:
 *
 * <ul>
 *   <li>{@link models.cra.fitness.architectureCRA.impl.ClassModelImpl#getClasses <em>Classes</em>}
 *   <li>{@link models.cra.fitness.architectureCRA.impl.ClassModelImpl#getFeatures
 *       <em>Features</em>}
 * </ul>
 *
 * @generated
 */
public class ClassModelImpl extends NamedElementImpl implements ClassModel {
  /**
   * The cached value of the '{@link #getClasses() <em>Classes</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getClasses()
   * @generated
   * @ordered
   */
  protected EList<models.cra.fitness.architectureCRA.Class> classes;

  /**
   * The cached value of the '{@link #getFeatures() <em>Features</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getFeatures()
   * @generated
   * @ordered
   */
  protected EList<Feature> features;

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  protected ClassModelImpl() {
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
    return ArchitectureCRAPackage.Literals.CLASS_MODEL;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public EList<models.cra.fitness.architectureCRA.Class> getClasses() {
    if (classes == null) {
      classes =
          new EObjectContainmentEList<models.cra.fitness.architectureCRA.Class>(
              models.cra.fitness.architectureCRA.Class.class,
              this,
              ArchitectureCRAPackage.CLASS_MODEL__CLASSES);
    }
    return classes;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public EList<Feature> getFeatures() {
    if (features == null) {
      features =
          new EObjectContainmentEList<Feature>(
              Feature.class, this, ArchitectureCRAPackage.CLASS_MODEL__FEATURES);
    }
    return features;
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
      case ArchitectureCRAPackage.CLASS_MODEL__CLASSES:
        return ((InternalEList<?>) getClasses()).basicRemove(otherEnd, msgs);
      case ArchitectureCRAPackage.CLASS_MODEL__FEATURES:
        return ((InternalEList<?>) getFeatures()).basicRemove(otherEnd, msgs);
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
      case ArchitectureCRAPackage.CLASS_MODEL__CLASSES:
        return getClasses();
      case ArchitectureCRAPackage.CLASS_MODEL__FEATURES:
        return getFeatures();
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
      case ArchitectureCRAPackage.CLASS_MODEL__CLASSES:
        getClasses().clear();
        getClasses()
            .addAll((Collection<? extends models.cra.fitness.architectureCRA.Class>) newValue);
        return;
      case ArchitectureCRAPackage.CLASS_MODEL__FEATURES:
        getFeatures().clear();
        getFeatures().addAll((Collection<? extends Feature>) newValue);
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
      case ArchitectureCRAPackage.CLASS_MODEL__CLASSES:
        getClasses().clear();
        return;
      case ArchitectureCRAPackage.CLASS_MODEL__FEATURES:
        getFeatures().clear();
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
      case ArchitectureCRAPackage.CLASS_MODEL__CLASSES:
        return classes != null && !classes.isEmpty();
      case ArchitectureCRAPackage.CLASS_MODEL__FEATURES:
        return features != null && !features.isEmpty();
    }
    return super.eIsSet(featureID);
  }
} // ClassModelImpl
