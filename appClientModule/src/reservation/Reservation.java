package src.reservation;


import java.text.SimpleDateFormat;
import java.util.Calendar;

// TODO : use m_numberDaysReserved
public class Reservation {
  private SimpleDateFormat m_reservedDateStart = new SimpleDateFormat("yyyy-MM-dd");
  private int m_numberDaysReserved = 1; // actualy not used
  private boolean m_finish = false; // (room reopen)
  private String m_roomName;

  // --------- Constructors --------- //

  /**
   * ctor
   * @param p_roomName
   * @throws Exception
   */
  public Reservation(String p_roomName) throws Exception{
    this.m_roomName = p_roomName;
  }

  /**
   * ctor
   * @param p_roomName
   * @param p_reservedDateStart
   */
  public Reservation(String p_roomName,SimpleDateFormat p_reservedDateStart){
    this.m_roomName = p_roomName;
    this.m_reservedDateStart = p_reservedDateStart;
  }

  /**
   * ctor
   * @param p_roomName
   * @param p_reservedDateStart
   * @param p_numberDaysReserved
   */
  public Reservation(String p_roomName,SimpleDateFormat p_reservedDateStart,int p_numberDaysReserved){
    this.m_roomName = p_roomName;
    this.m_reservedDateStart = p_reservedDateStart;
    this.m_numberDaysReserved = p_numberDaysReserved;
  }

  // --------- Override --------- //

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString(){
    StringBuilder allReservationsString = new StringBuilder("reservedDateStart : ").append(getReservedDateStart()).append("\n")
    .append("numberDaysReserved : ").append(this.m_numberDaysReserved).append("\n")
    .append("finish : ").append(this.m_finish).append("\n")
    .append("Room Number : ").append(this.m_roomName).append("\n");
    return allReservationsString.toString();
  }

  // --------- Getters --------- //

  /**
   * @return
   */
  public boolean isFinish() {
    return this.m_finish;
  }

  /**
   * @return
   */
  public String getReservedDateStart() {
    return this.m_reservedDateStart.format(Calendar.getInstance().getTime());
  }

  /**
   * @return
   */
  public int getNumberDaysReserved() {
    return this.m_numberDaysReserved;
  }

  /**
   * @return
   */
  public String getRoomName() {
    return m_roomName;
  }

  // --------- Setters --------- //

  /**
   * @param p_reservedDate
   */
  public void getReservedDateStart(SimpleDateFormat p_reservedDate) {
    this.m_reservedDateStart = p_reservedDate;
  }

  /**
   * @param p_finish
   */
  public void setFinish(boolean p_finish) {
    this.m_finish = p_finish;
  }

  /**
   * @param p_numberDaysReserved
   */
  public void setNumberDaysReserved(int p_numberDaysReserved) {
    this.m_numberDaysReserved = p_numberDaysReserved;
  }
}
