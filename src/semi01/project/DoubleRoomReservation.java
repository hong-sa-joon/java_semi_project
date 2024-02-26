package semi01.project;

public class DoubleRoomReservation extends RoomReservation {

    double discount;

    public DoubleRoomReservation(String name, int day, String room, int people) {
        super(name, day, room, people);
        this.discount = 0.05;
        super.room = "Double Room";
        super.money = 200000;
        super.people = 2;

    }

    @Override
    public int calcPrice(int price) {
        if (day >= 3) {

            int sale = (money - (int) (this.money * discount)) * day;
        }
        return money;
    }

}