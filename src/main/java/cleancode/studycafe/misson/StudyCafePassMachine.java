package cleancode.studycafe.misson;

import cleancode.studycafe.misson.exception.AppException;
import cleancode.studycafe.misson.io.InputHandler;
import cleancode.studycafe.misson.io.OutputHandler;
import cleancode.studycafe.misson.model.*;
import cleancode.studycafe.misson.model.dto.PassCost;
import cleancode.studycafe.misson.model.dto.PassCostBuilder;
import cleancode.studycafe.misson.pass.PassService;
import cleancode.studycafe.misson.pass.PassServiceFactory;

import java.util.List;

public class StudyCafePassMachine {

    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;
    private final PassServiceFactory passServiceFactory;

    private PassService passService;

    public StudyCafePassMachine(StudyCafeConfig studyCafeConfig) {
        this.inputHandler = studyCafeConfig.getInputHandler();
        this.outputHandler = studyCafeConfig.getOutputHandler();
        this.passServiceFactory = studyCafeConfig.getPassServiceFactory();
    }

    public void run() {
        try {
            // 웰컴 메시지
            showWelcomeMessage();

            // 이용권 선택
            StudyCafePassType studyCafePassTypeFromUserInput = getStudyCafePassTypeFromUserInput();
            StudyCafePass studyCafePass = userChooseStudyCafePass(studyCafePassTypeFromUserInput);

            // 라커 목록 가져오기
            StudyCafeLockerPass studyCafeLockerPass = getStudyCafeLockerPassFrom(studyCafePassTypeFromUserInput, studyCafePass);

            // 계산
            PassCost userPassCost = userPassTotalPrice(studyCafePass, studyCafeLockerPass);

            // 영수증 출력
            outputHandler.showPassOrderSummary(studyCafePass, studyCafeLockerPass, userPassCost);


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
        StudyCafePassType studyCafePassTypeFromUserInput = inputHandler.getPassTypeSelectingUserAction();
        passService = passServiceFactory.getPassService(studyCafePassTypeFromUserInput);
        return studyCafePassTypeFromUserInput;
    }

    private StudyCafePass userChooseStudyCafePass(StudyCafePassType studyCafePassType) {

        List<StudyCafePass> studyCafePassList = passService.getStudyCafePassListFrom(studyCafePassType);
        outputHandler.showPassListForSelection(studyCafePassList);

        return inputHandler.getSelectPass(studyCafePassList);
    }

    private StudyCafeLockerPass getStudyCafeLockerPassFrom(StudyCafePassType userPassType, StudyCafePass studyCafePass) {
        if (userPassType == StudyCafePassType.FIXED) {
            return makeStudyCafeLockerPass(studyCafePass);
        }
        return notMakeStudyCafeLockerPass(userPassType);
    }

    private StudyCafeLockerPass makeStudyCafeLockerPass(StudyCafePass studyCafePass) {
        return passService.getLockerPassFrom(studyCafePass);
    }

    private StudyCafeLockerPass notMakeStudyCafeLockerPass(StudyCafePassType userPassType) {
        return StudyCafeLockerPass.of(userPassType, 0, 0);
    }

    private PassCost userPassTotalPrice(StudyCafePass studyCafePass, StudyCafeLockerPass studyCafeLockerPass) {
        //라커 구매 및 총액
        if (userWantsLocker(studyCafePass, studyCafeLockerPass)) {
            return getPassCost(studyCafePass, studyCafeLockerPass);
        }

        return getPassCost(studyCafePass, null);
    }

    private boolean userWantsLocker(StudyCafePass studyCafePass, StudyCafeLockerPass studyCafeLockerPass) {
        if (studyCafeLockerPass == null || studyCafePass.isSamePassType(StudyCafePassType.FIXED)) {
            return false;
        }
        outputHandler.askLockerPass(studyCafeLockerPass);
        return inputHandler.getLockerSelection();
    }

    private PassCost getPassCost(StudyCafePass studyCafePass, StudyCafeLockerPass studyCafeLockerPass) {
        int extraCost = 0;

        if (studyCafeLockerPass != null) {
            extraCost = studyCafeLockerPass.getPrice();
        }

        return new PassCostBuilder()
                .defaultCost(studyCafePass.getPrice())
                .discountRate(studyCafePass.getDiscountRate())
                .extraCost(extraCost)
                .build();
    }

}
