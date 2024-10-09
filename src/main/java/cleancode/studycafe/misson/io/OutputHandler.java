package cleancode.studycafe.misson.io;

import cleancode.studycafe.misson.model.StudyCafePass;

import java.util.List;

public interface OutputHandler {

    void showWelcomeMessage();

    void showAnnouncement();

    void askPassTypeSelection();

    void showPassListForSelection(List<StudyCafePass> passes);

    void askLockerPass(String askLockerPass);

    void showPassOrderSummary(String selectedPass, String lockerPass, int discountPrice, int totalPrice);

    void showSimpleMessage(String message);
}
