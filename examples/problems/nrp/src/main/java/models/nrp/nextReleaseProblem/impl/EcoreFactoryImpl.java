/** */
package models.nrp.nextReleaseProblem.impl;

import models.nrp.nextReleaseProblem.*;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 *
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class EcoreFactoryImpl extends EFactoryImpl implements EcoreFactory {
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public static EcoreFactory init() {
    try {
      EcoreFactory theEcoreFactory =
          (EcoreFactory) EPackage.Registry.INSTANCE.getEFactory(EcorePackage.eNS_URI);
      if (theEcoreFactory != null) {
        return theEcoreFactory;
      }
    } catch (Exception exception) {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new EcoreFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public EcoreFactoryImpl() {
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
  public EObject create(EClass eClass) {
    switch (eClass.getClassifierID()) {
      case EcorePackage.NRP:
        return createNRP();
      case EcorePackage.CUSTOMER:
        return createCustomer();
      case EcorePackage.COST:
        return createCost();
      case EcorePackage.SOFTWARE_ARTIFACT:
        return createSoftwareArtifact();
      case EcorePackage.REQUIREMENT:
        return createRequirement();
      case EcorePackage.REQUIREMENT_REALISATION:
        return createRequirementRealisation();
      case EcorePackage.SOLUTION:
        return createSolution();
      case EcorePackage.VALUATION:
        return createValuation();
      default:
        throw new IllegalArgumentException(
            "The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public Object createFromString(EDataType eDataType, String initialValue) {
    switch (eDataType.getClassifierID()) {
      case EcorePackage.COST_TYPE:
        return createCostTypeFromString(eDataType, initialValue);
      default:
        throw new IllegalArgumentException(
            "The datatype '" + eDataType.getName() + "' is not a valid classifier");
    }
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public String convertToString(EDataType eDataType, Object instanceValue) {
    switch (eDataType.getClassifierID()) {
      case EcorePackage.COST_TYPE:
        return convertCostTypeToString(eDataType, instanceValue);
      default:
        throw new IllegalArgumentException(
            "The datatype '" + eDataType.getName() + "' is not a valid classifier");
    }
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public NRP createNRP() {
    NRPImpl nrp = new NRPImpl();
    return nrp;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public Customer createCustomer() {
    CustomerImpl customer = new CustomerImpl();
    return customer;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public Cost createCost() {
    CostImpl cost = new CostImpl();
    return cost;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public SoftwareArtifact createSoftwareArtifact() {
    SoftwareArtifactImpl softwareArtifact = new SoftwareArtifactImpl();
    return softwareArtifact;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public Requirement createRequirement() {
    RequirementImpl requirement = new RequirementImpl();
    return requirement;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public RequirementRealisation createRequirementRealisation() {
    RequirementRealisationImpl requirementRealisation = new RequirementRealisationImpl();
    return requirementRealisation;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public Solution createSolution() {
    SolutionImpl solution = new SolutionImpl();
    return solution;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public Valuation createValuation() {
    ValuationImpl valuation = new ValuationImpl();
    return valuation;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public CostType createCostTypeFromString(EDataType eDataType, String initialValue) {
    CostType result = CostType.get(initialValue);
    if (result == null)
      throw new IllegalArgumentException(
          "The value '"
              + initialValue
              + "' is not a valid enumerator of '"
              + eDataType.getName()
              + "'");
    return result;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public String convertCostTypeToString(EDataType eDataType, Object instanceValue) {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public EcorePackage getEcorePackage() {
    return (EcorePackage) getEPackage();
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @deprecated
   * @generated
   */
  @Deprecated
  public static EcorePackage getPackage() {
    return EcorePackage.eINSTANCE;
  }
} // EcoreFactoryImpl
