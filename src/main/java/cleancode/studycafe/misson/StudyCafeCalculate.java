package cleancode.studycafe.misson;

public class StudyCafeCalculate implements Caculate {

    @Override
    public int calculateDiscountPrice(int cost, double discountRate) {
        return (int) (cost * discountRate);
    }

    @Override
    public int calculateTotalPrice(int totalPrice, int discountPrice, int extraCost) {
        return totalPrice - discountPrice + extraCost;
    }
}
