package by.htp.jd2.service.validation;

import by.htp.jd2.entity.Order;

import java.util.List;

public class OrderDataValidator {
    private static final OrderDataValidator instance = new OrderDataValidator();

    private OrderDataValidator() {

    }

    public boolean checkOrdersList(List<Order> list) {
        Order trueOrder = new Order();

        for (Order order : list) {
            if (order.getClass() != trueOrder.getClass()) {
                return true;
            }
        }
        return false;
    }

    public boolean checkOrderInfo(Order order) {
        return order == null || order.getStartDate().isEmpty() || order.getEndDate().isEmpty() ||
                order.getDateOrder().isEmpty();

    }


    public static OrderDataValidator getInstance() {
        return instance;
    }
}
