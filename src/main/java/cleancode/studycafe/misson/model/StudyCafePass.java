package cleancode.studycafe.misson.model;

public class StudyCafePass {

    private final StudyCafePassType passType;
    private final int duration;
    private final int price;
    private final double discountRate;

    private StudyCafePass(StudyCafePassType passType, int duration, int price, double discountRate) {
        this.passType = passType;
        this.duration = duration;
        this.price = price;
        this.discountRate = discountRate;
    }

    public static StudyCafePass of(StudyCafePassType passType, int duration, int price, double discountRate) {
        return new StudyCafePass(passType, duration, price, discountRate);
    }

    public boolean isHourPass() {
        return StudyCafePassType.HOURLY == this.passType;
    }

    public boolean isWeeklyPass() {
        return StudyCafePassType.WEEKLY == this.passType;
    }

    public boolean isFixedPass() {
        return StudyCafePassType.FIXED == this.passType;
    }

    public boolean isSamePassType(StudyCafePassType studyCafePassType) {
        return passType == studyCafePassType;
    }

    public boolean isSameDuration(int inputDuration) {
        return duration == inputDuration;
    }

    public int getPrice() {
        return price;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public int getDuration() {
        return duration;
    }
}
