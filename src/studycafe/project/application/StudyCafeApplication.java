package studycafe.project.application;

import studycafe.project.Member;
import studycafe.project.Room;
import studycafe.project.RoomType;
import studycafe.project.StudyCafe;

public class StudyCafeApplication {
    public static void main(String[] args) {

        StudyCafe cafe = StudyCafe.getInstance();

        //회원가입
        cafe.signUpOrLogin("홍사준", "010-5899-0097", "123456", "joon");
        //로그인
        Member loggedInMember = cafe.signUpOrLogin("홍사준", "010-5899-0097", "123456", "joon");
        if (loggedInMember != null) {
            //방정보출력
            cafe.showRoomInfo();
            //방문예약
            System.out.println("\n\n");
            Room individualRoom = cafe.getAvailableRoom(RoomType.INDIVIDUAL);
            if (individualRoom != null) {
                cafe.processVisit(loggedInMember, individualRoom);
                cafe.makeReservation(loggedInMember, RoomType.INDIVIDUAL, 2);//예약(duration=예약시간)

            } else {

                System.out.println("이용 가능한 방이 없습니다.");
            }

            //결제
            cafe.makePayment(loggedInMember, RoomType.INDIVIDUAL, 4);
            cafe.makeReservation(loggedInMember, RoomType.INDIVIDUAL, 3);

        }


    }
}

