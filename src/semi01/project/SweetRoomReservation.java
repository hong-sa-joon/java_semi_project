package semi01.project;

public class SweetRoomReservation extends RoomReservation{

    double sale;

    public SweetRoomReservation(String name, int day, String room, int people) {
        super(name, day, room, people);

        super.room = "Twin Room";
        super.money = 500000;
        this.sale = 0.2;
        this.morning="Yes";

    }

}
