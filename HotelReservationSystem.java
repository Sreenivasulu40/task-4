import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Room {
    private int roomNumber;
    private String category;
    private double price;
    private boolean available;

    public Room(int roomNumber, String category, double price) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.price = price;
        this.available = true;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void bookRoom() {
        available = false;
    }

    public void releaseRoom() {
        available = true;
    }
}

class Reservation {
    private int reservationNumber;
    private String guestName;
    private Room bookedRoom;

    public Reservation(int reservationNumber, String guestName, Room bookedRoom) {
        this.reservationNumber = reservationNumber;
        this.guestName = guestName;
        this.bookedRoom = bookedRoom;
    }

    public int getReservationNumber() {
        return reservationNumber;
    }

    public String getGuestName() {
        return guestName;
    }

    public Room getBookedRoom() {
        return bookedRoom;
    }
}

class Hotel {
    private List<Room> rooms;
    private List<Reservation> reservations;
    private int reservationCounter;

    public Hotel() {
        rooms = new ArrayList<>();
        reservations = new ArrayList<>();
        reservationCounter = 1;

        // Initialize rooms
        rooms.add(new Room(101, "Standard", 100.0));
        rooms.add(new Room(102, "Deluxe", 150.0));
        rooms.add(new Room(103, "Suite", 200.0));
    }

    public void displayAvailableRooms() {
        System.out.println("Available Rooms:");
        for (Room room : rooms) {
            if (room.isAvailable()) {
                System.out.println("Room " + room.getRoomNumber() + " - " + room.getCategory() +
                        " - $" + room.getPrice() + " per night");
            }
        }
    }

    public Reservation makeReservation(String guestName, int roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber && room.isAvailable()) {
                room.bookRoom();
                Reservation reservation = new Reservation(reservationCounter++, guestName, room);
                reservations.add(reservation);
                return reservation;
            }
        }
        return null; // Room not available or does not exist
    }

    public void displayReservationDetails(int reservationNumber) {
        for (Reservation reservation : reservations) {
            if (reservation.getReservationNumber() == reservationNumber) {
                System.out.println("Reservation Details:");
                System.out.println("Reservation Number: " + reservation.getReservationNumber());
                System.out.println("Guest Name: " + reservation.getGuestName());
                System.out.println("Room Number: " + reservation.getBookedRoom().getRoomNumber());
                System.out.println("Room Category: " + reservation.getBookedRoom().getCategory());
                System.out.println("Room Price: $" + reservation.getBookedRoom().getPrice() + " per night");
                return;
            }
        }
        System.out.println("Reservation not found.");
    }
}

public class HotelReservationSystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Hotel hotel = new Hotel();

        while (true) {
            System.out.println("\nHotel Reservation System");
            System.out.println("1. Display Available Rooms");
            System.out.println("2. Make Reservation");
            System.out.println("3. View Reservation Details");
            System.out.println("4. Exit");

            System.out.print("Enter your choice (1-4): ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    hotel.displayAvailableRooms();
                    break;
                case 2:
                    System.out.print("Enter your name: ");
                    String guestName = scanner.next();
                    hotel.displayAvailableRooms();
                    System.out.print("Enter the room number you want to reserve: ");
                    int roomNumber = scanner.nextInt();
                    Reservation reservation = hotel.makeReservation(guestName, roomNumber);
                    if (reservation != null) {
                        System.out.println("Reservation successful! Your reservation number is: " +
                                reservation.getReservationNumber());
                    } else {
                        System.out.println("Failed to make reservation. The selected room is not available.");
                    }
                    break;
                case 3:
                    System.out.print("Enter your reservation number: ");
                    int reservationNumber = scanner.nextInt();
                    hotel.displayReservationDetails(reservationNumber);
                    break;
                case 4:
                    System.out.println("Thank you for using the Hotel Reservation System!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            }
        }
    }
}
