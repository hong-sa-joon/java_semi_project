package semi01.project.application;

import semi01.project.DoubleRoomReservation;
import semi01.project.RoomReservation;
import semi01.project.SweetRoomReservation;
import semi01.project.TwinRoomReservation;

import java.util.ArrayList;
import java.util.Scanner;

public class RoomReservationApplication {
    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<RoomReservation> roomList = new ArrayList<>();

    public static void main(String[] args) {
        boolean reserve = true;

        while (reserve) {
            System.out.println("=====================");
            System.out.println("1. 룸 예약 | 2. 예약 내역 확인 | 3. 지불 내역 확인 | 4. 모든 룸 정보 보기 | 5. 모든 고객 예약 정보 보기 | 6. 특정 고객 예약 정보 보기 | 7. 종료");
            System.out.println("=====================");

            System.out.print("선택 > ");
            int select = scanner.nextInt();

            switch (select) {
                case 1:
                    reserveRoom();
                    break;
                case 2:
                    showReservationList();
                    break;
                case 3:
                    showPaymentDetails();
                    break;
                case 4:
                    showAllRoomInfo();
                    break;
                case 5:
                    showAllCustomerInfo();
                    break;
                case 6:
                    showSpecificCustomerInfo();
                    break;
                case 7:
                    reserve = false;
                    break;
            }
        }

        System.out.println("프로그램 종료");
    }

    private static void reserveRoom() {
        System.out.println("=====================");
        System.out.println("룸 예약");
        System.out.println("=====================");

        System.out.print("예약자명: ");
        String name = scanner.next();

        System.out.print("예약 일수: ");
        int day = scanner.nextInt();

        System.out.print("예약할 룸 (SingleRoom, DoubleRoom, TwinRoom, SweetRoom): ");
        String roomType = scanner.next();

        System.out.print("인원 수: ");
        int people = scanner.nextInt();

        RoomReservation newReservation = null;

        // 룸 타입에 따라 객체 생성
        switch (roomType) {
            case "SingleRoom":
                newReservation = new RoomReservation(name, day, roomType, people);
                break;
            case "DoubleRoom":
                newReservation = new DoubleRoomReservation(name, day, roomType, people);
                break;
            case "TwinRoom":
                newReservation = new TwinRoomReservation(name, day, roomType, people);
                break;
            case "SweetRoom":
                newReservation = new SweetRoomReservation(name, day, roomType,people);
                break;
            default:
                System.out.println("잘못된 룸 타입입니다.");
                return;
        }

        // 인원 제한 체크
        if (people > newReservation.getPeople()&&newReservation.getPeople()!=-1) {
            System.out.println("인원이 초과되었습니다.");
        } else {
            roomList.add(newReservation);
            System.out.println("예약이 완료되었습니다.");
        }
    }

    private static void showReservationList() {
        System.out.println("=====================");
        System.out.println("예약 내역 확인");
        System.out.println("=====================");

        for (RoomReservation reservation : roomList) {
            System.out.println(reservation.showRoominfo());
        }
    }

    private static void showPaymentDetails() {
        System.out.println("=====================");
        System.out.println("지불 내역 확인");
        System.out.println("=====================");

        for (RoomReservation reservation : roomList) {
            System.out.println("룸 이름: " + reservation.getRoom());
            System.out.println("예약 일수: " + reservation.getDay());
            System.out.println("조식 제공 여부: " + reservation.getMorning());
            System.out.println("지불 금액: " + reservation.calcPrice());
            System.out.println("------------------------");
        }
    }

    private static void showAllRoomInfo() {
        System.out.println("=====================");
        System.out.println("모든 룸 정보 보기");
        System.out.println("=====================");

        // 각 룸의 기본 정보 출력
        RoomReservation singleRoom = new RoomReservation();
        printRoomInfo(singleRoom);

        RoomReservation doubleRoom = new DoubleRoomReservation("", 0, "", 0);
        printRoomInfo(doubleRoom);

        RoomReservation twinRoom = new TwinRoomReservation("", 0, "", 0);
        printRoomInfo(twinRoom);

        RoomReservation sweetRoom = new SweetRoomReservation("", 0, "", 0);
        printRoomInfo(sweetRoom);
    }

    private static void showAllCustomerInfo() {
        System.out.println("=====================");
        System.out.println("모든 고객 예약 정보 보기");
        System.out.println("=====================");

        for (RoomReservation reservation : roomList) {
            System.out.println("룸 이름: " + reservation.getRoom());
            System.out.println("예약 일수: " + reservation.getDay());
            System.out.println("조식 제공 여부: " + reservation.getMorning());
            System.out.println("------------------------");
        }
    }

    private static void showSpecificCustomerInfo() {
        System.out.println("=====================");
        System.out.println("특정 고객 예약 정보 보기");
        System.out.println("=====================");

        System.out.print("예약자명을 입력하세요: ");
        String customerName = scanner.next();

        RoomReservation customerReservation = findCustomerReservation(customerName);

        if (customerReservation != null) {
            System.out.println("룸 이름: " + customerReservation.getRoom());
            System.out.println("예약 일수: " + customerReservation.getDay());
            System.out.println("조식 제공 여부: " + customerReservation.getMorning());
            System.out.println("------------------------");
        } else {
            System.out.println("해당 고객의 예약 정보가 없습니다.");
        }
    }

    private static RoomReservation findCustomerReservation(String name) {
        for (RoomReservation reservation : roomList) {
            if (reservation.getName().equals(name)) {
                return reservation;
            }
        }
        return null;
    }

    private static void printRoomInfo(RoomReservation reservation) {
        System.out.println("룸 이름: " + reservation.getRoom());
        System.out.println("가격: " + reservation.getMoney() + "원");
        System.out.println("인원 제한: " + (reservation.getPeople() > 0 ? reservation.getPeople() + "명" : "없음"));

        // SweetRoomReservation의 경우 조식 제공 여부 출력
        if (reservation instanceof SweetRoomReservation) {
            System.out.println("조식 제공 여부: " + ((SweetRoomReservation) reservation).getMorning());
        }else{
            System.out.println("조식 제공 여부: 없음");
        }

        System.out.println("------------------------");
    }
}