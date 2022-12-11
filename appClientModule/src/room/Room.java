package src.room;

public class Room implements RoomInterface {
  private String m_roomName;
  private boolean m_isClean = true;
  private boolean m_isReserved = false;

  /**
   * ctor
   */
  Room(String p_roomName){
    this.m_roomName = p_roomName;
  }

  // --------- Override --------- //
  
  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString(){
    StringBuilder allRoomsString = new StringBuilder("Room name : ").append(this.m_roomName).append("\n")
    .append("Is clean : ").append(this.m_isClean).append("\n")
		.append("Is reserved : ").append(this.m_isReserved).append("\n");
    return allRoomsString.toString();
  }

  // --------- Getters --------- //

  /* (non-Javadoc)
   * @see src.room.RoomInterface#getRoomName()
   */
  @Override
  public String getRoomName() {
    return this.m_roomName;
  }

  /* (non-Javadoc)
   * @see src.room.RoomInterface#getIsClean()
   */
  @Override
  public boolean getIsClean() {
    return this.m_isClean;
  }

  /* (non-Javadoc)
   * @see src.room.RoomInterface#getIsReserved()
   */
  @Override
  public boolean getIsReserved() {
    return this.m_isReserved;
  }


  // --------- Setters --------- //

  /**
   * @param p_roomName
   */
  public void setRoomName(String p_roomName) {
    this.m_roomName = p_roomName;
  }

  /**
   * @param p_isClean
   */
  public void setIsClean(boolean p_isClean) {
    this.m_isClean = p_isClean;
  }

  /**
   * @param p_isReserved
   */
  public void setIsReserved(boolean p_isReserved) {
    this.m_isReserved = p_isReserved;
  }

  
}
