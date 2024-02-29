package studycafe.project;

import studentInfo.project.appliaction.StudentInfoApplication;
import studycafe.project.util.Receipt;

public class Member{
    //회원가입(회원정보)
    private String name;
    private String phoneNumber;
    private String userID;
    private String password;
    private boolean checkedIn;
    private int remainingTime;





    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public boolean isCheckedIn() {
        return checkedIn;
    }

    public void setCheckedIn(boolean checkedIn) {
        boolean isCheckedIn = checkedIn;
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(int remainingTime) {
        this.remainingTime = remainingTime;
    }

    public Member(String name, String phoneNumber, String userID, String password){
    this.name=name;//이름
    this.phoneNumber=phoneNumber;//연락처
    this.userID=userID;//아이디
    this.password=password;//비밀번호
    this.checkedIn=false;//체크인 기본값
    this.remainingTime=0;//남은 시간

}
    public void deductTime(int hours) {
        if (remainingTime >= hours) {
            remainingTime -= hours;
            System.out.println(name + "님, 남은 시간: " + remainingTime + "시간");
        } else {
            System.out.println(name + "님, 시간이 부족하여 더 이용할 수 없습니다.");
        }
    }
}
