package studycafe.project;

public class Reservation {
    //예약
    private Member member;//회원정보
    private Room room;//방
    private int duration;//이용시간

    //생성자
    public Reservation(Member member,Room room,int duration){
        this.member=member;
        this.room=room;
        this.duration=duration;
    }


    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
