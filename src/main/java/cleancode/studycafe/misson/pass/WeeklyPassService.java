package cleancode.studycafe.misson.pass;

import cleancode.studycafe.misson.model.*;
import cleancode.studycafe.misson.model.dto.PassCost;
import cleancode.studycafe.misson.model.dto.PassCostBuilder;

import java.util.List;

public class WeeklyPassService implements PassService {


    private final StudyCafePasses studyCafePasses;

    public WeeklyPassService(StudyCafePasses studyCafePasses) {
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
