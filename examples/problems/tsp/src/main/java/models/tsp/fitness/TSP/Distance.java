/** */
package models.tsp.fitness.TSP;

import org.eclipse.emf.ecore.EObject;

/**
 *
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Distance</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>The following features are supported:
 *
 * <ul>
 *   <li>{@link models.tsp.fitness.TSP.Distance#getDistance <em>Distance</em>}
 *   <li>{@link models.tsp.fitness.TSP.Distance#getFrom <em>From</em>}
 *   <li>{@link models.tsp.fitness.TSP.Distance#getTo <em>To</em>}
 * </ul>
 *
 * @see models.tsp.fitness.TSP.TSPPackage#getDistance()
 * @model
 * @generated
 */
public interface Distance extends EObject {
  /**
   * Returns the value of the '<em><b>Distance</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Distance</em>' attribute.
   * @see #setDistance(Double)
   * @see models.tsp.fitness.TSP.TSPPackage#getDistance_Distance()
   * @model required="true"
   * @generated
   */
  Double getDistance();

  /**
   * Sets the value of the '{@link models.tsp.fitness.TSP.Distance#getDistance <em>Distance</em>}'
   * attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @param value the new value of the '<em>Distance</em>' attribute.
   * @see #getDistance()
   * @generated
   */
  void setDistance(Double value);

  /**
   * Returns the value of the '<em><b>From</b></em>' reference. It is bidirectional and its opposite
   * is '{@link models.tsp.fitness.TSP.City#getOrigin <em>Origin</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>From</em>' reference.
   * @see #setFrom(City)
   * @see models.tsp.fitness.TSP.TSPPackage#getDistance_From()
   * @see models.tsp.fitness.TSP.City#getOrigin
   * @model opposite="origin" required="true"
   * @generated
   */
  City getFrom();

  /**
   * Sets the value of the '{@link models.tsp.fitness.TSP.Distance#getFrom <em>From</em>}'
   * reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @param value the new value of the '<em>From</em>' reference.
   * @see #getFrom()
   * @generated
   */
  void setFrom(City value);

  /**
   * Returns the value of the '<em><b>To</b></em>' reference. It is bidirectional and its opposite
   * is '{@link models.tsp.fitness.TSP.City#getDestination <em>Destination</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>To</em>' reference.
   * @see #setTo(City)
   * @see models.tsp.fitness.TSP.TSPPackage#getDistance_To()
   * @see models.tsp.fitness.TSP.City#getDestination
   * @model opposite="destination" required="true"
   * @generated
   */
  City getTo();

  /**
   * Sets the value of the '{@link models.tsp.fitness.TSP.Distance#getTo <em>To</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @param value the new value of the '<em>To</em>' reference.
   * @see #getTo()
   * @generated
   */
  void setTo(City value);
} // Distance
