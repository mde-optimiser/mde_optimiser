/** */
package models.tsp.fitness.TSP;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 *
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Region</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>The following features are supported:
 *
 * <ul>
 *   <li>{@link models.tsp.fitness.TSP.Region#getName <em>Name</em>}
 *   <li>{@link models.tsp.fitness.TSP.Region#getCities <em>Cities</em>}
 *   <li>{@link models.tsp.fitness.TSP.Region#getDistances <em>Distances</em>}
 * </ul>
 *
 * @see models.tsp.fitness.TSP.TSPPackage#getRegion()
 * @model
 * @generated
 */
public interface Region extends EObject {
  /**
   * Returns the value of the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Name</em>' attribute.
   * @see #setName(String)
   * @see models.tsp.fitness.TSP.TSPPackage#getRegion_Name()
   * @model required="true"
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link models.tsp.fitness.TSP.Region#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Cities</b></em>' containment reference list. The list contents
   * are of type {@link models.tsp.fitness.TSP.City}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Cities</em>' containment reference list.
   * @see models.tsp.fitness.TSP.TSPPackage#getRegion_Cities()
   * @model containment="true" ordered="false"
   * @generated
   */
  EList<City> getCities();

  /**
   * Returns the value of the '<em><b>Distances</b></em>' containment reference list. The list
   * contents are of type {@link models.tsp.fitness.TSP.Distance}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Distances</em>' containment reference list.
   * @see models.tsp.fitness.TSP.TSPPackage#getRegion_Distances()
   * @model containment="true" ordered="false"
   * @generated
   */
  EList<Distance> getDistances();
} // Region
