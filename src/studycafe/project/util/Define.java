package studycafe.project.util;
public abstract class Define {
    //상수 정의 (바뀌지않는 조건들)
    public static  final  int HOURLY_RATE = 1500;//한시간
    public static  final int TWO_HOURS_RATE = 3000;//두시간
    public static  final int FOUR_HOURS_RATE = 5000;//4시간
    public static  final int SIX_HOURS_RATE = 6000;//6시간
    public static  final int FULL_DAY_RATE = 10000;//종일권
    public static  final int NIGHT_RATE = 6000;//심야권
    public static  final int NIGHT_START_HOUR = 22;//심야권 이용가능시간10시

    private int reaminingTime;

    public Define(int initialTime){
        this.reaminingTime=initialTime;
    }
    public int getReaminingTime(){
        return reaminingTime;
    }

    public void deductTime(int hours) {
        if (reaminingTime >= hours) {
            reaminingTime -= hours;
            System.out.println("남은 시간: " + reaminingTime + "시간");
        } else {
            System.out.println("시간이 부족하여 더 이용할 수 없습니다.");
        }
    }
    public class Example extends Define {
        public Example(int initialTime) {
            super(initialTime);
        }



    }
    }

