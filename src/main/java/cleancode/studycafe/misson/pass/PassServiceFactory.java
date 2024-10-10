package cleancode.studycafe.misson.pass;

import cleancode.studycafe.misson.model.StudyCafeLockerPasses;
import cleancode.studycafe.misson.model.StudyCafePassType;
import cleancode.studycafe.misson.model.StudyCafePasses;

public class PassServiceFactory {

    private final StudyCafePasses studyCafePasses;
    private final StudyCafeLockerPasses lockerPasses;

    public PassServiceFactory(StudyCafePasses studyCafePasses, StudyCafeLockerPasses lockerPasses) {
        this.studyCafePasses = studyCafePasses;
        this.lockerPasses = lockerPasses;
    }

    public PassService getPassService(StudyCafePassType studyCafePassType) {
        switch (studyCafePassType) {
            case HOURLY -> {
                return new HourlyPassService(studyCafePasses);
            } case WEEKLY -> {
                return new WeeklyPassService(studyCafePasses);
            } case FIXED -> {
                return new FixedPassService(studyCafePasses, lockerPasses);
            } case null, default -> throw new UnsupportedOperationException("Unsupported study cafe pass type: " + studyCafePassType);
        }
    }
}
