package semi01.project;

public class SweetRoomReservation extends RoomReservation{
 double discount;
    int people;


    public SweetRoomReservation() {
        this.discount = 0.2;
        super.room = "Sweet Room";
        super.money = 500000;
        super.people = -1; // 인원 제한 없음
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
