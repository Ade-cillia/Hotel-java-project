package src.reservation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import src.client.Client;
import src.room.Room;
import src.room.RoomService;

/**
 * Singleton reservation collection
 */
public class ReservationService {

  private static ReservationService instance = null; 
  // private ArrayList<Reservation> resevations = new ArrayList<Reservation>();

  // --------- Constructors --------- //

  /**
   * ctor
   */
  private ReservationService(){}

  // --------- Singleton instance --------- //

  /**
   * Create him self instance once if not exist (singleton)
   * @return
   */
  public static ReservationService getInstance() {
    if (instance == null){
      instance = new ReservationService();
    }    
    return instance;
  } 

 
  // --------- Reservation managment --------- //

  /**
   * Search first room can be reserved and reserve it
   * @param client
   * @return
   * @throws Exception if no room disponible
   */
  public String createRandomReservation(Client client) throws Exception{
    RoomService roomService = RoomService.getInstance();

    Room room = roomService.getDisponibleRoom(); // throw exception if no room disponible

    room.setIsClean(false);
    room.setIsReserved(true);
    Reservation reservation = new Reservation(room.getRoomName());
    client.addReservation(reservation);
    return "Random reservation has been create, room is " + room.getRoomName();
  }

  /**
   * Create reservation for specified room
   * @param client
   * @param roomNumber
   * @return
   * @throws Exception
   */
  public String createReservation(Client client,String roomNumber) throws Exception{
    RoomService roomService = RoomService.getInstance();
    //if not exist, throw error :
    Room room = roomService.getRoomByName(roomNumber);
    if (room.getIsReserved() ) {
      throw new Exception("Room " + roomNumber + " is already reserved");
    }else if(!room.getIsClean()){
      throw new Exception("Room" + roomNumber + " is not clean");
    }else if(client == null){
      throw new Exception("Client not exist");
    }
    room.setIsClean(false);
    room.setIsReserved(true);
    Reservation reservation = new Reservation(roomNumber);
    client.addReservation(reservation);
    return "Reservation has been create";
  }

  /**
   * Create reservation for specified room AND specified date
   * TODO (for v2) : Actualy nobody can use room if reserved room if his reserved for future date, need better organisation planning for not set dirty and reserved now. 
   * @param client
   * @param roomNumber
   * @param p_reservedDateStart
   * @return
   * @throws Exception
   */
  public String createReservation(Client client,String roomNumber,SimpleDateFormat p_reservedDateStart) throws Exception{
    RoomService roomService = RoomService.getInstance();
    //if not exist, throw error :
    Room room = roomService.getRoomByName(roomNumber);
    if (room.getIsReserved() ) {
      throw new Exception("Room is already reserved");
    }else if(!room.getIsClean()){
      throw new Exception("Room is not clean");
    }else if(client == null){
      throw new Exception("Client not exist");
    }
    room.setIsClean(false);
    room.setIsReserved(true);
    Reservation reservation = new Reservation(roomNumber,p_reservedDateStart);
    client.addReservation(reservation);
    return "Reservation has been create";
  }

  /**
   * TEST TONIGHT, if reservation date < now, finish this reservation 
   * TODO (for v2) : need place reservation finished in historical array and delete from reservation array (in client)
   * @param clients
   * @throws ParseException
   */
  public void finishReservation(Client[] clients) throws ParseException{
    RoomService roomService = RoomService.getInstance();
    for (Client client : clients){
      for (Reservation reservation : client.getReservations()){
        Date dateReservation = new SimpleDateFormat("yyyy-MM-dd").parse(reservation.getReservedDateStart());
        Date dateNow = new SimpleDateFormat("yyyy-MM-dd").parse(new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()));
        // TODO (for v2) : dateReservation.equals(dateNow) is for only dev (after place cron, need to delete "|| dateReservation.equals(dateNow)" in condition)

        if( (dateReservation.after(dateNow) || dateReservation.equals(dateNow)) && !reservation.isFinish() ){
          try {
            Room room = roomService.getRoomByName(reservation.getRoomName());
            room.setIsClean(false);
            room.setIsReserved(false);
            reservation.setFinish(true);
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      }
    }
  }
}
