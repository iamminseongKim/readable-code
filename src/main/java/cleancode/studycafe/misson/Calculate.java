package cleancode.studycafe.misson;

import cleancode.studycafe.misson.model.StudyCafeLockerPass;
import cleancode.studycafe.misson.model.StudyCafePass;

public class Calculate {

    public int calculateDiscountPrice(StudyCafePass selectedPass) {

        double discountRate = selectedPass.getDiscountRate();

        return (int) (selectedPass.getPrice() * discountRate);
    }

    public int calculateTotalPrice(StudyCafePass selectedPass, StudyCafeLockerPass lockerPass, int discountPrice) {
        return selectedPass.getPrice() - discountPrice + (lockerPass != null ? lockerPass.getPrice() : 0);
    }
}
