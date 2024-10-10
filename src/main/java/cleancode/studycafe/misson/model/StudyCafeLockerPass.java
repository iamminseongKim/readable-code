package cleancode.studycafe.misson.model;

public class StudyCafeLockerPass {

    private final StudyCafePassType passType;
    private final int duration;
    private final int price;

    private StudyCafeLockerPass(StudyCafePassType passType, int duration, int price) {
        this.passType = passType;
        this.duration = duration;
        this.price = price;
    }

    public static StudyCafeLockerPass of(StudyCafePassType passType, int duration, int price) {
        return new StudyCafeLockerPass(passType, duration, price);
    }

    public int getPrice() {
        return price;
    }

    public int getDuration() {
        return duration;
    }


    public boolean isSamePassType(StudyCafePass selectedPass) {
        return selectedPass.isSamePassType(passType);
    }

    public boolean isSameDuration(StudyCafePass selectedPass) {
        return selectedPass.isSameDuration(duration);
    }
}
