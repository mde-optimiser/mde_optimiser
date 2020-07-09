/** */
package models.nrp.nextReleaseProblem.impl;

import java.util.Collection;
import models.nrp.nextReleaseProblem.EcorePackage;
import models.nrp.nextReleaseProblem.Requirement;
import models.nrp.nextReleaseProblem.RequirementRealisation;
import models.nrp.nextReleaseProblem.SoftwareArtifact;
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
 * An implementation of the model object '<em><b>Requirement Realisation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>The following features are implemented:
 *
 * <ul>
 *   <li>{@link models.nrp.nextReleaseProblem.impl.RequirementRealisationImpl#getRequirement
 *       <em>Requirement</em>}
 *   <li>{@link models.nrp.nextReleaseProblem.impl.RequirementRealisationImpl#getPercentage
 *       <em>Percentage</em>}
 *   <li>{@link models.nrp.nextReleaseProblem.impl.RequirementRealisationImpl#getDependsOn
 *       <em>Depends On</em>}
 * </ul>
 *
 * @generated
 */
public class RequirementRealisationImpl extends MinimalEObjectImpl.Container
    implements RequirementRealisation {
  /**
   * The default value of the '{@link #getPercentage() <em>Percentage</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getPercentage()
   * @generated
   * @ordered
   */
  protected static final double PERCENTAGE_EDEFAULT = 1.0;

  /**
   * The cached value of the '{@link #getPercentage() <em>Percentage</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getPercentage()
   * @generated
   * @ordered
   */
  protected double percentage = PERCENTAGE_EDEFAULT;

  /**
   * The cached value of the '{@link #getDependsOn() <em>Depends On</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getDependsOn()
   * @generated
   * @ordered
   */
  protected EList<SoftwareArtifact> dependsOn;

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  protected RequirementRealisationImpl() {
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
    return EcorePackage.Literals.REQUIREMENT_REALISATION;
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
    if (eContainerFeatureID() != EcorePackage.REQUIREMENT_REALISATION__REQUIREMENT) return null;
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
            (InternalEObject) newRequirement,
            EcorePackage.REQUIREMENT_REALISATION__REQUIREMENT,
            msgs);
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
        || (eContainerFeatureID() != EcorePackage.REQUIREMENT_REALISATION__REQUIREMENT
            && newRequirement != null)) {
      if (EcoreUtil.isAncestor(this, newRequirement))
        throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
      NotificationChain msgs = null;
      if (eInternalContainer() != null) msgs = eBasicRemoveFromContainer(msgs);
      if (newRequirement != null)
        msgs =
            ((InternalEObject) newRequirement)
                .eInverseAdd(this, EcorePackage.REQUIREMENT__REALISATIONS, Requirement.class, msgs);
      msgs = basicSetRequirement(newRequirement, msgs);
      if (msgs != null) msgs.dispatch();
    } else if (eNotificationRequired())
      eNotify(
          new ENotificationImpl(
              this,
              Notification.SET,
              EcorePackage.REQUIREMENT_REALISATION__REQUIREMENT,
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
  public double getPercentage() {
    return percentage;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public void setPercentage(double newPercentage) {
    double oldPercentage = percentage;
    percentage = newPercentage;
    if (eNotificationRequired())
      eNotify(
          new ENotificationImpl(
              this,
              Notification.SET,
              EcorePackage.REQUIREMENT_REALISATION__PERCENTAGE,
              oldPercentage,
              percentage));
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public EList<SoftwareArtifact> getDependsOn() {
    if (dependsOn == null) {
      dependsOn =
          new EObjectWithInverseResolvingEList.ManyInverse<SoftwareArtifact>(
              SoftwareArtifact.class,
              this,
              EcorePackage.REQUIREMENT_REALISATION__DEPENDS_ON,
              EcorePackage.SOFTWARE_ARTIFACT__CONTRIBUTES_TO);
    }
    return dependsOn;
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
      case EcorePackage.REQUIREMENT_REALISATION__REQUIREMENT:
        if (eInternalContainer() != null) msgs = eBasicRemoveFromContainer(msgs);
        return basicSetRequirement((Requirement) otherEnd, msgs);
      case EcorePackage.REQUIREMENT_REALISATION__DEPENDS_ON:
        return ((InternalEList<InternalEObject>) (InternalEList<?>) getDependsOn())
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
      case EcorePackage.REQUIREMENT_REALISATION__REQUIREMENT:
        return basicSetRequirement(null, msgs);
      case EcorePackage.REQUIREMENT_REALISATION__DEPENDS_ON:
        return ((InternalEList<?>) getDependsOn()).basicRemove(otherEnd, msgs);
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
      case EcorePackage.REQUIREMENT_REALISATION__REQUIREMENT:
        return eInternalContainer()
            .eInverseRemove(this, EcorePackage.REQUIREMENT__REALISATIONS, Requirement.class, msgs);
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
      case EcorePackage.REQUIREMENT_REALISATION__REQUIREMENT:
        return getRequirement();
      case EcorePackage.REQUIREMENT_REALISATION__PERCENTAGE:
        return getPercentage();
      case EcorePackage.REQUIREMENT_REALISATION__DEPENDS_ON:
        return getDependsOn();
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
      case EcorePackage.REQUIREMENT_REALISATION__REQUIREMENT:
        setRequirement((Requirement) newValue);
        return;
      case EcorePackage.REQUIREMENT_REALISATION__PERCENTAGE:
        setPercentage((Double) newValue);
        return;
      case EcorePackage.REQUIREMENT_REALISATION__DEPENDS_ON:
        getDependsOn().clear();
        getDependsOn().addAll((Collection<? extends SoftwareArtifact>) newValue);
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
      case EcorePackage.REQUIREMENT_REALISATION__REQUIREMENT:
        setRequirement((Requirement) null);
        return;
      case EcorePackage.REQUIREMENT_REALISATION__PERCENTAGE:
        setPercentage(PERCENTAGE_EDEFAULT);
        return;
      case EcorePackage.REQUIREMENT_REALISATION__DEPENDS_ON:
        getDependsOn().clear();
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
      case EcorePackage.REQUIREMENT_REALISATION__REQUIREMENT:
        return getRequirement() != null;
      case EcorePackage.REQUIREMENT_REALISATION__PERCENTAGE:
        return percentage != PERCENTAGE_EDEFAULT;
      case EcorePackage.REQUIREMENT_REALISATION__DEPENDS_ON:
        return dependsOn != null && !dependsOn.isEmpty();
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
    result.append(" (percentage: ");
    result.append(percentage);
    result.append(')');
    return result.toString();
  }
} // RequirementRealisationImpl
