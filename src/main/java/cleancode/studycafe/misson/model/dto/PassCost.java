package cleancode.studycafe.misson.model.dto;

public class PassCost {

    private int defaultCost;
    private int discountPrice;
    private double discountRate;
    private int totalPrice;
    private int extraCost;

    protected PassCost(int defaultCost, int discountPrice, double discountRate, int totalPrice, int extraCost) {
        this.defaultCost = defaultCost;
        this.discountPrice = discountPrice;
        this.discountRate = discountRate;
        this.totalPrice = totalPrice;
        this.extraCost = extraCost;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }

    public int getExtraCost() {
        return extraCost;
    }

}
