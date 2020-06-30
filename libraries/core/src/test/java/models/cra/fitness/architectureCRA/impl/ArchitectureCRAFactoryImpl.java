/** */
package models.cra.fitness.architectureCRA.impl;

import models.cra.fitness.architectureCRA.*;
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
public class ArchitectureCRAFactoryImpl extends EFactoryImpl implements ArchitectureCRAFactory {
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public static ArchitectureCRAFactory init() {
    try {
      ArchitectureCRAFactory theArchitectureCRAFactory =
          (ArchitectureCRAFactory)
              EPackage.Registry.INSTANCE.getEFactory(ArchitectureCRAPackage.eNS_URI);
      if (theArchitectureCRAFactory != null) {
        return theArchitectureCRAFactory;
      }
    } catch (Exception exception) {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new ArchitectureCRAFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public ArchitectureCRAFactoryImpl() {
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
      case ArchitectureCRAPackage.CLASS_MODEL:
        return createClassModel();
      case ArchitectureCRAPackage.CLASS:
        return createClass();
      case ArchitectureCRAPackage.ATTRIBUTE:
        return createAttribute();
      case ArchitectureCRAPackage.METHOD:
        return createMethod();
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
  public ClassModel createClassModel() {
    ClassModelImpl classModel = new ClassModelImpl();
    return classModel;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public models.cra.fitness.architectureCRA.Class createClass() {
    ClassImpl class_ = new ClassImpl();
    return class_;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public Attribute createAttribute() {
    AttributeImpl attribute = new AttributeImpl();
    return attribute;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public Method createMethod() {
    MethodImpl method = new MethodImpl();
    return method;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public ArchitectureCRAPackage getArchitectureCRAPackage() {
    return (ArchitectureCRAPackage) getEPackage();
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
  public static ArchitectureCRAPackage getPackage() {
    return ArchitectureCRAPackage.eINSTANCE;
  }
} // ArchitectureCRAFactoryImpl
