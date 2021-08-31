package api;

import model.Customer;
import model.IRoom;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class AdminResource {
    static ReservationService reservationService = ReservationService.getInstance();
    static CustomerService customerService = CustomerService.getInstance();
    private static AdminResource adminResource;
    private AdminResource(){}
    public static AdminResource getInstance(){
        if(Objects.isNull(adminResource)){
            adminResource = new AdminResource();
        }
        return adminResource;
    }

    public Customer getCustomer(String email){
        return null;
    }

    public static void addRoom(List<IRoom> rooms){
        for(IRoom room : rooms){
            reservationService.addRoom(room);
        }
    }
    public static Collection<IRoom> getAllRooms(){
        return reservationService.roomList;
    }
    public static Collection<Customer> getAllCustomers(){
        return customerService.customers;
    }
    public static void displayAllReservations(){
        reservationService.printAllReservation();
    }
}
