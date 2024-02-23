package customer.project;

public class VIPcustomerOther extends Customer {
    double saleRatio;//할인률

    public VIPcustomerOther(int cusotmerID, String customerName) {
        super(cusotmerID, customerName);

        super.customerGrade = "VIP";
        super.bonusRatio = 0.03;
        this.saleRatio = 0.2;

    }

    @Override
    public int calcPrice(int price) {
        bonusPoint += price * bonusRatio;//보너스 적립
        return price - (int) (price * saleRatio);//지불할 금액(할인률 적용)
    }


}
