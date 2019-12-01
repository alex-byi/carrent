package by.htp.jd2.dao.impl;

import by.htp.jd2.dao.DaoException;
import by.htp.jd2.dao.connectionpool.ConnectionPool;
import by.htp.jd2.entity.Order;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class SQLOrderDaoTest {


    @Test
    public void addNewOrder() throws DaoException {
        Order order = new Order("2019-10-01", "2019-10-01", "2019-10-02", 1,
                3, 200, 2);
        SQLOrderDao sqlOrderDao = new SQLOrderDao();
        sqlOrderDao.addNewOrder(order);
        int id = getLastId();
        order.setId(id);
        order.setRejectReason("0");
        Order expectedOreder = getOrderById(id);
        Assert.assertEquals(order, expectedOreder);
        delOrderAfterAdd(id);
    }

    @Test
    public void userOrders() throws DaoException {
        SQLOrderDao sqlOrderDao = new SQLOrderDao();
        List<Order> userOrders = sqlOrderDao.userOrders(2);
        boolean expected = true;
        for (Order userOrder : userOrders) {
            if (userOrder.getIdUser() != 2) {
                expected = false;
                break;
            }
            break;
        }
        Assert.assertTrue(expected);
    }

    @Test
    public void getOrderById() throws DaoException {
        SQLOrderDao sqlOrderDao = new SQLOrderDao();
        Order order = sqlOrderDao.getOrderById(1);
        Assert.assertEquals(order, getOrderById(1));
    }

    @Test
    public void setPayment() throws DaoException {
        SQLOrderDao sqlOrderDao = new SQLOrderDao();
        boolean expected = sqlOrderDao.setPayment(1);
        Assert.assertTrue(expected);
    }

    @Test
    public void setComplete() throws DaoException {
        SQLOrderDao sqlOrderDao = new SQLOrderDao();
        boolean expected = sqlOrderDao.setComplete(8);
        Assert.assertTrue(expected);
    }

    @Test
    public void setCanceled() throws DaoException {
        SQLOrderDao sqlOrderDao = new SQLOrderDao();
        boolean expected = sqlOrderDao.setCanceled(14);
        Assert.assertTrue(expected);
    }

    @Test
    public void setRejectReason() throws DaoException {
        SQLOrderDao sqlOrderDao = new SQLOrderDao();
        boolean expected = sqlOrderDao.setRejectReason("так надо", 14);
        Assert.assertTrue(expected);
    }

    private int getLastId() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.retrieve();
        int id = 0;
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT max(idorder) FROM orders;");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pool.putback(connection);
        }
        return id;
    }

    private Order getOrderById(int id) {
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
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM orders WHERE idorder = ?;")) {
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
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pool.putback(connection);
        }
        return new Order(dateOrder, startDate, endDate, isPaid, isCrash, idCar, crashBill, idUser, dayCol, amount,
                id, isCanceled, isComplete, rejectReason);
    }

    private static void delOrderAfterAdd(int id) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.retrieve();
        try (PreparedStatement ps = connection.prepareStatement("DELETE FROM orders WHERE idorder = ?;")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pool.putback(connection);
        }
    }
}