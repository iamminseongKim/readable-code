package cleancode.studycafe.misson.pass;

import cleancode.studycafe.misson.model.StudyCafeLockerPass;
import cleancode.studycafe.misson.model.StudyCafePass;
import cleancode.studycafe.misson.model.StudyCafePassType;

import java.util.List;

public interface PassService {

    List<StudyCafePass> getStudyCafePassListFrom(StudyCafePassType studyCafePassType);

    StudyCafeLockerPass getLockerPassFrom(StudyCafePass selectedPass);

}