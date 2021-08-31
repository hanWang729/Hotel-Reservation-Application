package api;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.Collection;
import java.util.Date;
import java.util.Objects;

public class HotelResource {
    private static HotelResource hotelResource;
    private HotelResource(){}

    public static HotelResource getInstance(){
        if(Objects.isNull(hotelResource)){
            hotelResource = new HotelResource();
        }
        return hotelResource;
    }

    public static Customer getCustomer(String email){
        return null;
    }
    public void createACustomer(String email, String firstName, String lastName){

    }
    public IRoom getRoom(String rootNumber){
        return null;
    }
    public Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate){
        return null;
    }
    public Collection<Reservation> getCustomersReservations(String customerEmail){
        return null;
    }
    public Collection<IRoom> findARoom(Date checkIn, Date checkOut){
        return null;
    }
}
