package cleancode.studycafe.misson.pass;

import cleancode.studycafe.misson.model.*;
import cleancode.studycafe.misson.model.dto.PassCost;
import cleancode.studycafe.misson.model.dto.PassCostBuilder;

import java.util.List;

public class FixedPassService implements PassService {

    private final StudyCafePasses studyCafePasses;
    private final StudyCafeLockerPasses lockerPasses;

    public FixedPassService(StudyCafePasses studyCafePasses, StudyCafeLockerPasses lockerPasses) {
        this.studyCafePasses = studyCafePasses;
        this.lockerPasses = lockerPasses;
    }

    @Override
    public List<StudyCafePass> getStudyCafePassListFrom(StudyCafePassType studyCafePassType) {
        return studyCafePasses.matchedTypeMadePasses(studyCafePassType);
    }

    @Override
    public StudyCafeLockerPass getLockerPassFrom(StudyCafePass selectedPass) {
        return lockerPasses.getLockerPassFrom(selectedPass);
    }

}
