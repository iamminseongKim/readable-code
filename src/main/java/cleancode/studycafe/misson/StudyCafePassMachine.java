package cleancode.studycafe.misson;

import cleancode.studycafe.misson.exception.AppException;
import cleancode.studycafe.misson.io.InputHandler;
import cleancode.studycafe.misson.io.OutputHandler;
import cleancode.studycafe.misson.io.file.FileHandler;
import cleancode.studycafe.misson.model.*;

import java.util.List;

public class StudyCafePassMachine {

    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;
    private final StudyCafePasses studyCafePasses;
    private final StudyCafeLockerPasses lockerPasses;
    private final Caculate calculate;

    public StudyCafePassMachine(StudyCafeConfig studyCafeConfig) {
        this.inputHandler = studyCafeConfig.getInputHandler();
        this.outputHandler = studyCafeConfig.getOutputHandler();
        this.studyCafePasses = studyCafeConfig.getStudyCafePasses();
        this.lockerPasses = studyCafeConfig.getLockerPasses();
        this.calculate = studyCafeConfig.getCalculate();
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
        StudyCafeLockerPass lockerPass = lockerPasses.getLockerPassFrom(selectedPass);

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
        double discountRate = selectedPass.getDiscountRate();
        int price = selectedPass.getPrice();
        int extraCost = lockerPass.getPrice();
        int discountPrice = calculate.calculateDiscountPrice(price, discountRate);
        int totalPrice = calculate.calculateTotalPrice(price, discountPrice, extraCost);
        outputHandler.showPassOrderSummary(selectedPass.userSelectedPassInfo(), lockerPass.userSelectedLockerInfo(), discountPrice, totalPrice);
    }


    private boolean userSelectedLocker(StudyCafeLockerPass lockerPass) {
        boolean lockerSelection = false;

        if (lockerPass != null) {
            outputHandler.askLockerPass(lockerPass.userSelectedLockerInfo());
            lockerSelection = inputHandler.getLockerSelection();
        }
        return lockerSelection;
    }

    private StudyCafePass getStudyCafePass(StudyCafePassType studyCafePassType) {
        List<StudyCafePass> passes = studyCafePasses.matchedTypeMadePasses(studyCafePassType);
        outputHandler.showPassListForSelection(passes);
        return inputHandler.getSelectPass(passes);
    }

    private void selectHouryOrWeeklyStudyTicket(StudyCafePassType studyCafePassType) {
        StudyCafePass selectedPass = getStudyCafePass(studyCafePassType);
        getStudyCafePassExpirationPeriod(selectedPass, null);
    }

}
