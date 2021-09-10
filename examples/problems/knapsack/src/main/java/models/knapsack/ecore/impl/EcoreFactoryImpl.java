/** */
package models.knapsack.ecore.impl;

import models.knapsack.ecore.*;
import org.eclipse.emf.ecore.EClass;
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
      case EcorePackage.PLAN:
        return createPlan();
      case EcorePackage.ITEM:
        return createItem();
      case EcorePackage.KNAPSACK:
        return createKnapsack();
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
  public Plan createPlan() {
    PlanImpl plan = new PlanImpl();
    return plan;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public Item createItem() {
    ItemImpl item = new ItemImpl();
    return item;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public Knapsack createKnapsack() {
    KnapsackImpl knapsack = new KnapsackImpl();
    return knapsack;
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
