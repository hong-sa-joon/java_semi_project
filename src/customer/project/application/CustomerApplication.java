package customer.project.application;

import customer.project.Customer;
import customer.project.Goldcustomer;
import customer.project.VIPcustomer;
import customer.project.VIPcustomerOther;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

public class CustomerApplication {


    //    <Customer> :제네릭 -> ArrayList에 저장할 타입을 지정
    // customerList에는 Customer 타입의 객체만 저장가능
    //ArrayList: 중복된 데이터 저장 가능, 순서대로 인덱스 번호를 생성해 저장
    private static ArrayList<Customer> customerList = new ArrayList<>();


    public static void main(String[] args) {
        Customer customerLee = new Customer(10010, "이순신");
        Customer customerShin = new Customer(10020, "신사임당");
//        System.out.println(customerLee.getCustomerName() + "이 지불할 금액: " + customerLee.calcPrice(10000));
//        System.out.println(customerShin.getCustomerName() + "이 지불할 금액: " + customerShin.calcPrice(35000));
//        System.out.println(customerLee.showCustomerInfo());
//        System.out.println(customerShin.showCustomerInfo());
//        System.out.println();
        Customer customerHong = new Goldcustomer(10030, "홍길동");
        Customer customerJeong = new Goldcustomer(10040, "정약용");
//        //자동타입변환
//        System.out.println(customerHong.getCustomerName() + "이 지불할 금액: " + customerHong.calcPrice(10000));
//        System.out.println(customerJeong.getCustomerName() + "이 지불할 금액: " + customerJeong.calcPrice(35000));
//        System.out.println(customerHong.showCustomerInfo());
//        System.out.println(customerJeong.showCustomerInfo());
//        System.out.println();
        Customer customerYul = new VIPcustomer(10050, "이율곡", 1122);
//        System.out.println(customerYul.getCustomerName() + "이 지불할 금액: " + customerYul.calcPrice(35000));
//        System.out.println(customerYul.showCustomerInfo());
//        System.out.println();
        Customer customerkok = new VIPcustomerOther(10060, "이율곡");
//        System.out.println(customerkok.getCustomerName() + "이 지불할 금액: " + customerkok.calcPrice(35000));
//        System.out.println(customerkok.showCustomerInfo());
//
        //customerList에 고객 객체 저장
        customerList.add(customerLee);
        customerList.add(customerShin);
        customerList.add(customerHong);
        customerList.add(customerJeong);
        customerList.add(customerYul);
        customerList.add(customerkok);


        //arrayList 실습
        /*
        System.out.println("customerList 길이: "+ customerList.size());
        Customer getCustomer = customerList.get(2);
        System.out.println("getCustomer: "+getCustomer.getCustomerName());
        customerList.remove(0);//제거할 인덱스 번호
        Customer getCustomer2 = customerList.get(0);//제거가 됐는지 확인
        System.out.println("getCustomer2:"+getCustomer2.getCustomerName());
*/
        showAllCustomer();
//
//        //id로 찾은 고객의 실제 지불금액과 적립된 포인트 출력
        Customer customer = findCustomer(10050);//이율곡 id

        if (customer == null) {
            System.out.println("경고 : 존재하지 않는 회원입니다.");
        } else {
            showPriceBouns(customer,10000);//이율곡 고객은 만원짜리 상품 구매
        }

//
    }

    public static void showAllCustomer() {
        System.out.println("-=-========모든 고객 정보 출력===========");
        for (Customer customer : customerList) {
            System.out.println(customer.showCustomerInfo());
        }
    }

    public static Customer findCustomer(int customerID) {
        Customer resrultCustomer = null;
        for (Customer customer : customerList) {
            if (customer.getCustomerID() == customerID) {
                resrultCustomer = customer;//id가 같은 Customer 객체
                break;//id는 중복되지 않은 값이므로 1명밖에 없다
                //해당 id를 찾으면 반복문 종료

            }
        }
        return resrultCustomer;//찾은 Customer 객체를 리턴
    }

    //찾은 고객의 실제지불금액과 포인트 출력
    public static void showPriceBouns(Customer customer,int price){
        System.out.println("======="+customer.getCustomerName()+"고객의 지불금액과 포인트 내역=======");
        System.out.println("지불금액: "+customer.calcPrice(price));
        System.out.println("현재 보너스 포인트: "+customer.bonusPoint+"점");
    }

}
