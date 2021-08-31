package model;

import java.util.Objects;

public class FreeRoom extends Room{

    public FreeRoom(String roomNumber, Double price, RoomType enumeration) {
        super(roomNumber, 0.0, enumeration);
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomNumber=" + super.getRoomNumber() + '\'' +
                ", price=Free" +
                ", enumeration=" + super.getRoomType() +
                '}';
    }

}
