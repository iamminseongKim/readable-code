package cleancode.studycafe.misson.io;

import cleancode.studycafe.misson.model.StudyCafeLockerPass;
import cleancode.studycafe.misson.model.StudyCafePass;
import cleancode.studycafe.misson.model.dto.PassCost;

import java.util.List;

public interface OutputHandler {

    void showWelcomeMessage();

    void showAnnouncement();

    void askPassTypeSelection();

    void showPassListForSelection(List<StudyCafePass> passes);

    void askLockerPass(StudyCafeLockerPass askLockerPass);

    void showPassOrderSummary(StudyCafePass selectedPass, StudyCafeLockerPass lockerPass, PassCost passCost);

    void showSimpleMessage(String message);

}
