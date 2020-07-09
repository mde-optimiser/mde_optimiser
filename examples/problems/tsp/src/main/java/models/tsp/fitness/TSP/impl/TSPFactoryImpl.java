/** */
package models.tsp.fitness.TSP.impl;

import models.tsp.fitness.TSP.*;
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
public class TSPFactoryImpl extends EFactoryImpl implements TSPFactory {
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public static TSPFactory init() {
    try {
      TSPFactory theTSPFactory =
          (TSPFactory) EPackage.Registry.INSTANCE.getEFactory(TSPPackage.eNS_URI);
      if (theTSPFactory != null) {
        return theTSPFactory;
      }
    } catch (Exception exception) {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new TSPFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public TSPFactoryImpl() {
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
      case TSPPackage.REGION:
        return createRegion();
      case TSPPackage.CITY:
        return createCity();
      case TSPPackage.DISTANCE:
        return createDistance();
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
  public Region createRegion() {
    RegionImpl region = new RegionImpl();
    return region;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public City createCity() {
    CityImpl city = new CityImpl();
    return city;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public Distance createDistance() {
    DistanceImpl distance = new DistanceImpl();
    return distance;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public TSPPackage getTSPPackage() {
    return (TSPPackage) getEPackage();
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
  public static TSPPackage getPackage() {
    return TSPPackage.eINSTANCE;
  }
} // TSPFactoryImpl
