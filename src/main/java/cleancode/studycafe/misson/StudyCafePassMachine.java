package cleancode.studycafe.misson;

import cleancode.studycafe.misson.exception.AppException;
import cleancode.studycafe.misson.io.InputHandler;
import cleancode.studycafe.misson.io.OutputHandler;
import cleancode.studycafe.misson.io.file.StudyCafeFileHandler;
import cleancode.studycafe.misson.model.StudyCafeLockerPass;
import cleancode.studycafe.misson.model.StudyCafePass;
import cleancode.studycafe.misson.model.StudyCafePassType;

import java.util.List;

public class StudyCafePassMachine {

    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;
    private final List<StudyCafePass> studyCafePasses;
    private final List<StudyCafeLockerPass> lockerPasses;

    public StudyCafePassMachine(InputHandler inputHandler, OutputHandler outputHandler, StudyCafeFileHandler studyCafeFileHandler) {
        this.inputHandler = inputHandler;
        this.outputHandler = outputHandler;
        this.studyCafePasses = studyCafeFileHandler.readStudyCafePasses();
        this.lockerPasses = studyCafeFileHandler.readLockerPasses();
    }

    public void run() {
        try {
            showWelcomeMessage();
            operateStudyCafeCounter(getStudyCafePassTypeFromUserInput());

        } catch (AppException e) {
            outputHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            outputHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }

    private void showWelcomeMessage() {
        outputHandler.showWelcomeMessage();
        outputHandler.showAnnouncement();
        outputHandler.askPassTypeSelection();
    }

    private StudyCafePassType getStudyCafePassTypeFromUserInput() {
        return inputHandler.getPassTypeSelectingUserAction();
    }

    private void operateStudyCafeCounter(StudyCafePassType studyCafePassType) {

        if (StudyCafePassType.FIXED == studyCafePassType) {
            selectFixedStudyTicket(studyCafePassType);
            return;
        }

        selectHouryOrWeeklyStudyTicket(studyCafePassType);
    }

    private void selectFixedStudyTicket(StudyCafePassType studyCafePassType) {

        StudyCafePass selectedPass = getStudyCafePass(studyCafePassType);
        StudyCafeLockerPass lockerPass = StudyCafeLockerPass.getLockerPass(selectedPass, lockerPasses);

        boolean lockerSelection = userSelectedLocker(lockerPass);

        if (lockerSelection) {
            getStudyCafePassExpirationPeriodAndLocker(selectedPass, lockerPass);
        } else {
            getStudyCafePassExpirationPeriod(selectedPass, null);
        }
    }

    private void getStudyCafePassExpirationPeriodAndLocker(StudyCafePass selectedPass, StudyCafeLockerPass lockerPass) {
        outputHandler.showPassOrderSummary(selectedPass, lockerPass);
    }

    private boolean userSelectedLocker(StudyCafeLockerPass lockerPass) {
        boolean lockerSelection = false;

        if (lockerPass != null) {
            outputHandler.askLockerPass(lockerPass);
            lockerSelection = inputHandler.getLockerSelection();
        }
        return lockerSelection;
    }

    private StudyCafePass getStudyCafePass(StudyCafePassType studyCafePassType) {
        List<StudyCafePass> passes = getStudyCafePassesFrom(studyCafePassType);
        outputHandler.showPassListForSelection(passes);
        return inputHandler.getSelectPass(passes);
    }

    private void selectHouryOrWeeklyStudyTicket(StudyCafePassType studyCafePassType) {
        StudyCafePass selectedPass = getStudyCafePass(studyCafePassType);
        getStudyCafePassExpirationPeriod(selectedPass, null);
    }

    private void getStudyCafePassExpirationPeriod(StudyCafePass selectedPass, StudyCafeLockerPass lockerPass) {
        outputHandler.showPassOrderSummary(selectedPass, lockerPass);
    }

    private List<StudyCafePass> getStudyCafePassesFrom(StudyCafePassType studyCafePassType) {
        return studyCafePasses.stream()
                .filter(studyCafePass -> studyCafePass.getPassType() == studyCafePassType)
                .toList();
    }
}
