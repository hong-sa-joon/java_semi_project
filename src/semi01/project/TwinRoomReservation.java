package semi01.project;

public class TwinRoomReservation extends RoomReservation {


    double sale;

    public TwinRoomReservation(String name, int day, String room, int people) {
        super(name, day, room, people);

        super.room = "Twin Room";
        super.money = 250000;
        this.sale = 0.1;

    }

}
