package studycafe.project;

import studycafe.project.util.Define;

public class Room {
    //방 정보
    private RoomType type;//방의 타입(개인방,단체방,쉼터)
    private int roomId;//예약을 위한 id
    private int rate;//쉼터 요금
    private boolean available;//방이 있는지 없는지

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public Room(int roomId, RoomType type) {
        this.roomId = roomId;
        this.type = type;
        this.available = true;//방이 있다

        switch (type) {
            case INDIVIDUAL:
                this.rate = Define.HOURLY_RATE;
                break;
            case GROUP:
                this.rate = Define.HOURLY_RATE;
                break;
            case REST:
                this.rate = 0;
                break;
        }


    }

    public void setType(RoomType type) {
        this.type = type;

    }

    public RoomType getType() {
        return type;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public boolean isAvailable() {
        return available;
    }


    public void setAvailable(boolean available) {
        this.available = available;
    }

}
