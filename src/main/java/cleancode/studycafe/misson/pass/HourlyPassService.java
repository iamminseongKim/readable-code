package cleancode.studycafe.misson.pass;

import cleancode.studycafe.misson.model.*;

import java.util.List;

public class HourlyPassService implements PassService {


    private final StudyCafePasses studyCafePasses;

    public HourlyPassService(StudyCafePasses studyCafePasses) {
        this.studyCafePasses = studyCafePasses;
    }

    @Override
    public List<StudyCafePass> getStudyCafePassListFrom(StudyCafePassType studyCafePassType) {
        return studyCafePasses.matchedTypeMadePasses(studyCafePassType);
    }

    @Override
    public StudyCafeLockerPass getLockerPassFrom(StudyCafePass selectedPass) {
        return null;
    }


}
