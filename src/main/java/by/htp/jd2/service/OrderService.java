package by.htp.jd2.service;

import java.util.List;

import by.htp.jd2.entity.Order;

public interface OrderService {

    List<Order> getAllOrders(int page) throws ServiceException;

    boolean addNewOrder(Order order) throws ServiceException;

    List<Order> userOrders(int id) throws ServiceException;

    boolean setAmount(int orderId, int amount) throws ServiceException;

    Order getOrderById(int id) throws ServiceException;

    boolean setPayment(int orderId) throws ServiceException;

    boolean setComplete(int orderId) throws ServiceException;

    boolean setCanceled(int orderId) throws ServiceException;

    boolean setRejectReason(String reason, int orderId) throws ServiceException;

    boolean setCrash(int orderId, int crashId) throws ServiceException;

    int pageCol() throws ServiceException;

}
