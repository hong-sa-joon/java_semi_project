package semi01.project;

public class SweetRoomReservation extends RoomReservation{

 double discount;
    public SweetRoomReservation(String name, int day, String room, int people) {
        super(name, day, room, people);

        super.room = "Twin Room";
        super.money = 500000;
        this.discount = 0.2;
        this.morning="Yes";
        super.people = people;

    }
    @Override
    public int calcPrice(int price) {
        if (day >= 3) {

            int sale = (money - (int) (this.money * discount)) * day;
        }
        return money;
    }
}
