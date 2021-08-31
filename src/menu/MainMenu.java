package menu;

import api.HotelResource;
import model.Customer;
import model.IRoom;
import model.Reservation;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;

public class MainMenu {
    public void getStart() {
        boolean keepRunning = true;
        while (keepRunning) {
            System.out.println("Main Menu\n---------------------------------------");
            System.out.println("1. Find and reserve a room\n" +
                    "2. See my reservation\n" +
                    "3. Create an account\n" +
                    "4. Admin\n" +
                    "5. Exit\n");
            try {
                Scanner scanner = new Scanner(System.in);
                int selection = Integer.parseInt(scanner.next());
                switch (selection) {
                    case 1:
                        System.out.println("Find and reserve a room");
                        reserveRoomMenu();
                        break;
                    case 2:
                        System.out.println("See my reservation");
                        customerReservationMenu();
                        break;
                    case 3:
                        System.out.println("Create an account");
                        createAccountMenu();
                        break;
                    case 4:
                        System.out.println("Admin");
                        AdminMenu adminMenu = new AdminMenu();
                        adminMenu.getStart(scanner);
                        break;
                    case 5:
                        System.out.println("Exit");
                        keepRunning = false;
                        break;
                    default:
                        System.out.println("Error, please input an interger between 1 to 5");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please input Integer between 1 to 5");
            }

        }
    }

    public void customerReservationMenu(){
        boolean keepRunning = true;
        String email = "";
        while (keepRunning){
            try {
                Scanner customerReservationScanner = new Scanner(System.in);
                System.out.println("Please enter your email");
                email = customerReservationScanner.nextLine();
                String emailRegex = "^(.+)@(.+).(.+)$";
                Pattern pattern = Pattern.compile(emailRegex);
                if (!pattern.matcher(email).matches()) {
                    throw new IllegalArgumentException("Error, Invalid email");
                } else {
                    keepRunning = false;
                }
            }catch (Exception e){
                System.out.println("Please enter a valid email");
            }
        }
        Collection<Reservation> customerReservations = HotelResource.getCustomersReservations(email);
        if(customerReservations.size() == 0){
            System.out.println("You have no reservation now");
        }
        else{
            for (Reservation reservation : customerReservations){
                System.out.println(reservation);
            }
        }
    }


    public void createAccountMenu () {
        boolean keepRunning = true;
        while (keepRunning){
            try {
                Scanner createAccountScanner = new Scanner(System.in);
                System.out.println("Please enter your email:");
                String email = createAccountScanner.nextLine();
                System.out.println("Please enter your first name:");
                String firstName = createAccountScanner.nextLine();
                System.out.println("Please enter your last name:");
                String lastName = createAccountScanner.nextLine();
                HotelResource.createACustomer(email,firstName,lastName);
                keepRunning = false;
            } catch (Exception e){
                System.out.println("Invalid email format. please create account again");
            }
        }
    }

    public void reserveRoomMenu(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        simpleDateFormat.setLenient(false);
        boolean keepRunning = true;
        while (keepRunning){
            try {
                Scanner reserveRoomScanner = new Scanner(System.in);
                System.out.println("Enter CheckIn Date mm/dd/yyyy exmaple: 02/01/2020");
                Date checkInDate = simpleDateFormat.parse(reserveRoomScanner.nextLine());
                System.out.println("Enter CheckOut Date mm/dd/yyyy exmaple: 02/01/2020");
                Date checkOutDate = simpleDateFormat.parse(reserveRoomScanner.nextLine());
                if (checkInDate.compareTo(checkOutDate) >= 0){
                    System.out.println("Error, the check in date should not before or the same with the check out date");
                }
                else {
                    Collection<IRoom> roomAvailable = HotelResource.findARoom(checkInDate,checkOutDate);
                    if (roomAvailable.size() == 0){
                        System.out.println("There is no available room at this moment, here is some suggestion after a week");
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(checkInDate);
                        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + 7);
                        checkInDate = calendar.getTime();
                        calendar.setTime(checkOutDate);
                        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + 7);
                        checkOutDate = calendar.getTime();
                        roomAvailable = HotelResource.findARoom(checkInDate,checkOutDate);
                        if (roomAvailable.size() == 0){
                            System.out.println("There is no available room after a week, please try later");
                        }
                        else {
                            bookRoomMemu(roomAvailable,checkInDate,checkOutDate);
                        }
                    }
                    else {
                        bookRoomMemu(roomAvailable,checkInDate,checkOutDate);
                    }
                    keepRunning = false;
                }
            } catch (Exception e){
                System.out.println("Error, the date is invalid, please enter again");
            }
        }
    }

    public void bookRoomMemu(Collection<IRoom> roomAvailable, Date checkInDate, Date checkOutDate){
        for(IRoom room : roomAvailable){
            System.out.println("Here is the list of available room: \n");
            System.out.println(room);
        }
        System.out.println("Would you like to book a room(y/n)");
        Scanner bookRoomScanner = new Scanner(System.in);
        boolean keepRunning = true;
        while (keepRunning){
            String ans = bookRoomScanner.nextLine();
            if (ans.equalsIgnoreCase("y")){
                System.out.println("Do you have an account with us? {y/n}");
                while (keepRunning) {
                    ans = bookRoomScanner.nextLine();
                    if (ans.equalsIgnoreCase("y")) {
                        boolean keepRunning2 = true;
                        String email = "";
                        while (keepRunning2) {
                            try {
                                System.out.println("Please enter into your email");
                                email = bookRoomScanner.nextLine();
                                // check email
                                String emailRegex = "^(.+)@(.+).(.+)$";
                                Pattern pattern = Pattern.compile(emailRegex);
                                if (!pattern.matcher(email).matches()) {
                                    throw new IllegalArgumentException("Error, Invalid email");
                                } else {
                                    keepRunning2 = false;
                                }
                            } catch (Exception e) {
                                System.out.println("Invalid email format, please enter again");
                            }
                        }
                        Customer customer = HotelResource.getCustomer(email);
                        if (customer == null) {
                            System.out.println("Your email is not registered, please create an account first");
                            return;
                        } else {
                            boolean keepRunning3 = true;
                            while (keepRunning3) {
                                System.out.println("Please enter the room number you want to book: ");
                                for (IRoom room : roomAvailable) {
                                    System.out.println("Here is the list of available room: \n");
                                    System.out.println(room);
                                }
                                String roomID = bookRoomScanner.nextLine();
                                IRoom roomReserved = null;
                                for (IRoom room : roomAvailable) {
                                    if (room.getRoomNumber().equals(roomID)) {
                                        roomReserved = room;
                                    }
                                }
                                if (roomReserved == null) {
                                    System.out.println("Please enter a room number list above");
                                } else {
                                    Reservation reservation = HotelResource.bookARoom(customer, roomReserved, checkInDate, checkOutDate);
                                    return;
                                }
                            }
                        }

                    } else if (ans.equalsIgnoreCase("n")) {
                        System.out.println("You should create an account first");
                        return;
                    } else {
                        System.out.println("Do you have an account with us? Please enter (y/n)");
                    }
                }
            }
            else if (ans.equalsIgnoreCase("n")){
                return;
            }
            else {
                System.out.println("Would you like to book a room? Please enter (y/n)");
            }
        }
    }

}
