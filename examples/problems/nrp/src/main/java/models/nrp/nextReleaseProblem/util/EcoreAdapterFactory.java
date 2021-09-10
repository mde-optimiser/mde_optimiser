/** */
package models.nrp.nextReleaseProblem.util;

import models.nrp.nextReleaseProblem.*;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;

/**
 *
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model. It provides an adapter <code>createXXX</code> method
 * for each class of the model.
 * <!-- end-user-doc -->
 *
 * @see models.nrp.nextReleaseProblem.EcorePackage
 * @generated
 */
public class EcoreAdapterFactory extends AdapterFactoryImpl {
  /**
   * The cached model package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  protected static EcorePackage modelPackage;

  /**
   * Creates an instance of the adapter factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public EcoreAdapterFactory() {
    if (modelPackage == null) {
      modelPackage = EcorePackage.eINSTANCE;
    }
  }

  /**
   * Returns whether this factory is applicable for the type of the object.
   * <!-- begin-user-doc -->
   * This implementation returns <code>true</code> if the object is either the model's package or is
   * an instance object of the model.
   * <!-- end-user-doc -->
   *
   * @return whether this factory is applicable for the type of the object.
   * @generated
   */
  @Override
  public boolean isFactoryForType(Object object) {
    if (object == modelPackage) {
      return true;
    }
    if (object instanceof EObject) {
      return ((EObject) object).eClass().getEPackage() == modelPackage;
    }
    return false;
  }

  /**
   * The switch that delegates to the <code>createXXX</code> methods.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  protected EcoreSwitch<Adapter> modelSwitch =
      new EcoreSwitch<Adapter>() {
        @Override
        public Adapter caseNRP(NRP object) {
          return createNRPAdapter();
        }

        @Override
        public Adapter caseCustomer(Customer object) {
          return createCustomerAdapter();
        }

        @Override
        public Adapter caseCost(Cost object) {
          return createCostAdapter();
        }

        @Override
        public Adapter caseSoftwareArtifact(SoftwareArtifact object) {
          return createSoftwareArtifactAdapter();
        }

        @Override
        public Adapter caseRequirement(Requirement object) {
          return createRequirementAdapter();
        }

        @Override
        public Adapter caseRequirementRealisation(RequirementRealisation object) {
          return createRequirementRealisationAdapter();
        }

        @Override
        public Adapter caseSolution(Solution object) {
          return createSolutionAdapter();
        }

        @Override
        public Adapter caseValuation(Valuation object) {
          return createValuationAdapter();
        }

        @Override
        public Adapter defaultCase(EObject object) {
          return createEObjectAdapter();
        }
      };

  /**
   * Creates an adapter for the <code>target</code>.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @param target the object to adapt.
   * @return the adapter for the <code>target</code>.
   * @generated
   */
  @Override
  public Adapter createAdapter(Notifier target) {
    return modelSwitch.doSwitch((EObject) target);
  }

  /**
   * Creates a new adapter for an object of class '{@link models.nrp.nextReleaseProblem.NRP
   * <em>NRP</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases; it's useful to
   * ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   *
   * @return the new adapter.
   * @see models.nrp.nextReleaseProblem.NRP
   * @generated
   */
  public Adapter createNRPAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link models.nrp.nextReleaseProblem.Customer
   * <em>Customer</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases; it's useful to
   * ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   *
   * @return the new adapter.
   * @see models.nrp.nextReleaseProblem.Customer
   * @generated
   */
  public Adapter createCustomerAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link models.nrp.nextReleaseProblem.Cost
   * <em>Cost</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases; it's useful to
   * ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   *
   * @return the new adapter.
   * @see models.nrp.nextReleaseProblem.Cost
   * @generated
   */
  public Adapter createCostAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link
   * models.nrp.nextReleaseProblem.SoftwareArtifact <em>Software Artifact</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases; it's useful to
   * ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   *
   * @return the new adapter.
   * @see models.nrp.nextReleaseProblem.SoftwareArtifact
   * @generated
   */
  public Adapter createSoftwareArtifactAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link models.nrp.nextReleaseProblem.Requirement
   * <em>Requirement</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases; it's useful to
   * ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   *
   * @return the new adapter.
   * @see models.nrp.nextReleaseProblem.Requirement
   * @generated
   */
  public Adapter createRequirementAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link
   * models.nrp.nextReleaseProblem.RequirementRealisation <em>Requirement Realisation</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases; it's useful to
   * ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   *
   * @return the new adapter.
   * @see models.nrp.nextReleaseProblem.RequirementRealisation
   * @generated
   */
  public Adapter createRequirementRealisationAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link models.nrp.nextReleaseProblem.Solution
   * <em>Solution</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases; it's useful to
   * ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   *
   * @return the new adapter.
   * @see models.nrp.nextReleaseProblem.Solution
   * @generated
   */
  public Adapter createSolutionAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link models.nrp.nextReleaseProblem.Valuation
   * <em>Valuation</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases; it's useful to
   * ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   *
   * @return the new adapter.
   * @see models.nrp.nextReleaseProblem.Valuation
   * @generated
   */
  public Adapter createValuationAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for the default case.
   * <!-- begin-user-doc -->
   * This default implementation returns null.
   * <!-- end-user-doc -->
   *
   * @return the new adapter.
   * @generated
   */
  public Adapter createEObjectAdapter() {
    return null;
  }
} // EcoreAdapterFactory
