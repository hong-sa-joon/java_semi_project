package semi01.project;


public class DoubleRoomReservation extends RoomReservation {

    double discount;
    public DoubleRoomReservation() {
        this.discount = 0.05;
        super.room = "Double Room";
        super.money = 200000;
        super.people = 2; // 부모 클래스의 people 필드 사용
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
