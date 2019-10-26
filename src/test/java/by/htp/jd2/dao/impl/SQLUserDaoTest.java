package by.htp.jd2.dao.impl;

import by.htp.jd2.dao.DaoException;
import by.htp.jd2.dao.connectionpool.ConnectionPool;
import by.htp.jd2.entity.User;
import by.htp.jd2.entity.UserType;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class SQLUserDaoTest {

    @Test
    public void authorization() throws DaoException {
        SQLUserDao sqlUserDao = new SQLUserDao();
        User actualUser = new User("ivanov", "8bd22c09a8c50e84416a975d13b09758", "Иванов Иван Иванович", "MC19283123",
                "ivan@mail.com", "г.Минск, Победителей 83", 1000, UserType.ADMIN, true, 1);
        User user = sqlUserDao.authorization("ivanov", "ivanov12345");
        Assert.assertEquals(user, actualUser);
    }

    @Test
    public void getAllUsers() throws DaoException {
        SQLUserDao sqlUserDao = new SQLUserDao();
        List<User> users = sqlUserDao.getAllUsers(5);
        int expected = users.size();
        Assert.assertEquals(expected, 5);
    }

    @Test
    public void delUser() throws DaoException {
        SQLUserDao sqlUserDao = new SQLUserDao();
        boolean expected = sqlUserDao.delUser(24);
        Assert.assertTrue(expected);
    }

    @Test
    public void activateUser() throws DaoException {
        SQLUserDao sqlUserDao = new SQLUserDao();
        boolean expected = sqlUserDao.activateUser(24);
        Assert.assertTrue(expected);
    }

    @Test
    public void pay() throws DaoException {
        SQLUserDao sqlUserDao = new SQLUserDao();
        boolean expected = sqlUserDao.pay(500, 24);
        Assert.assertTrue(expected);
    }

    @Test
    public void addMoney() throws DaoException {
        SQLUserDao sqlUserDao = new SQLUserDao();
        boolean expected = sqlUserDao.addMoney(500, 24);
        Assert.assertTrue(expected);
    }

    @Test
    public void getUserById() throws DaoException {
        SQLUserDao sqlUserDao = new SQLUserDao();
        User actualUser = new User("ivanov", "8bd22c09a8c50e84416a975d13b09758", "Иванов Иван Иванович", "MC19283123",
                "ivan@mail.com", "г.Минск, Победителей 83", 1000, UserType.ADMIN, true, 1);
        User user = sqlUserDao.getUserById(1);
        Assert.assertEquals(user, actualUser);

    }

    private int getUserCount() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.retrieve();
        int pageCol = 0;
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT COUNT(*) FROM users LIMIT 1;");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                pageCol = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pool.putback(connection);
        }
        return pageCol;
    }


}