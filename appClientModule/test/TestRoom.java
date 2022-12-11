package test;

import src.room.RoomService;

public abstract class TestRoom {
  public static void main(String[] args) {
		System.out.println("----------------- Start test Room only -----------------");
    launchTest();
	}
  public static void launchTest() {
    System.out.println("----------------- Test Room -----------------");

    // roomService singleton
    RoomService roomService = RoomService.getInstance();
    System.out.println(roomService);

    // Test get Room
    try {
      System.out.println(roomService.getRoomByName("300"));
    } catch (Exception e) {
      System.err.println(e);
    }

    // Test get Room when he not exist
    try {
      roomService.getRoomByName("400");
    } catch (Exception e) {
      System.err.println(e);
    }

    // Check room status
    try {
      System.out.println("Room Status :");
      System.out.println(roomService.getRoomByName("301"));
    } catch (Exception e) {
      System.err.println(e);
    }
  }
}
