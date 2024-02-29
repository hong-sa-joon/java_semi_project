package studycafe.project.util;

import studycafe.project.Member;

public interface Receipt {
//     영수증 출력
    public static void printReceipt(Member member, int totalAmount) {
        System.out.println("===== 영수증 =====");
        System.out.println("회원: " + member.getName());
        System.out.println("총 결제 금액: " + totalAmount + "원");
        System.out.println("=================");
    }
}



