package by.htp.jd2.dao;

import java.util.List;

import by.htp.jd2.entity.Order;

public interface OrderDao {

    List<Order> getAllOrders(int page) throws DaoException;

    boolean addNewOrder(Order order) throws DaoException;

    List<Order> userOrders(int id) throws DaoException;

    boolean setAmount(int orderId, int amount) throws DaoException;

    Order getOrderById(int id) throws DaoException;

    boolean setPayment(int orderId) throws DaoException;

    boolean setComplete(int orderId) throws DaoException;

    boolean setCanceled(int orderId) throws DaoException;

    boolean setRejectReason(String reason, int orderId) throws DaoException;

    boolean setCrash(int orderId, int crashId) throws DaoException;

    int pageCol() throws DaoException;

}
