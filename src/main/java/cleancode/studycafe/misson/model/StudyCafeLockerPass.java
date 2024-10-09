package cleancode.studycafe.misson.model;

import java.util.List;

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

    public static StudyCafeLockerPass getLockerPass(StudyCafePass selectedPass, List<StudyCafeLockerPass> lockerPasses) {
        return lockerPasses.stream()
                .filter(option -> option.getPassType() == selectedPass.getPassType()
                        && option.getDuration() == selectedPass.getDuration()
                )
                .findFirst()
                .orElseThrow(()-> new IllegalArgumentException("잘못된 입력입니다."));
    }

    public StudyCafePassType getPassType() {
        return passType;
    }

    public int getDuration() {
        return duration;
    }

    public int getPrice() {
        return price;
    }

    public String display() {
        if (passType == StudyCafePassType.HOURLY) {
            return String.format("%s시간권 - %d원", duration, price);
        }
        if (passType == StudyCafePassType.WEEKLY) {
            return String.format("%s주권 - %d원", duration, price);
        }
        if (passType == StudyCafePassType.FIXED) {
            return String.format("%s주권 - %d원", duration, price);
        }
        return "";
    }


}
