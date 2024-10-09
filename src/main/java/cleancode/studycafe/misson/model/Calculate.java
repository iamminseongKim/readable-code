package cleancode.studycafe.misson.model;

public class Calculate {

    public int calculateDiscountPrice(StudyCafePass selectedPass) {

        double discountRate = selectedPass.getDiscountRate();

        return (int) (selectedPass.getPrice() * discountRate);
    }

    public int calculateTotalPrice(StudyCafePass selectedPass, StudyCafeLockerPass lockerPass, int discountPrice) {
        return selectedPass.getPrice() - discountPrice + (lockerPass != null ? lockerPass.getPrice() : 0);
    }
}
