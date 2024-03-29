/** */
package models.cra.fitness.architectureCRA.util;

import models.cra.fitness.architectureCRA.*;
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
 * @see models.cra.fitness.architectureCRA.ArchitectureCRAPackage
 * @generated
 */
public class ArchitectureCRAAdapterFactory extends AdapterFactoryImpl {
  /**
   * The cached model package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  protected static ArchitectureCRAPackage modelPackage;

  /**
   * Creates an instance of the adapter factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public ArchitectureCRAAdapterFactory() {
    if (modelPackage == null) {
      modelPackage = ArchitectureCRAPackage.eINSTANCE;
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
  protected ArchitectureCRASwitch<Adapter> modelSwitch =
      new ArchitectureCRASwitch<Adapter>() {
        @Override
        public Adapter caseClassModel(ClassModel object) {
          return createClassModelAdapter();
        }

        @Override
        public Adapter caseClass(models.cra.fitness.architectureCRA.Class object) {
          return createClassAdapter();
        }

        @Override
        public Adapter caseAttribute(Attribute object) {
          return createAttributeAdapter();
        }

        @Override
        public Adapter caseMethod(Method object) {
          return createMethodAdapter();
        }

        @Override
        public Adapter caseFeature(Feature object) {
          return createFeatureAdapter();
        }

        @Override
        public Adapter caseNamedElement(NamedElement object) {
          return createNamedElementAdapter();
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
   * Creates a new adapter for an object of class '{@link
   * models.cra.fitness.architectureCRA.ClassModel <em>Class Model</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases; it's useful to
   * ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   *
   * @return the new adapter.
   * @see models.cra.fitness.architectureCRA.ClassModel
   * @generated
   */
  public Adapter createClassModelAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link models.cra.fitness.architectureCRA.Class
   * <em>Class</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases; it's useful to
   * ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   *
   * @return the new adapter.
   * @see models.cra.fitness.architectureCRA.Class
   * @generated
   */
  public Adapter createClassAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link
   * models.cra.fitness.architectureCRA.Attribute <em>Attribute</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases; it's useful to
   * ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   *
   * @return the new adapter.
   * @see models.cra.fitness.architectureCRA.Attribute
   * @generated
   */
  public Adapter createAttributeAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link models.cra.fitness.architectureCRA.Method
   * <em>Method</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases; it's useful to
   * ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   *
   * @return the new adapter.
   * @see models.cra.fitness.architectureCRA.Method
   * @generated
   */
  public Adapter createMethodAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link models.cra.fitness.architectureCRA.Feature
   * <em>Feature</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases; it's useful to
   * ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   *
   * @return the new adapter.
   * @see models.cra.fitness.architectureCRA.Feature
   * @generated
   */
  public Adapter createFeatureAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link
   * models.cra.fitness.architectureCRA.NamedElement <em>Named Element</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases; it's useful to
   * ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   *
   * @return the new adapter.
   * @see models.cra.fitness.architectureCRA.NamedElement
   * @generated
   */
  public Adapter createNamedElementAdapter() {
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
} // ArchitectureCRAAdapterFactory
