package cleancode.order;


import java.util.logging.Logger;

public class OrderService {


    public boolean validateOrder(Order order) {

        if (orderHasNoItems(order)) {
            throw new IllegalArgumentException("주문 항목이 없습니다.");
        }

        if (isTheTotalPriceZero(order)) {
            throw new IllegalArgumentException("올바르지 않은 총 가격입니다.");
        }

        if (orderHasNoCustomerInfo(order)) {
            throw new IllegalArgumentException("사용자 정보가 없습니다.");
        }

        return true;
    }

    private static boolean orderHasNoCustomerInfo(Order order) {
        return !order.hasCustomerInfo();
    }

    private static boolean isTheTotalPriceZero(Order order) {
        return order.getTotalPrice() <= 0;
    }

    private static boolean orderHasNoItems(Order order) {
        return order.getItems().isEmpty();
    }

}
