package by.htp.jd2.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.htp.jd2.dao.DaoException;
import by.htp.jd2.dao.OrderDao;
import by.htp.jd2.dao.connectionpool.ConnectionPool;
import by.htp.jd2.entity.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author alexey
 */
public class SQLOrderDao implements OrderDao {

    private static final Logger LOG = LogManager.getLogger(SQLOrderDao.class.getName());

    private static final String GET_ALL_ORDERS = "SELECT * FROM orders order by idorders desc LIMIT ?, 5;";
    private static final String ADD_ORDER = "INSERT INTO orders (dateorder, startdate, enddate, cars_idcars, users_iduser, amount, dayCol) VALUES(?, ?, ?, ?, ?, ?, ?)";
    private static final String GET_ORDERS_BY_ID_USER = "SELECT * FROM orders WHERE users_iduser = ?;";
    private static final String SET_AMOUNT = "UPDATE orders SET orders.amount = ? WHERE orders.idorders = ?;";
    private static final String GET_ORDER_BY_ID = "SELECT * FROM orders WHERE idorders = ?;";
    private static final String SET_PAYMENT = "UPDATE orders SET orders.ispaid = '1' WHERE orders.idorders = ?;";
    private static final String SET_COMPLETE = "UPDATE orders SET orders.iscomplete = '1' WHERE orders.idorders = ?;";
    private static final String SET_CANCELED = "UPDATE orders SET orders.iscanceled = '1' WHERE orders.idorders = ?;";
    private static final String SET_REJECT_REASON = "UPDATE orders SET orders.reject_reason = ? WHERE orders.idorders = ?;";
    private static final String SET_CRASHBILL_ID = "UPDATE orders SET orders.crashbill_idcrashbill = ?, orders.iscrash = '1' WHERE orders.idorders = ?;";
    private static final String GET_PAGE_COL = "SELECT count(orders.idorders) FROM orders;";

    /**
     * @param page int page number
     * @return List of orders
     */
    @Override
    public List<Order> getAllOrders(int page) throws DaoException {
        String dateOrder;
        String startDate;
        String endDate;
        boolean isPaid;
        boolean isCrash;
        int idCar;
        int crashBill;
        int idUser;
        int dayCol;
        int amount;
        int id;
        boolean isCanceled;
        boolean isComplete;
        String rejectReason;
        List<Order> list = new ArrayList<>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.retrieve();
        try (PreparedStatement ps = connection.prepareStatement(GET_ALL_ORDERS)) {
            ps.setInt(1, page);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                dateOrder = rs.getString(2);
                startDate = rs.getString(3);
                endDate = rs.getString(4);
                isPaid = rs.getBoolean(5);
                isCrash = rs.getBoolean(6);
                idCar = rs.getInt(7);
                crashBill = rs.getInt(8);
                idUser = rs.getInt(9);
                dayCol = rs.getInt(11);
                amount = rs.getInt(10);
                id = rs.getInt(1);
                isCanceled = rs.getBoolean(12);
                isComplete = rs.getBoolean(13);
                rejectReason = rs.getString(14);
                list.add(new Order(dateOrder, startDate, endDate, isPaid, isCrash, idCar, crashBill, idUser, dayCol,
                        amount, id, isCanceled, isComplete, rejectReason));
            }
            return list;
        } catch (SQLException e) {
            LOG.error(e);
            throw new DaoException("GET ALL ORDER ERROR!!!", e);
        } finally {
            pool.putback(connection);
        }
    }

    /**
     *
     * @param order order object
     * @return true if adding is successfully or false if no
     */
    @Override
    public boolean addNewOrder(Order order) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.retrieve();

        try (PreparedStatement ps = connection.prepareStatement(ADD_ORDER)) {
            ps.setString(1, order.getDateOrder());
            ps.setString(2, order.getStartDate());
            ps.setString(3, order.getEndDate());
            ps.setInt(4, order.getIdCar());
            ps.setInt(5, order.getIdUser());
            ps.setInt(6, order.getAmount());
            ps.setInt(7, order.getDayCol());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOG.error(e);
            throw new DaoException("RADD ORDER ERROR", e);
        } finally {
            pool.putback(connection);
        }
    }

    /**
     *
     * @param idUser int user id
     * @return List of orders for current user
     */
    @Override
    public List<Order> userOrders(int idUser) throws DaoException {
        String dateOrder;
        String startDate;
        String endDate;
        boolean isPaid;
        boolean isCrash;
        int idCar;
        int crashBill;
        int dayCol;
        int amount;
        int id;
        boolean isCanceled;
        boolean isComplete;
        String rejectReason;
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.retrieve();
        List<Order> list = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(GET_ORDERS_BY_ID_USER)) {
            ps.setInt(1, idUser);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                dateOrder = rs.getString(2);
                startDate = rs.getString(3);
                endDate = rs.getString(4);
                isPaid = rs.getBoolean(5);
                isCrash = rs.getBoolean(6);
                idCar = rs.getInt(7);
                crashBill = rs.getInt(8);
                dayCol = rs.getInt(11);
                amount = rs.getInt(10);
                id = rs.getInt(1);
                isCanceled = rs.getBoolean(12);
                isComplete = rs.getBoolean(13);
                rejectReason = rs.getString(14);
                list.add(new Order(dateOrder, startDate, endDate, isPaid, isCrash, idCar, crashBill, idUser, dayCol,
                        amount, id, isCanceled, isComplete, rejectReason));
            }
            return list;
        } catch (SQLException e) {
            LOG.error(e);
            throw new DaoException("GET ALL USER ORDERS ERROR!!!", e);
        } finally {
            pool.putback(connection);
        }
    }

    /**
     *
     * @param orderId int order id
     * @param amount int amount
     * @return true if set complete successfully or false if no
     */
    @Override
    public boolean setAmount(int orderId, int amount) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.retrieve();
        try (PreparedStatement ps = connection.prepareStatement(SET_AMOUNT)) {
            ps.setInt(1, amount);
            ps.setInt(2, orderId);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOG.error(e);
            throw new DaoException("SET AMOUNT ORDER ERROR", e);
        } finally {
            pool.putback(connection);
        }
    }

    /**
     *
     * @param id int order id
     * @return order object
     */
    @Override
    public Order getOrderById(int id) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.retrieve();
        String dateOrder = null;
        String startDate = null;
        String endDate = null;
        boolean isPaid = false;
        boolean isCrash = false;
        int idCar = 0;
        int crashBill = 0;
        int idUser = 0;
        int dayCol = 0;
        int amount = 0;
        boolean isCanceled = false;
        boolean isComplete = false;
        String rejectReason = null;

        try (PreparedStatement ps = connection.prepareStatement(GET_ORDER_BY_ID)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                dateOrder = rs.getString(2);
                startDate = rs.getString(3);
                endDate = rs.getString(4);
                isPaid = rs.getBoolean(5);
                isCrash = rs.getBoolean(6);
                idCar = rs.getInt(7);
                crashBill = rs.getInt(8);
                idUser = rs.getInt(9);
                dayCol = rs.getInt(11);
                amount = rs.getInt(10);
                isCanceled = rs.getBoolean(12);
                isComplete = rs.getBoolean(13);
                rejectReason = rs.getString(14);

            }
            return new Order(dateOrder, startDate, endDate, isPaid, isCrash, idCar, crashBill, idUser, dayCol, amount,
                    id, isCanceled, isComplete, rejectReason);
        } catch (SQLException e) {
            LOG.error(e);
            throw new DaoException("GET ORDER BY ID ERROR", e);
        } finally {
            pool.putback(connection);
        }
    }

    /**
     *
     * @param orderId int order id
     * @return true if payment complete successfully
     */
    @Override
    public boolean setPayment(int orderId) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.retrieve();
        try (PreparedStatement ps = connection.prepareStatement(SET_PAYMENT)) {
            ps.setInt(1, orderId);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOG.error(e);
            throw new DaoException("SET PAYMENT ERROR", e);
        } finally {
            pool.putback(connection);
        }
    }

    /**
     *
     * @param orderId int order id
     * @return true if setting complete flag complete successfully
     */
    @Override
    public boolean setComplete(int orderId) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.retrieve();
        try (PreparedStatement ps = connection.prepareStatement(SET_COMPLETE)) {
            ps.setInt(1, orderId);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOG.error(e);
            throw new DaoException("SET COMPLETE ERROR", e);
        } finally {
            pool.putback(connection);
        }
    }

    /**
     *
     * @param orderId int order id
     * @return true if setting canceled flag complete successfully
     */
    @Override
    public boolean setCanceled(int orderId) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.retrieve();
        try (PreparedStatement ps = connection.prepareStatement(SET_CANCELED)) {
            ps.setInt(1, orderId);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOG.error(e);
            throw new DaoException("SET CANCELED ERROR", e);
        } finally {
            pool.putback(connection);
        }
    }

    /**
     *
     * @param reason string reason of addition bill
     * @param orderId int order id
     * @return true if setting reason of reject complete successfully
     */
    @Override
    public boolean setRejectReason(String reason, int orderId) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.retrieve();
        try (PreparedStatement ps = connection.prepareStatement(SET_REJECT_REASON)) {
            ps.setString(1, reason);
            ps.setInt(2, orderId);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOG.error(e);
            throw new DaoException("SET REJECT REASON ERROR", e);
        } finally {
            pool.putback(connection);
        }
    }

    /**
     *
     * @param orderId int order id
     * @param crashId int crash id
     * @return true if setting crash flag complete successfully
     */
    @Override
    public boolean setCrash(int orderId, int crashId) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.retrieve();
        try (PreparedStatement ps = connection.prepareStatement(SET_CRASHBILL_ID)) {
            ps.setInt(1, crashId);
            ps.setInt(2, orderId);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOG.error(e);
            throw new DaoException("SET CRASH ERROR", e);
        } finally {
            pool.putback(connection);
        }
    }

    /**
     *
     * @return number of page from database
     */
    @Override
    public int pageCol() throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.retrieve();
        int pageCol = 0;
        try {
            PreparedStatement ps = connection.prepareStatement(GET_PAGE_COL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                pageCol = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOG.error(e);
            throw new DaoException("GET PAGE COUNT ERROR", e);
        }
        return pageCol;
    }

}
