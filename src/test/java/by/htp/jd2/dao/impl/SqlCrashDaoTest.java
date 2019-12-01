package by.htp.jd2.dao.impl;

import by.htp.jd2.dao.DaoException;
import by.htp.jd2.dao.connectionpool.ConnectionPool;
import by.htp.jd2.entity.Crash;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SqlCrashDaoTest {

    @Test
    public void getAllCrashs() throws DaoException {
        SqlCrashDao sqlCrashDao = new SqlCrashDao();
        List<Crash> crashs = sqlCrashDao.getAllCrashs();
        int expeted = crashs.size();
        Assert.assertEquals(expeted, getCrashCount());
    }

    @Test
    public void addCrash() throws DaoException {
        Crash actualCrash = new Crash("test", 100, 1, 1);
        SqlCrashDao sqlCrashDao = new SqlCrashDao();
        int actualId = sqlCrashDao.addCrash(actualCrash);
        actualCrash.setId(actualId);
        Crash crash = getCrashByDescription(actualCrash.getDamage());
        Assert.assertEquals(crash, actualCrash);
        delCrashAfterAdd(actualId);
    }

    @Test(expected = DaoException.class)
    public void addCrashException() throws DaoException {
        Crash actualCrash = new Crash("test", 100, 100, 100);
        SqlCrashDao sqlCrashDao = new SqlCrashDao();
        sqlCrashDao.addCrash(actualCrash);
    }

    @Test
    public void getUsersCrashs() throws DaoException {
        SqlCrashDao sqlCrashDao = new SqlCrashDao();
        List<Crash> usersCrashs = sqlCrashDao.getUsersCrashs(2);
        boolean expected = true;
        for (Crash usersCrash : usersCrashs) {
            if (usersCrash.getIdUser() != 2) {
                expected = false;
                break;
            }
            break;
        }
        Assert.assertTrue(expected);
    }

    @Test
    public void setCrashPayment() throws DaoException {
        SqlCrashDao sqlCrashDao = new SqlCrashDao();
        boolean expected = sqlCrashDao.setCrashPayment(1);
        Assert.assertTrue(expected);
    }

//    @Test
//    public void getCrashById() throws DaoException {
//        Crash actualCrash = new Crash("прокол колеса", 50, 2, 2);
//        actualCrash.setComplete(true);
//        actualCrash.setId(1);
//        SqlCrashDao sqlCrashDao = new SqlCrashDao();
//        Crash crash = sqlCrashDao.getCrashById(1);
//        Assert.assertEquals(actualCrash, crash);
//    }

    private int getCrashCount() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.retrieve();
        int pageCol = 0;
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT COUNT(*) FROM crashbill LIMIT 1;");
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

    private static void delCrashAfterAdd(int id) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.retrieve();
        try (PreparedStatement ps = connection.prepareStatement("DELETE FROM crashbill WHERE idcrashbill = ?;")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pool.putback(connection);
        }
    }

    private Crash getCrashByDescription(String description) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.retrieve();
        int id = 0;
        int amount = 0;
        int idCar = 0;
        int idUser = 0;
        boolean complete = false;
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM crashbill WHERE description = ?;")) {
            ps.setString(1, description);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
                amount = rs.getInt(3);
                idCar = rs.getInt(5);
                idUser = rs.getInt(6);
                complete = rs.getBoolean(4);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pool.putback(connection);
        }
        return new Crash(id, description, amount, idCar, idUser, complete);
    }

}