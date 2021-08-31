package menu;

import api.AdminResource;
import model.*;

import javax.crypto.Cipher;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class AdminMenu {
    public void getStart(Scanner scanner){
        boolean keepRunning = true;
        while(keepRunning){
            System.out.println("Admin Menu\n---------------------------------------");
            System.out.println("1. See all Customers\n"+
                    "2. See all Rooms\n" +
                    "3. See all Reservations\n" +
                    "4. Add a Room\n" +
                    "5. Back to Main Menu");
            try {
                int selection = Integer.parseInt(scanner.next());
                switch (selection){
                    case 1:
                        System.out.println("See all Customers");
                        Collection<Customer> customers = AdminResource.getAllCustomers();
                        for(Customer customer : customers){
                            System.out.println(customer);
                        }
                        break;
                    case 2:
                        System.out.println("See all Rooms");
                        Collection<IRoom> roomList = AdminResource.getAllRooms();
                        for(IRoom r : roomList){
                            System.out.println(r);
                        }
                        break;
                    case 3:
                        System.out.println("See all Reservations");
                        AdminResource.displayAllReservations();
                        break;
                    case 4:
                        System.out.println("Add a Room");
                        addRoomMenu();
                        break;
                    case 5:
                        System.out.println("Back to Main Menu");
                        keepRunning = false;
                        break;
                    default:
                        System.out.println("Place enter in an Integer between 1 to 5");
                }
            } catch (Exception ex){
                System.out.println("Invalid input. Please input Integer between 1 to 5");
            }
        }

    }

    public void addRoomMenu(){
        boolean keepRunning = true;
        while(keepRunning){
            try {
                Scanner addRoomScanner = new Scanner(System.in);
                System.out.println("Please enter room Number: ");
                String roomNumber = addRoomScanner.nextLine();
                System.out.println("Please enter room price: ");
                Double roomPrice = Double.parseDouble(addRoomScanner.nextLine());
                System.out.println("Please enter type (SINGLE/DOUBLE): ");
                RoomType roomType = RoomType.valueOf(addRoomScanner.nextLine());

                List<IRoom> roomList = new ArrayList<>();
                IRoom newRoom = new Room(roomNumber,roomPrice,roomType);
                roomList.add(newRoom);
                AdminResource.addRoom(roomList);
                keepRunning = false;
            } catch (Exception e){
                System.out.println("Invalid Room type (should be SINGLE/DOUBLE), please add a room again");
            }
        }
    }

}
