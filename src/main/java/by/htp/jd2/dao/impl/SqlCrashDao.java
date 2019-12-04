package by.htp.jd2.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.htp.jd2.dao.CrashDao;
import by.htp.jd2.dao.DaoException;
import by.htp.jd2.dao.connectionpool.ConnectionPool;
import by.htp.jd2.entity.Crash;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author alexey
 */
public class SqlCrashDao implements CrashDao {

    private static final Logger LOG = LogManager.getLogger(SqlCrashDao.class.getName());

    private static final String GET_ALL_CRASH_BILLS = "SELECT * FROM crashbill;";
    private static final String ADD_CRASH = "INSERT INTO crashbill (description, amount, cars_idcars , users_iduser) VALUES(?, ?, ?, ?);";
    private static final String GET_LAST_ID = "SELECT MAX( idcrashbill ) FROM crashbill;";
    private static final String GET_USERS_CRASH_BILLS = "select * from crashbill where users_iduser = ?;";
    private static final String SET_CRASH_PAYMENT = "UPDATE crashbill set complete = '1' WHERE idcrashbill = ?;";
    private static final String GET_CRASH_BY_ID = "SELECT * FROM crashbill WHERE idcrashbill = ?;";

    /**
     * @return List of all additional bills from database
     */
    @Override
    public List<Crash> getAllCrashs() throws DaoException {
        int id;
        String damage;
        int amount;
        int idCar;
        int idUser;
        boolean isComplete;
        List<Crash> list = new ArrayList<>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.retrieve();
        try (PreparedStatement ps = connection.prepareStatement(GET_ALL_CRASH_BILLS)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    id = rs.getInt(1);
                    damage = rs.getString(2);
                    amount = rs.getInt(3);
                    idCar = rs.getInt(5);
                    idUser = rs.getInt(6);
                    isComplete = rs.getBoolean(4);
                    list.add(new Crash(id, damage, amount, idCar, idUser, isComplete));
                }
                return list;
            }
        } catch (SQLException e) {
            LOG.error(e);
            throw new DaoException("GET ALL CRASHBILL ERROR!!!", e);
        } finally {
            pool.putback(connection);
        }

    }

    /**
     * @param crash Crash object
     * @return id of added crash
     */
    @Override
    public int addCrash(Crash crash) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.retrieve();
        int id = 0;
        try (PreparedStatement ps = connection.prepareStatement(ADD_CRASH)) {
            ps.setString(1, crash.getDamage());
            ps.setInt(2, crash.getAmount());
            ps.setInt(3, crash.getIdCar());
            ps.setInt(4, crash.getIdUser());
            ps.executeUpdate();
            try (PreparedStatement ps2 = connection.prepareStatement(GET_LAST_ID)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        id = rs.getInt(1);
                    }
                    return id;
                }
            }
        } catch (SQLException e) {
            LOG.error(e);
            throw new DaoException("ADD CRASH ERROR", e);
        } finally {
            pool.putback(connection);
        }
    }

    /**
     * @param idUserC int id user
     * @return List of crashs of concrete user
     */
    @Override
    public List<Crash> getUsersCrashs(int idUserC) throws DaoException {
        int id;
        String damage;
        int amount;
        int idCar;
        int idUser;
        boolean isComplete;
        List<Crash> list = new ArrayList<>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.retrieve();
        try (PreparedStatement ps = connection.prepareStatement(GET_USERS_CRASH_BILLS)) {
            ps.setInt(1, idUserC);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    id = rs.getInt(1);
                    damage = rs.getString(2);
                    amount = rs.getInt(3);
                    idCar = rs.getInt(5);
                    idUser = rs.getInt(6);
                    isComplete = rs.getBoolean(4);

                    list.add(new Crash(id, damage, amount, idCar, idUser, isComplete));
                }
                return list;
            }
        } catch (SQLException e) {
            LOG.error(e);
            throw new DaoException("GET USERS CRASHBILLS ERROR!!!", e);
        } finally {
            pool.putback(connection);
        }
    }

    /**
     * @param idCrash int crash id
     * @return true if crash payment install successfully or false if no
     */
    @Override
    public boolean setCrashPayment(int idCrash) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.retrieve();
        try (PreparedStatement ps = connection.prepareStatement(SET_CRASH_PAYMENT)) {
            ps.setInt(1, idCrash);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOG.error(e);
            throw new DaoException("SET PAYMENT CRASH ERROR", e);
        } finally {
            pool.putback(connection);
        }
    }

    /**
     * @param idCrash int crash id
     * @return crash object
     */
    @Override
    public Crash getCrashById(int idCrash) throws DaoException {
        int id = 0;
        String damage = null;
        int amount = 0;
        int idCar = 0;
        int idUser = 0;
        boolean isComplete = false;
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.retrieve();
        try (PreparedStatement ps = connection.prepareStatement(GET_CRASH_BY_ID)) {
            ps.setInt(1, idCrash);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    id = rs.getInt(1);
                    damage = rs.getString(2);
                    amount = rs.getInt(3);
                    idCar = rs.getInt(5);
                    idUser = rs.getInt(6);
                    isComplete = rs.getBoolean(4);
                }
                return new Crash(id, damage, amount, idCar, idUser, isComplete);
            }
        } catch (SQLException e) {
            LOG.error(e);
            throw new DaoException("GET CRASHBILL BY ID ERROR!!!", e);
        } finally {
            pool.putback(connection);
        }
    }
}
