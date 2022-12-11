package src.room;

import java.util.ArrayList;

/**
 * Singleton room collection
 */
public class RoomService {

  private static RoomService instance = null; 
  private ArrayList<Room> roomList = new ArrayList<Room>();

  /**
   * ctor
   * ( Generate room in roomList )
   */
  private RoomService(){
    for (int stage = 1; stage <= 3; stage++) {
      for (int roomIndex = 0; roomIndex < 10; roomIndex++) {
        int roomName = stage * 100 + roomIndex;
        roomList.add(new Room(Integer.toString(roomName)));
      }
    }
  }

  // --------- Singleton instance --------- //

  /**
   * Create himself once if not exist (singleton)
   * @return
   */
  public static RoomService getInstance() {
    if (instance == null) {
      instance = new RoomService();
    }
    return instance;
  }

  // --------- Override --------- //

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString(){
    StringBuilder allRoomsString = new StringBuilder("List des chambres : [\n");
		roomList.forEach((e) -> allRoomsString.append("[\n").append(e.toString()).append("],\n"));
		allRoomsString.append("]\n");
    return allRoomsString.toString();
  }
  
  // --------- Getters --------- //

  /**
   * @return
   */
  public ArrayList<Room> getRoomList() {
    return roomList;
  }

  // --------- Room managment --------- //

  /**
   * Return Room if clean and not reserved
   * @return
   * @throws Exception
   */
  public Room getDisponibleRoom() throws Exception{
    for (Room room : roomList) {
      if (room.getIsClean() && !room.getIsReserved()) {
        return room;
      }
    }
    throw new Exception("No room disponible");
  }

  /**
   * Return Room object by using room name
   * @param roomName
   * @return
   * @throws Exception
   */
  public Room getRoomByName(String roomName) throws Exception{
    for (Room room : roomList) {
      if (roomName.equals(room.getRoomName())) {
        return room;
      }
    }
    throw new Exception(new StringBuilder("Room number '").append(roomName).append("' not found").toString());
  }

  /**
   * Maid cleaning tonight
   * Clean Max of 10 rooms
   * @return
   */
  public String maidCleaning(){
    Integer roomClean = 0;
    StringBuilder actionStr = new StringBuilder("");
    for (Room room : roomList) {
      if (roomClean==10) {
        actionStr.append("10 Rooms has been cleaned, the maid is now tiredness.\n");
        return actionStr.toString();
      }
      if (!room.getIsClean()) {
        // TODO (for v1.5): clean room after client go out only (for this moment, client can reserve in future date, but room has declared to dirty even if not already in room and redeclared when client go out [room has been clear twice for one reservation])
        roomClean = roomClean + 1;
        actionStr.append("Room ").append(room.getRoomName()).append(" has been cleaned.\n");
        room.setIsClean(true);
      }
    }
    actionStr.append(roomClean).append(" Room(s) has been cleaned, all rooms in Hotel is cleaned.\n");
    return actionStr.toString();
  }
  
}
