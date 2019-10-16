package by.htp.jd2.dao.impl;

import by.htp.jd2.dao.DaoException;
import by.htp.jd2.dao.UserDao;
import by.htp.jd2.dao.connectionpool.ConnectionPool;
import by.htp.jd2.entity.User;
import by.htp.jd2.entity.UserType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLUserDao implements UserDao {

    private static final String SELECT_USER_BY_LOGIN_AND_PASSWORD = "SELECT * FROM users WHERE(users.`login` = ?) AND (users.`password` = ?);";
    private static final String REGISTRATION_USER = "INSERT INTO users(login, password, passportnumber, fullname, address, email) VALUES(?, ?, ?, ?, ?, ?)";
    private static final String GET_ALL_USERS = "SELECT * FROM users;";
    private static final String DEL_USER = "UPDATE users SET users.active = '0' WHERE users.`iduser` = ?;";
    private static final String ACTIVATE_USER = "UPDATE users SET users.active = '1' WHERE users.`iduser` = ?;";
    private static final String PAY_BILL = "UPDATE users SET users.cash = users.cash - ? WHERE users.iduser = ?;";
    private static final String ADD_MONEY = "UPDATE users SET users.cash = users.cash + ? WHERE users.iduser = ?;";
    private static final String SELECT_LOGINS = "SELECT users.login FROM users;";
    private static final String GET_USER_BY_ID = "SELECT * FROM users WHERE users.`iduser` = ?;";
    private static final String HASH_PASSWORD = "SELECT MD5(?); ";

    @Override
    public User authorization(String loginA, String passwordA) throws DaoException {
        String login = null;
        String password = null;
        String fullName = null;
        String passNum = null;
        String email = null;
        String address = null;
        UserType type = null;
        int cash = 0;
        boolean active = false;
        int id = 0;
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.retrieve();
        String hash = null;
        try {
            PreparedStatement ps = connection.prepareStatement(HASH_PASSWORD);
            ps.setString(1, passwordA);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                hash = rs.getString(1);
            }
            ps = connection.prepareStatement(SELECT_USER_BY_LOGIN_AND_PASSWORD);
            ps.setString(1, loginA);
            ps.setString(2, hash);
            rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
                type = UserType.valueOf(rs.getString(4).toUpperCase());
                active = rs.getBoolean(5);
                passNum = rs.getString(6);
                fullName = rs.getString(7);
                address = rs.getString(8);
                email = rs.getString(9);
                cash = rs.getInt(10);
                login = rs.getString(2);
                password = rs.getString(3);

            }
            return new User(login, password, fullName, passNum, email, address, cash, type, active, id);
        } catch (SQLException e) {
            throw new DaoException("AUTORIZATION ERROR", e);
        } finally {
            pool.putback(connection);
        }
    }


    @Override
    public boolean registration(User user) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.retrieve();
        String hash = null;

        try {
            PreparedStatement ps = connection.prepareStatement(HASH_PASSWORD);
            ps.setString(1, user.getPassword());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                hash = rs.getString(1);
            }
            ps = connection.prepareStatement(REGISTRATION_USER);
            ps.setString(1, user.getLogin());
            ps.setString(2, hash);
            ps.setString(3, user.getPassNum());
            ps.setString(4, user.getFullName());
            ps.setString(5, user.getAddress());
            ps.setString(6, user.getEmail());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DaoException("REGISTRATION ERROR", e);
        } finally {
            pool.putback(connection);
        }
    }

    @Override
    public List<User> getAllUsers() throws DaoException {
        String login;
        String password;
        String fullName;
        String passNum;
        String email;
        String address;
        UserType type;
        int cash;
        boolean active;
        int id;
        List<User> list = new ArrayList<>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.retrieve();
        try (PreparedStatement ps = connection.prepareStatement(GET_ALL_USERS)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
                type = UserType.valueOf(rs.getString(4).toUpperCase());
                active = rs.getBoolean(5);
                passNum = rs.getString(6);
                fullName = rs.getString(7);
                address = rs.getString(8);
                email = rs.getString(9);
                cash = rs.getInt(10);
                login = rs.getString(2);
                password = rs.getString(3);
                list.add(new User(login, password, fullName, passNum, email, address, cash, type, active, id));
            }
            return list;
        } catch (SQLException e) {
            throw new DaoException("GET ALL CAR ERROR!!!", e);
        } finally {
            pool.putback(connection);
        }
    }

    @Override
    public boolean delUser(int id) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.retrieve();
        try (PreparedStatement ps = connection.prepareStatement(DEL_USER)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DaoException("DEL USER ERROR", e);
        } finally {
            pool.putback(connection);
        }
    }

    @Override
    public boolean pay(int sum, int id) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.retrieve();
        try (PreparedStatement ps = connection.prepareStatement(PAY_BILL)) {
            ps.setInt(1, sum);
            ps.setInt(2, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DaoException("PAY ERROR", e);
        } finally {
            pool.putback(connection);
        }
    }

    @Override
    public boolean activateUser(int id) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.retrieve();
        try (PreparedStatement ps = connection.prepareStatement(ACTIVATE_USER)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DaoException("ACTIVATE USER ERROR", e);
        } finally {
            pool.putback(connection);
        }
    }

    @Override
    public boolean addMoney(int sum, int id) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.retrieve();
        try (PreparedStatement ps = connection.prepareStatement(ADD_MONEY)) {
            ps.setInt(1, sum);
            ps.setInt(2, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DaoException("ADD MONEY ERROR", e);
        } finally {
            pool.putback(connection);
        }
    }

    @Override
    public boolean checkUser(String login) throws DaoException {

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.retrieve();
        List<String> logins = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(SELECT_LOGINS)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                logins.add(rs.getString(1));
            }
            return !logins.contains(login);
        } catch (SQLException e) {
            throw new DaoException("SELECT USER BY ID ERROR", e);
        } finally {
            pool.putback(connection);
        }
    }

    @Override
    public User getUserById(int id) throws DaoException {
        String login = null;
        String password = null;
        String fullName = null;
        String passNum = null;
        String email = null;
        String address = null;
        UserType type = null;
        int cash = 0;
        boolean active = false;
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.retrieve();

        try (PreparedStatement ps = connection.prepareStatement(GET_USER_BY_ID)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                type = UserType.valueOf(rs.getString(4).toUpperCase());
                active = rs.getBoolean(5);
                passNum = rs.getString(6);
                fullName = rs.getString(7);
                address = rs.getString(8);
                email = rs.getString(9);
                cash = rs.getInt(10);
                login = rs.getString(2);
                password = rs.getString(3);
            }
            return new User(login, password, fullName, passNum, email, address, cash, type, active, id);
        } catch (SQLException e) {
            throw new DaoException("GET USER BY ID ERROR", e);
        } finally {
            pool.putback(connection);

        }
    }
}
