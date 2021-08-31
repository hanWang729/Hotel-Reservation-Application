package service;

import model.Customer;
import model.IRoom;
import model.Reservation;
import model.Room;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;

public class ReservationService {
    public Collection<IRoom> roomList = new HashSet<>();
    public Collection<Reservation> reservations = new HashSet<>();
    private static ReservationService reservationService;

    private ReservationService() {}

    public static ReservationService getInstance(){
        if (Objects.isNull(reservationService)){
            reservationService = new ReservationService();
        }
        return reservationService;
    }


    public void addRoom(IRoom room){
        roomList.add(room);
    }

    public IRoom getARoom(String roomId){
        for(IRoom room:roomList){
            if (room.getRoomNumber().equals(roomId)){
                return room;
            }
        }
        return null;
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate){
        Reservation newReservation = new Reservation(customer,room,checkInDate,checkOutDate);
        reservations.add(newReservation);
        return newReservation;
    }

    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate){
        Collection<IRoom> availableRoom = new HashSet<>();

        return null;
    }

    public  Collection<Reservation> getCustomersReservation(Customer customer){
        Collection<Reservation> customersReservation = new HashSet<>();
        for (Reservation reservation: reservations){
            if(reservation.getCustomer().equals(customer)){
                customersReservation.add(reservation);
            }
        }
        return customersReservation;
    }

    public void printAllReservation(){
        System.out.println("Here is the list of all reservation");
        for(Reservation r : reservations){
            System.out.println(r);
        }
    }


}