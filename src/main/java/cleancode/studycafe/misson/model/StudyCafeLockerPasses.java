package cleancode.studycafe.misson.model;

import java.util.ArrayList;
import java.util.List;

public class StudyCafeLockerPasses {

    private final List<StudyCafeLockerPass> passes;

    private StudyCafeLockerPasses(List<StudyCafeLockerPass> passes) {
        this.passes = passes;
    }

    public static StudyCafeLockerPasses of(List<StudyCafeLockerPass> passes) {
        return new StudyCafeLockerPasses(passes);
    }

    public StudyCafeLockerPass getLockerPassFrom(StudyCafePass selectedPass) {
        List<StudyCafeLockerPass> lockerPasses = new ArrayList<>(passes);

        return lockerPasses.stream()
                .filter(option -> selectedPass.isSamePassType(option.getPassType()) && selectedPass.isSameDuration(option.getDuration()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 입력입니다."));
    }


}
