package by.htp.jd2.dao;

import java.util.List;

import by.htp.jd2.entity.Order;

/**
 * @author alexey
 */
public interface OrderDao {

    /**
     * Returns a list of all orders from the database.
     *
     * @param page int current page
     * @return List of Orders object
     */
    List<Order> getAllOrders(int page) throws DaoException;

    /**
     * Adds a new order to the database
     *
     * @param order Order object
     * @return true if added successfully
     */
    boolean addNewOrder(Order order) throws DaoException;

    /**
     * Returns a list of orders for a specific user
     *
     * @param id int User ID
     * @return List of Order objects
     */
    List<Order> userOrders(int id) throws DaoException;

    /**
     * Sets the total amount for the order.
     *
     * @param orderId int Order ID
     * @param amount  int total amount
     * @return true if setting complete successfully
     */
    boolean setAmount(int orderId, int amount) throws DaoException;

    /**
     * Returns an Order object by its ID
     *
     * @param id int Order id
     * @return Order object
     */
    Order getOrderById(int id) throws DaoException;

    /**
     * Sets payment flag in true
     * @param orderId int Order id
     * @return true if payment setting successfully
     */
    boolean setPayment(int orderId) throws DaoException;

    /**
     * Sets complete flag in true
     * @param orderId int order id
     * @return true if setting successfully
     */
    boolean setComplete(int orderId) throws DaoException;

    /**
     * Sets complete flag in true
     * @param orderId intt Order ID
     * @return true if setting successfully
     */
    boolean setCanceled(int orderId) throws DaoException;

    /**
     * Sets the reason for cancellation to order
     * @param reason String reject reason
     * @param orderId int Order ID
     * @return true if setting successfully
     */
    boolean setRejectReason(String reason, int orderId) throws DaoException;

    /**
     * Sets the crash to order
     * @param orderId int Order ID
     * @param crashId int Crash ID
     * @return true if setting successfully
     */
    boolean setCrash(int orderId, int crashId) throws DaoException;

    /**
     * @return int number of pages
     */
    int pageCol() throws DaoException;

}
