package semi01.project.application;

import semi01.project.DoubleRoomReservation;
import semi01.project.RoomReservation;
import semi01.project.SweetRoomReservation;
import semi01.project.TwinRoomReservation;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.SimpleTimeZone;

public class RoomReservationApplication {
    private static Scanner scanner = new Scanner(System.in);
    //RoomReservation객체 저장가능 50개
    private static ArrayList<RoomReservation> roomList = new ArrayList<>();

    public static void main(String[] args) {
        boolean reserve = true;

        while (reserve) {
            System.out.println("=====================");
            System.out.println("1.예약 | 2. 예약목록 확인 | 3.예약자 확인 ");
            System.out.println("=====================");


            System.out.println("선택 >");

            int select = scanner.nextInt();//입력받기
            switch (select) {
                case 1:
                    createroom();
                    break;
                case 2:
                    showAllroom();
                    break;

                case 3:
                    String name=scanner.next();
                    System.out.println(findpeople(name).showRoominfo());
                break;
                case 4:
                    reserve = false;
            }
        }
        System.out.println("프로그램 종료");
    }

    private static void createroom() {

        //예약하기
        System.out.println("=====================");
        System.out.println("예약하기");
        System.out.println("=====================");

        System.out.println("예약자명: ");
        String name = scanner.next();

        System.out.println("예약 일수: ");
        int day = scanner.nextInt();


        System.out.println("예약할 룸: ");
        String roomselect = scanner.next();

        System.out.println("인원 수: ");
        int people = scanner.nextInt();



        RoomReservation newRoomReservation = null;

        switch (roomselect) {
            case "SingleRoom":
                newRoomReservation = new RoomReservation(name, day, roomselect, people);
                break;
            case "DoubleRoom":
                newRoomReservation = new DoubleRoomReservation(name, day, roomselect, people );
                if (day>=3){
                    newRoomReservation.calcPrice();
                }
                break;
            case "TwinRoom":
                newRoomReservation = new TwinRoomReservation(name, day, roomselect, people);
                break;
            case "SweetRoom":
                newRoomReservation = new SweetRoomReservation(name, day, roomselect, people);
                break;
        }
        if (people>newRoomReservation.getPeople()){
            System.out.println("인원이 초과되었습니다.");
        }else {
        roomList.add(newRoomReservation);
        }

        }

    //모든 예약 정보 확인
    public static void showAllroom() {
        System.out.println("====== 모든 예약 정보 확인======");
        for (RoomReservation roomReservation : roomList) {
            System.out.println(roomReservation.showRoominfo());
        }
    }

    //예약 정보 학인

    public static RoomReservation findpeople(String name){


       RoomReservation resultpeople = null;
        for (RoomReservation roomReservation : roomList) {
           if ((roomReservation.getName()).equals(name)){
               resultpeople = roomReservation;
               break;
           }
        }
        return resultpeople;
    }

}


