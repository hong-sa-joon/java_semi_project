package semi01.project;

public class TwinRoomReservation extends RoomReservation {
    int people;


    double discount;


    public TwinRoomReservation() {
        this.discount = 0.1;
        super.room = "Twin Room";
        super.money = 250000;
        super.people = 3; // 인원 제한 3명 설정
        super.morning = false; // 조식 무료 제공 여부 설정
    }
    @Override
    public int calcPrice() {
        if (!room.equalsIgnoreCase("Single Room") && day >= 3) {
            return (int) (money * day * (1 - (discount / 100.0)));
        } else {
            return money * day;
        }
    }

}
