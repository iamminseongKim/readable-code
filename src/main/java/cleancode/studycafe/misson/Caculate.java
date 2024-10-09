package cleancode.studycafe.misson;

public interface Caculate {

    int calculateDiscountPrice(int cost, double discountRate);

    int calculateTotalPrice(int totalPrice, int discountPrice, int extraCost);
}
