package cleancode.studycafe.misson;

import cleancode.studycafe.misson.exception.AppException;
import cleancode.studycafe.misson.io.InputHandler;
import cleancode.studycafe.misson.io.OutputHandler;
import cleancode.studycafe.misson.io.file.FileHandler;
import cleancode.studycafe.misson.model.Calculate;
import cleancode.studycafe.misson.model.StudyCafeLockerPass;
import cleancode.studycafe.misson.model.StudyCafePass;
import cleancode.studycafe.misson.model.StudyCafePassType;

import java.util.List;

public class StudyCafePassMachine {

    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;
    private final List<StudyCafePass> studyCafePasses;
    private final List<StudyCafeLockerPass> lockerPasses;
    private final Calculate calculate;

    public StudyCafePassMachine(InputHandler inputHandler, OutputHandler outputHandler, FileHandler cafeFileHandler, FileHandler lockerFileHandler, Calculate calculate) {
        this.inputHandler = inputHandler;
        this.outputHandler = outputHandler;
        this.studyCafePasses = (List<StudyCafePass>) cafeFileHandler.readFileAndMakePasses();
        this.lockerPasses = (List<StudyCafeLockerPass>) lockerFileHandler.readFileAndMakePasses();
        this.calculate = calculate;
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
        calculatePriceAndShowToUser(selectedPass, lockerPass);
    }

    private void getStudyCafePassExpirationPeriod(StudyCafePass selectedPass, StudyCafeLockerPass lockerPass) {
        calculatePriceAndShowToUser(selectedPass, lockerPass);
    }

    private void calculatePriceAndShowToUser(StudyCafePass selectedPass, StudyCafeLockerPass lockerPass) {
        int discountPrice = calculate.calculateDiscountPrice(selectedPass);
        int totalPrice = calculate.calculateTotalPrice(selectedPass, lockerPass, discountPrice);
        outputHandler.showPassOrderSummary(selectedPass.display(), lockerPass.display(), discountPrice, totalPrice);
    }


    private boolean userSelectedLocker(StudyCafeLockerPass lockerPass) {
        boolean lockerSelection = false;

        if (lockerPass != null) {
            outputHandler.askLockerPass(lockerPass.display());
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

    private List<StudyCafePass> getStudyCafePassesFrom(StudyCafePassType studyCafePassType) {
        return studyCafePasses.stream()
                .filter(studyCafePass -> studyCafePass.getPassType() == studyCafePassType)
                .toList();
    }
}
