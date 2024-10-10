package cleancode.studycafe.misson.model.dto;

public class PassCostBuilder {

    private int defaultCost;
    private double discountRate;
    private int extraCost;

    public PassCostBuilder defaultCost(int defaultCost) {
        this.defaultCost = defaultCost;
        return this;
    }

    public PassCostBuilder discountRate(double discountRate) {
        this.discountRate = discountRate;
        return this;
    }

    public PassCostBuilder extraCost(int extraCost) {
        this.extraCost = extraCost;
        return this;
    }

    public PassCost build() {
        int discountPrice = (int) (defaultCost * discountRate);
        int totalPrice = defaultCost - discountPrice + extraCost;
        return new PassCost(defaultCost, discountPrice, discountRate, totalPrice, extraCost);
    }

}
