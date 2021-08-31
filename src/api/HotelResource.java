package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.Date;
import java.util.Objects;

public class HotelResource {
    static CustomerService customerService = CustomerService.getInstance();
    static ReservationService reservationService = ReservationService.getInstance();
    private static HotelResource hotelResource;
    private HotelResource(){}

    public static HotelResource getInstance(){
        if(Objects.isNull(hotelResource)){
            hotelResource = new HotelResource();
        }
        return hotelResource;
    }

    public static Customer getCustomer(String email){
        return customerService.getCustomer(email);
    }
    public static void createACustomer(String email, String firstName, String lastName){
        customerService.addCustomer(email,firstName,lastName);
    }
    public IRoom getRoom(String rootNumber){
        return null;
    }

    public static Reservation bookARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate){
        return reservationService.reserveARoom(customer,room,checkInDate,checkOutDate);
    }
    public Collection<Reservation> getCustomersReservations(String customerEmail){
        return null;
    }
    public static Collection<IRoom> findARoom(Date checkIn, Date checkOut){
        return reservationService.findRooms(checkIn,checkOut);
    }
}
