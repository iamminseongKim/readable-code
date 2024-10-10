package cleancode.studycafe.misson.io;

import cleancode.studycafe.misson.model.StudyCafeLockerPass;
import cleancode.studycafe.misson.model.StudyCafePass;
import cleancode.studycafe.misson.model.StudyCafePassType;
import cleancode.studycafe.misson.model.dto.PassCost;

import java.util.List;

public class ConsoleOutputHandler implements OutputHandler {

    @Override
    public void showWelcomeMessage() {
        System.out.println("*** 프리미엄 스터디카페 ***");
    }

    @Override
    public void showAnnouncement() {
        System.out.println("* 사물함은 고정석 선택 시 이용 가능합니다. (추가 결제)");
        System.out.println("* !오픈 이벤트! 2주권 이상 결제 시 10% 할인, 12주권 결제 시 15% 할인! (결제 시 적용)");
        System.out.println();
    }

    @Override
    public void askPassTypeSelection() {
        System.out.println("사용하실 이용권을 선택해 주세요.");
        System.out.println("1. 시간 이용권(자유석) | 2. 주단위 이용권(자유석) | 3. 1인 고정석");
    }

    @Override
    public void showPassListForSelection(List<StudyCafePass> passes) {
        System.out.println();
        System.out.println("이용권 목록");
        for (int index = 0; index < passes.size(); index++) {
            StudyCafePass pass = passes.get(index);
            System.out.println(String.format("%s. ", index + 1) + showPassInfo(pass));
        }
    }

    @Override
    public void askLockerPass(StudyCafeLockerPass askLockerPass) {
        System.out.println();
        String askMessage = String.format(
                "사물함을 이용하시겠습니까? (%s)",
                showLockerPassInfo(askLockerPass)
        );

        System.out.println(askMessage);
        System.out.println("1. 예 | 2. 아니오");
    }

    @Override
    public void showPassOrderSummary(StudyCafePass selectedPass, StudyCafeLockerPass lockerPass, PassCost passCost) {
        System.out.println();
        System.out.println("이용 내역");
        System.out.println("이용권: " + showPassInfo(selectedPass));

        if (userSelectLockerPassCheck(lockerPass) && passCost.getExtraCost() > 0) {
            System.out.println("사물함: " + showLockerPassInfo(lockerPass));
        }

        if (hasDiscount(passCost.getDiscountPrice())) {
            System.out.println("이벤트 할인 금액: " + passCost.getDiscountPrice() + "원");
        }

        System.out.println("총 결제 금액: " + passCost.getTotalPrice() + "원");
        System.out.println();
    }

    private String showLockerPassInfo(StudyCafeLockerPass lockerPass) {

        if (lockerPass.getPrice() > 0) {
            return String.format("%s주권 - %d원", lockerPass.getDuration(), lockerPass.getPrice());
        }
        return "";
    }

    private String showPassInfo(StudyCafePass selectedPass) {
        if (selectedPass.isHourPass()) {
            return String.format("%s시간권 - %d원", selectedPass.getDuration(), selectedPass.getPrice());
        }
        if (selectedPass.isWeeklyPass()) {
            return String.format("%s주권 - %d원", selectedPass.getDuration(), selectedPass.getPrice());
        }
        if (selectedPass.isFixedPass()) {
            return String.format("%s주권 - %d원", selectedPass.getDuration(), selectedPass.getPrice());
        }
        return "";
    }

    private boolean hasDiscount(int discountPrice) {
        return discountPrice > 0;
    }

    private boolean userSelectLockerPassCheck(StudyCafeLockerPass lockerPass) {
        return lockerPass.getPrice() > 0;
    }

    @Override
    public void showSimpleMessage(String message) {
        System.out.println(message);
    }

}
