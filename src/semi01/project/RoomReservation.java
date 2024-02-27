package semi01.project;

public class RoomReservation {
    // 필드
    protected int day;      // 예약 일수
    protected String name;  // 예약자명
    protected int money;    // 룸에 따른 가격

    private int discount;   // 3박 할인시 할인률(싱글은 x)

    protected boolean morning;  // 조식 무료 제공

    protected int people;    // 인원수

    public String room;    // 룸

    // 생성자
    public RoomReservation() {
        initRoom();
    }

    public RoomReservation(String name, int day, String room, int people) {
        this.name = name;
        this.day = day;
        this.room = room;
        this.people = people;  // 인원수 설정

        initRoom();
    }

    // 초기화
    private void initRoom() {
        money = 100000;
        room = "Single Room";
        morning = false;
        people = 1;
    }

    // 인원수 변경
    public void setPeople(int people) {
        this.people = people;
    }

    public int getDay() {
        return day;
    }

    public int getPeople() {
        return people;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public boolean getMorning() {
        return morning;
    }

    public void setMorning(boolean morning) {
        this.morning = morning;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    // 예약 확인
    public String showRoominfo() {
        return name + "님의 예약내역 \n" +
                "예약 룸: " + room + "\n" +
                "예약일수: " + day + "\n" +
                "예약인원: " + people + "\n" +
                "조식제공: " + morning + "\n" +
                "룸 가격: " + (money * day) + "원" +
                "\n할인 후 가격: " + calcPrice();
    }

    // 3박 이상 예약시 할인(Single 제외)
    public int calcPrice() {
        if (!room.equalsIgnoreCase("Single Room") && day >= 3) {
            return (int) (money * day * (1 - (discount / 100.0)));
        } else {
            return money * day;
        }
    }
}


