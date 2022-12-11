package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import src.client.Client;
import src.client.ClientInterface;
import src.reservation.ReservationService;
import src.room.RoomService;

public class TestClient {
  public static void main(String[] args) {
		System.out.println("----------------- Start test client/reservation only -----------------");
    launchTest();
	}
  public static void launchTest() {
    System.out.println("----------------- Test client/reservation -----------------");
    // Init array of client
    // TODO (for v3) : place this array in HotelService class (need create it) for manage Hotel chain
    Client[] clients = new Client[2];

    // instanciate Reservation singleton 
    ReservationService reservationService = ReservationService.getInstance();

    // TEST "¤SINGLETON"
    RoomService roomService = RoomService.getInstance();
    

    // Test create a client
    System.out.println("-- Test create a client :");
    clients[0] = new Client("DE CILLIA","Aurélien");
    clients[1] = new Client("Blaszkiewicz","Ewa");

    // TEST "¤TOSTRING OVERRIDE"  
    System.out.println(clients[0]);
    //test client interface
    try {
      // valide
      System.out.println("-- Test client change name :");
      // TEST "¤ENCAPSULATION"
      System.out.println("firstName : " + clients[0].getFirstName());
      clients[0].setFirstName("Nicolas");
      System.out.println("firstName now: " + clients[0].getFirstName());

      // TEST "¤INTERFACE"
      System.out.println("-- Test client change name with interface :");
      ClientInterface clientInterface1 = new Client("DE CILLIA","Aurélien");
      // setFirstName access denied for interface (setFirstName not exist in interface)

      /* clientInterface1.setFirstName("Nicolas"); */

      System.out.println(clientInterface1);
    } catch (Exception e) {
      System.err.println(e);
    }
    
    // Test create a reservations with different constructors
    System.out.println("-- Test create a reservations with different constructors :");
    try {
      reservationService.createReservation(clients[0],"301");
      // Test "¤SURGARGE" of createReservation in reservationService
      // (+ ¤CALL DEFFERENT CONSTRUCTOR of Reservation used in createReservation)
      reservationService.createReservation(clients[0],"302",new SimpleDateFormat("8022-12-02"));
      System.out.println(clients[0]);
    } catch (Exception e) {
      System.err.println(e);
    }

    // Test if already reserved
    try {
      System.out.println("Test if already reserved:");
      reservationService.createReservation(clients[0],"301");
    } catch (Exception e) {
      System.err.println(e);
    }

    // Test if already reserved by another client
    try {
      System.out.println("Test if already reserved by another client:");
      reservationService.createReservation(clients[1],"301");
    } catch (Exception e) {
      System.err.println(e);
    }

    // Check room status after reservation
    try {
      System.out.println("Room Status :");
      System.out.println(roomService.getRoomByName("301"));
    } catch (Exception e) {
      System.err.println(e);
    }

    /* 
     * DAY 1 TONIGHT
     * The maid come at tonight for clean max 10 room.
    */

    // check all reservation for pass is Finish to true
    try {
      reservationService.finishReservation(clients); // TODO (for v2) : need to be place on a cron
    } catch (ParseException e) {
      System.err.println(e);
    }
    System.err.println(roomService.maidCleaning());
    
    /* 
     * DAY 2 :
    */
    System.out.println("Day 2:");
    // Test reserve random 10 room
    for (int index = 1; index <= 10; index++) {
      try {
        System.out.println(reservationService.createRandomReservation(clients[0]));
      } catch (Exception e) {
        System.err.println(e);
      }
    }
    // Test reserve random 1 room with other client
    try {
      System.out.println(reservationService.createRandomReservation(clients[1]));
    } catch (Exception e) {
      System.err.println(e);
    }
    System.out.println("Client1 :");
    System.out.println(clients[0]);
    System.out.println("Client2 :");
    System.out.println(clients[1]);

    System.out.println("all rooms :");
    System.out.println(roomService);

    /* 
     * DAY 2 TONIGHT
     * The maid come at tonight for clean max 10 room.
    */

    try {
      reservationService.finishReservation(clients);
    } catch (ParseException e) {
      System.err.println(e);
    }
    System.out.println(roomService.maidCleaning());
    
    /* 
     * DAY 3 :
    */
    System.out.println("Day 3:");
    System.out.println("all rooms :");
    System.out.println(roomService);
    System.out.println("Client2 Resarvate room already cleaned and another not:");
    try {
      // clean
      System.out.println("Reserve room 100 (need ok):");
      // System.out.println(roomService);
      System.out.println(reservationService.createReservation(clients[1],"100"));

      // not clean (need throw error)
      System.out.println("Reserve room 200 (need send not clean):");
      System.out.println(reservationService.createReservation(clients[1],"200")); 
    } catch (Exception e) {
      System.err.println(e);
    }

    /* 
     * DAY 3 TONIGHT
     * The maid come at tonight for clean max 10 room.
    */

    try {
      reservationService.finishReservation(clients);
    } catch (ParseException e) {
      System.err.println(e);
    }
    System.out.println(roomService.maidCleaning());
     /* 
     * DAY 4 TONIGHT
     * The maid come at tonight for clean max 10 room.
    */

    try {
      reservationService.finishReservation(clients);
    } catch (ParseException e) {
      System.err.println(e);
    }
    System.out.println(roomService.maidCleaning());
  }
}
