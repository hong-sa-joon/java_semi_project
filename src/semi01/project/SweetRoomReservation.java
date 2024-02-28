package semi01.project;

public class SweetRoomReservation extends RoomReservation{
 double discount;


    public SweetRoomReservation(String name, int day, String room,int people) {
        super(name,day,room,people);
        this.discount = 0.2;
        super.room = "Sweet Room";
        this.money = 500000;
        super.people = -1; // 인원 제한 없음
        super.morning = true; // 조식 무료 제공 여부 설정
    }

    @Override
    public int calcPrice() {
        if (!room.equalsIgnoreCase("Single Room") && day >= 3) {
            return (this.money*day)-(int) ((money * day )*discount);
        } else {
            return money * day;
        }
    }
}
