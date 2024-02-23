package semi01.project;

public class DoubleRoomReservation extends RoomReservation {
    double sale;


    public DoubleRoomReservation(String name, int day, String room, int people) {
        super(name, day, room, people);

        super.room = "Double Room";
        super.money = 200000;
        this.sale = 0.05;
        super.people=2;

    }

    @Override
    public int calcPrice() {
        if (day > 3) {

            money = super.calcPrice();
        }
            return money;
    }
}
