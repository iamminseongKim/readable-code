package cleancode.order;

import java.util.List;

public class Order {

    private int totalPrice;
    private String customerInfo;

    private List<String> items;

    public int getTotalPrice() {
        return totalPrice;
    }

    public boolean hasCustomerInfo() {
        return customerInfo != null;
    }

    public List<String> getItems() {
        return items;
    }
}
