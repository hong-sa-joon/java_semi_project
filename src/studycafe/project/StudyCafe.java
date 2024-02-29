package studycafe.project;

import studycafe.project.util.Define;
import studycafe.project.util.Receipt;

import java.util.ArrayList;
import java.util.List;

public class StudyCafe{

    private static StudyCafe instanse;//StudyCafe를 넣을 변수이름
    private static List<Member> members;//회원정보를 넣을 변수
    private List<Room> rooms;//룸 정보를 넣을 변수
    private List<Reservation> reservations;//예약정보를 넣을 변수

    private StudyCafe() {
        this.members = new ArrayList<>();
        this.rooms = new ArrayList<>();
        this.reservations = new ArrayList<>();
        //방 초기화(개인 방 10개,단체방 5개 ,쉼터 1개)
        for (int i = 0; i < 10; i++) {//개인방
            rooms.add(new Room(i + 1, RoomType.INDIVIDUAL));
        }
        for (int i = 0; i < 10; i++) {
            rooms.add(new Room(i + 11, RoomType.GROUP));//단체방
        }
        rooms.add(new Room(21, RoomType.REST));


    }

    //instanse호출(StudyCafe)
    public static StudyCafe getInstance() {
        if (instanse == null) {
            instanse = new StudyCafe();
        }
        return instanse;
    }


    public Member signUpOrLogin(String name, String phoneNumber, String userID, String password) {
        for (Member member : members) {
            if (member.getUserID().equals(userID)) {
                // 이미 가입한 회원이면 로그인
                if (member.getPassword().equals(password)) {
                    System.out.println("로그인 되었습니다. " + member.getName() + "님, 환영합니다!");
                    return member;
                } else {
                    System.out.println("비밀번호가 일치하지 않습니다. 다시 시도해주세요.");
                    return null;
                }
            }
        }
        // 가입한 적 없으면 회원 가입
        Member newMember = new Member(name, phoneNumber, userID, password);
        members.add(newMember);
        System.out.println("회원 가입이 완료되었습니다. " + newMember.getName() + "님, 환영합니다!");
        return newMember;
    }


    //방문 및 퇴실 처리
    public void processVisit(Member member, Room room) {
        if (room != null && room.isAvailable()) {//방이 있는지 true, false로 알려줌
            if (!member.isCheckedIn()) {

                System.out.println(member.getName() + "님이" + room.getType() + "방에 입장했습니다.");

                //시간차감
                int visitDuration = 1;//1시간 방문
                member.setRemainingTime(member.getRemainingTime() - visitDuration);//보유시간-사용시간

                //방문했으면
                member.setCheckedIn(true);
            } else {
                System.out.println("다른 사용자가 이용중입니다");
            }return;
        } else {
            System.out.println("남은 방이 없습니다.");
        }
    }
    //예약 기능

    public boolean makeReservation(Member member, RoomType roomType, int duration) {
        Room room = getAvailableRoom(roomType);
        if (room != null) {
            Reservation reservation = new Reservation(member, room, duration);
            processVisit(member, room); // 방문 처리
            // 예약 내용 출력
            System.out.println("===== 예약 내용 =====");
            System.out.println("회원: " + member.getName());
            System.out.println("방 타입: " + room.getType());
            System.out.println("예약 기간: " + duration + "시간");
            System.out.println("남은 기간: " + member.getRemainingTime() + "시간");
            System.out.println("예약이 완료되었습니다.");
            return true;
        } else {
            System.out.println("해당 타입의 방이 모두 예약되었습니다. 다른 타입을 선택해주세요.");
            return false;
        }
    }

    private StudyCafe cafe;
    Member loggedInMember;


    public Room getAvailableRoom(RoomType roomType) {
        for (Room room : rooms) {
            if (room.getType() == roomType && room.isAvailable()) {
                return room;
            }
        }
        return null; // 해당 타입의 이용 가능한 방이 없을 경우

    }


    //결제관리(선불결제)
    public void makePayment(Member member, RoomType roomtype, int duration) {
        //결제처리
        int totalAmount = calculateTotalAmount(roomtype, duration);//룸 타입과 이용시간
        //영수증 출력
        Receipt.printReceipt(member, totalAmount);
    }

    //


    //금액 계산
    private int calculateTotalAmount(RoomType roomtype, int duration) {
        int totalAmount = 0;
        switch (roomtype) {
            case INDIVIDUAL://개인방
                if (duration == 2) {
                    totalAmount = Define.TWO_HOURS_RATE;break;
                } else if (duration == 4) {
                    totalAmount = Define.FOUR_HOURS_RATE;break;
                } else if (duration == 6) {
                    totalAmount = Define.SIX_HOURS_RATE;break;
                } else if (duration == 24) {
                    totalAmount = Define.FULL_DAY_RATE;break;
                } else if (duration > 6 && duration <= 24 && isNightTime()) {
                    totalAmount = Define.NIGHT_START_HOUR;break;
                } else {
                    totalAmount = Define.HOURLY_RATE * duration;
                    break;
                }
            case GROUP://단체방
                if (duration == 2) {
                    totalAmount = Define.TWO_HOURS_RATE;break;
                } else if (duration == 4) {
                    totalAmount = Define.FOUR_HOURS_RATE;break;
                } else if (duration == 6) {
                    totalAmount = Define.SIX_HOURS_RATE;break;
                } else if (duration == 24) {
                    totalAmount = Define.FULL_DAY_RATE;break;
                } else if (duration > 6 && duration <= 24 && isNightTime()) {
                    totalAmount = Define.NIGHT_START_HOUR;break;
                } else {
                    totalAmount = Define.HOURLY_RATE * duration;
                    break;
                }
            case REST://쉼터
                return 0;
        }
        return totalAmount;
    }

    // 심야 시간인지 확인
    private boolean isNightTime() {
        // 밤 10시 이후로
        return java.time.LocalTime.now().getHour() >= Define.NIGHT_START_HOUR;
    }

    //방정보 출력
    public void showRoomInfo() {
        System.out.println("==========방 정보=========");
        for (Room room : rooms) {
            System.out.println("방 ID: " + room.getRoomId() + "타입 : " + room.getType() + "요금 : "
                    + room.getRate() + "원, 예약가능 : " + (room.isAvailable() ? "가능" : "불가능"));
        }
        System.out.println("=========================");


    }

    public StudyCafe getInstanse() {
        return instanse;
    }

    public void setInstanse(StudyCafe instanse) {
        this.instanse = instanse;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public StudyCafe getCafe() {
        return cafe;
    }

    public void setCafe(StudyCafe cafe) {
        this.cafe = cafe;
    }

    public Member getLoggedInMember() {
        return loggedInMember;
    }

    public void setLoggedInMember(Member loggedInMember) {
        this.loggedInMember = loggedInMember;
    }


}
