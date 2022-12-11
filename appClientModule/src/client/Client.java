package src.client;

import java.util.ArrayList;

import src.reservation.Reservation;

// TODO (for v2) : create reservationHistorical
// TODO (for v3) : out reservations and reservation from client (just use array of UUID)
public class Client implements ClientInterface {
  private String m_lastName;
  private String m_firstName;
  private ArrayList<Reservation> reservations = new ArrayList<Reservation>();

  // --------- Constructor --------- //

  /**
   * ctor
   * @param p_lastName
   * @param p_firstName
   */
  public Client(String p_lastName,String p_firstName){
    this.m_lastName = p_lastName;
    this.m_firstName = p_firstName;
  }
  
  // --------- Override --------- //

  @Override
  public String toString(){
    StringBuilder allReservationsString = new StringBuilder("LastName : ").append(this.m_lastName).append("\n")
    .append("firstName : ").append(this.m_firstName).append("\n")
    .append("List des réservations : [\n");
		reservations.forEach((e) -> allReservationsString.append("[\n").append(e.toString()).append("],\n"));
		allReservationsString.append("]\n");
    return allReservationsString.toString();
  }

  // --------- Getters --------- //

  /* (non-Javadoc)
   * @see src.client.ClientInterface#getLastName()
   */
  @Override
  public String getLastName() {
    return this.m_lastName;
  }



  /* (non-Javadoc)
   * @see src.client.ClientInterface#getFirstName()
   */
  @Override
  public String getFirstName() {
    return this.m_firstName;
  }

  /**
   * @return
   */
  public ArrayList<Reservation> getReservations() {
    return reservations;
  }

  
  // --------- Setters --------- //

  /**
   * Set client lastName
   * @param p_lastName
   */
  public void setLastName(String p_lastName) {
    this.m_lastName = p_lastName;
  }

  /**
   * Set client firstName
   * @param m_firstName
   */
  public void setFirstName(String p_firstName) {
    this.m_firstName = p_firstName;
  }

  /**
   * @param p_reservation
   */
  public void addReservation(Reservation p_reservation) {
    this.reservations.add(p_reservation);
  }
 
}
