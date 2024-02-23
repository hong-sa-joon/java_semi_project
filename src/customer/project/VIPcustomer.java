package customer.project;

public class VIPcustomer extends Customer {
    private int agentID;//상담원 ID
    double saleRatio;//할인률

    public VIPcustomer(int cusotmerID, String customerName, int agentID) {
        super(cusotmerID, customerName);

        super.customerGrade = "VIP";
        super.bonusRatio = 0.05;
        this.saleRatio = 0.1;
        this.agentID = agentID;

    }

    @Override
    public int calcPrice(int price) {
        bonusPoint += price * bonusRatio;//보너스 적립
        return price - (int) (price * saleRatio);//지불할 금액(할인률 적용)
    }

    @Override
    public String showCustomerInfo() {
        return super.showCustomerInfo() + ", 담당 상담원 ID: " + agentID;
    }

    public int getAgentID() {
        return agentID;
    }
}
