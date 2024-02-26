package semi01.project;

import javax.swing.plaf.PanelUI;

public class RoomReservation {
    //필드
    protected int day;//예약 일수
    protected String name;//예약자명
    protected int money;//룸에 따른 가격

    private int discount;//3박 할인시 할인률(싱글은 x)

    protected String morning;//조식무료제공

    protected int people;//인원수

    public String room;//룸

    //생성자

    public RoomReservation() {
        initRoom();
    }

    public RoomReservation(String name, int day, String room, int people) {
        this.name = name;
        this.day = day;
        this.room = room;
        this.people = people;

        initRoom();
    }

    //초기화
    private void initRoom() {
        people = 1;
        money = 100000;
        room = "Single Room";
        morning = "No";
        discount = 0;
    }

    public int getDay() {
        return day;
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

    public String getMorning() {
        return morning;
    }

    public void setMorning(String morning) {
        this.morning = morning;
    }

    public int getPeople() {
        return people;
    }

    public void setPeople(int people) {
        this.people = people;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }


    //예약 확인

    public String showRoominfo() {
        return name + "님의 예약내역 \n" + "예약 룸: " + room + "\n" + "예약일수: " + day + "\n" + "예약인원: " + people + "\n" + "조식제공: " + morning + "\n" + "룸 가격: " + ((money * day) + "원");
    }

    //3박 이상 예약시 할인(Single제외)
    public int calcPrice() {
        int price = (money - (int) (this.money * discount)) * day;
        return price;
    }

    //예약 일수에 따라서 금액조정


}


