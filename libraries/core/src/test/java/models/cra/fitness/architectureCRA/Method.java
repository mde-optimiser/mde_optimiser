/** */
package models.cra.fitness.architectureCRA;

import org.eclipse.emf.common.util.EList;

/**
 *
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Method</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>The following features are supported:
 *
 * <ul>
 *   <li>{@link models.cra.fitness.architectureCRA.Method#getDataDependency <em>Data
 *       Dependency</em>}
 *   <li>{@link models.cra.fitness.architectureCRA.Method#getFunctionalDependency <em>Functional
 *       Dependency</em>}
 * </ul>
 *
 * @see models.cra.fitness.architectureCRA.ArchitectureCRAPackage#getMethod()
 * @model
 * @generated
 */
public interface Method extends Feature {
  /**
   * Returns the value of the '<em><b>Data Dependency</b></em>' reference list. The list contents
   * are of type {@link models.cra.fitness.architectureCRA.Attribute}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Data Dependency</em>' reference list.
   * @see models.cra.fitness.architectureCRA.ArchitectureCRAPackage#getMethod_DataDependency()
   * @model
   * @generated
   */
  EList<Attribute> getDataDependency();

  /**
   * Returns the value of the '<em><b>Functional Dependency</b></em>' reference list. The list
   * contents are of type {@link models.cra.fitness.architectureCRA.Method}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Functional Dependency</em>' reference list.
   * @see models.cra.fitness.architectureCRA.ArchitectureCRAPackage#getMethod_FunctionalDependency()
   * @model
   * @generated
   */
  EList<Method> getFunctionalDependency();
} // Method
