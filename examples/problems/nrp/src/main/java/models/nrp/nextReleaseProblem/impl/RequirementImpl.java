/** */
package models.nrp.nextReleaseProblem.impl;

import java.util.Collection;
import models.nrp.nextReleaseProblem.EcorePackage;
import models.nrp.nextReleaseProblem.Requirement;
import models.nrp.nextReleaseProblem.RequirementRealisation;
import models.nrp.nextReleaseProblem.Valuation;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 *
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Requirement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>The following features are implemented:
 *
 * <ul>
 *   <li>{@link models.nrp.nextReleaseProblem.impl.RequirementImpl#getName <em>Name</em>}
 *   <li>{@link models.nrp.nextReleaseProblem.impl.RequirementImpl#getValuations
 *       <em>Valuations</em>}
 *   <li>{@link models.nrp.nextReleaseProblem.impl.RequirementImpl#getRealisations
 *       <em>Realisations</em>}
 *   <li>{@link models.nrp.nextReleaseProblem.impl.RequirementImpl#getCombines <em>Combines</em>}
 * </ul>
 *
 * @generated
 */
public class RequirementImpl extends MinimalEObjectImpl.Container implements Requirement {
  /**
   * The default value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getName()
   * @generated
   * @ordered
   */
  protected static final String NAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getName()
   * @generated
   * @ordered
   */
  protected String name = NAME_EDEFAULT;

  /**
   * The cached value of the '{@link #getValuations() <em>Valuations</em>}' containment reference
   * list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getValuations()
   * @generated
   * @ordered
   */
  protected EList<Valuation> valuations;

  /**
   * The cached value of the '{@link #getRealisations() <em>Realisations</em>}' containment
   * reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getRealisations()
   * @generated
   * @ordered
   */
  protected EList<RequirementRealisation> realisations;

  /**
   * The cached value of the '{@link #getCombines() <em>Combines</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getCombines()
   * @generated
   * @ordered
   */
  protected EList<Valuation> combines;

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  protected RequirementImpl() {
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
    return EcorePackage.Literals.REQUIREMENT;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public String getName() {
    return name;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public void setName(String newName) {
    String oldName = name;
    name = newName;
    if (eNotificationRequired())
      eNotify(
          new ENotificationImpl(
              this, Notification.SET, EcorePackage.REQUIREMENT__NAME, oldName, name));
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public EList<Valuation> getValuations() {
    if (valuations == null) {
      valuations =
          new EObjectContainmentWithInverseEList<Valuation>(
              Valuation.class,
              this,
              EcorePackage.REQUIREMENT__VALUATIONS,
              EcorePackage.VALUATION__REQUIREMENT);
    }
    return valuations;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public EList<RequirementRealisation> getRealisations() {
    if (realisations == null) {
      realisations =
          new EObjectContainmentWithInverseEList<RequirementRealisation>(
              RequirementRealisation.class,
              this,
              EcorePackage.REQUIREMENT__REALISATIONS,
              EcorePackage.REQUIREMENT_REALISATION__REQUIREMENT);
    }
    return realisations;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public EList<Valuation> getCombines() {
    if (combines == null) {
      combines =
          new EObjectWithInverseResolvingEList.ManyInverse<Valuation>(
              Valuation.class,
              this,
              EcorePackage.REQUIREMENT__COMBINES,
              EcorePackage.VALUATION__CONTRIBUTES_TO);
    }
    return combines;
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
      case EcorePackage.REQUIREMENT__VALUATIONS:
        return ((InternalEList<InternalEObject>) (InternalEList<?>) getValuations())
            .basicAdd(otherEnd, msgs);
      case EcorePackage.REQUIREMENT__REALISATIONS:
        return ((InternalEList<InternalEObject>) (InternalEList<?>) getRealisations())
            .basicAdd(otherEnd, msgs);
      case EcorePackage.REQUIREMENT__COMBINES:
        return ((InternalEList<InternalEObject>) (InternalEList<?>) getCombines())
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
      case EcorePackage.REQUIREMENT__VALUATIONS:
        return ((InternalEList<?>) getValuations()).basicRemove(otherEnd, msgs);
      case EcorePackage.REQUIREMENT__REALISATIONS:
        return ((InternalEList<?>) getRealisations()).basicRemove(otherEnd, msgs);
      case EcorePackage.REQUIREMENT__COMBINES:
        return ((InternalEList<?>) getCombines()).basicRemove(otherEnd, msgs);
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
      case EcorePackage.REQUIREMENT__NAME:
        return getName();
      case EcorePackage.REQUIREMENT__VALUATIONS:
        return getValuations();
      case EcorePackage.REQUIREMENT__REALISATIONS:
        return getRealisations();
      case EcorePackage.REQUIREMENT__COMBINES:
        return getCombines();
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
      case EcorePackage.REQUIREMENT__NAME:
        setName((String) newValue);
        return;
      case EcorePackage.REQUIREMENT__VALUATIONS:
        getValuations().clear();
        getValuations().addAll((Collection<? extends Valuation>) newValue);
        return;
      case EcorePackage.REQUIREMENT__REALISATIONS:
        getRealisations().clear();
        getRealisations().addAll((Collection<? extends RequirementRealisation>) newValue);
        return;
      case EcorePackage.REQUIREMENT__COMBINES:
        getCombines().clear();
        getCombines().addAll((Collection<? extends Valuation>) newValue);
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
      case EcorePackage.REQUIREMENT__NAME:
        setName(NAME_EDEFAULT);
        return;
      case EcorePackage.REQUIREMENT__VALUATIONS:
        getValuations().clear();
        return;
      case EcorePackage.REQUIREMENT__REALISATIONS:
        getRealisations().clear();
        return;
      case EcorePackage.REQUIREMENT__COMBINES:
        getCombines().clear();
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
      case EcorePackage.REQUIREMENT__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case EcorePackage.REQUIREMENT__VALUATIONS:
        return valuations != null && !valuations.isEmpty();
      case EcorePackage.REQUIREMENT__REALISATIONS:
        return realisations != null && !realisations.isEmpty();
      case EcorePackage.REQUIREMENT__COMBINES:
        return combines != null && !combines.isEmpty();
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
    result.append(" (name: ");
    result.append(name);
    result.append(')');
    return result.toString();
  }
} // RequirementImpl
