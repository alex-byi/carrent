package by.htp.jd2.dao.impl;

import by.htp.jd2.dao.DaoException;
import by.htp.jd2.dao.UserDao;
import by.htp.jd2.dao.connectionpool.ConnectionPool;
import by.htp.jd2.entity.User;
import by.htp.jd2.entity.UserType;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author alexey
 */
public class SQLUserDao implements UserDao {

    private static final Logger LOG = LogManager.getLogger(SQLUserDao.class.getName());

    private static final String SELECT_USER_BY_LOGIN_AND_PASSWORD = "SELECT * FROM users WHERE(users.login = ?) AND (users.password = ?);";
    private static final String REGISTRATION_USER = "INSERT INTO users(login, password, passportnumber, fullname, address, email) VALUES(?, ?, ?, ?, ?, ?)";
    private static final String GET_ALL_USERS = "SELECT * FROM users order by iduser desc LIMIT 5 OFFSET ?;";
    private static final String DEL_USER = "UPDATE users SET active = '0' WHERE iduser = ?;";
    private static final String ACTIVATE_USER = "UPDATE users SET active = '1' WHERE iduser = ?;";
    private static final String PAY_BILL = "UPDATE users SET cash = cash - ? WHERE iduser = ?;";
    private static final String ADD_MONEY = "UPDATE users SET cash = cash + ? WHERE iduser = ?;";
    private static final String SELECT_LOGINS = "SELECT login FROM users;";
    private static final String GET_USER_BY_ID = "SELECT * FROM users WHERE iduser = ?;";
    private static final String SEARCH_USER = "select * from users where login like ?;";


    private static String md5Apache(String st) {
        return DigestUtils.md5Hex(st);
    }

    /**
     * @param loginA    string user login
     * @param passwordA string user password
     * @return object User
     */
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
        String hash = md5Apache(passwordA);
        try {
            PreparedStatement ps = connection.prepareStatement(SELECT_USER_BY_LOGIN_AND_PASSWORD);
            ps.setString(1, loginA);
            ps.setString(2, hash);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt(5);
                type = UserType.valueOf(rs.getString(1).toUpperCase());
                active = rs.getBoolean(9);
                passNum = rs.getString(3);
                fullName = rs.getString(6);
                address = rs.getString(8);
                email = rs.getString(7);
                cash = rs.getInt(10);
                login = rs.getString(4);
                password = rs.getString(2);

            }
            return new User(login, password, fullName, passNum, email, address, cash, type, active, id);
        } catch (SQLException e) {
            LOG.error(e);
            throw new DaoException("AUTORIZATION ERROR", e);
        } finally {
            pool.putback(connection);
        }
    }


    /**
     * @param user object User
     * @return true if registration complete successfully
     */
    @Override
    public boolean registration(User user) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.retrieve();
        String hash = md5Apache(user.getPassword());

        try {
            PreparedStatement ps = connection.prepareStatement(REGISTRATION_USER);
            ps.setString(1, user.getLogin());
            ps.setString(2, hash);
            ps.setString(3, user.getPassNum());
            ps.setString(4, user.getFullName());
            ps.setString(5, user.getAddress());
            ps.setString(6, user.getEmail());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOG.error(e);
            throw new DaoException("REGISTRATION ERROR", e);
        } finally {
            pool.putback(connection);
        }
    }

    /**
     * @return List of all users from database
     */
    @Override
    public List<User> getAllUsers(int page) throws DaoException {
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
            ps.setInt(1, page);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt(5);
                type = UserType.valueOf(rs.getString(1).toUpperCase());
                active = rs.getBoolean(9);
                passNum = rs.getString(3);
                fullName = rs.getString(6);
                address = rs.getString(8);
                email = rs.getString(7);
                cash = rs.getInt(10);
                login = rs.getString(4);
                password = rs.getString(2);
                list.add(new User(login, password, fullName, passNum, email, address, cash, type, active, id));
            }
            return list;
        } catch (SQLException e) {
            LOG.error(e);
            throw new DaoException("GET ALL CAR ERROR!!!", e);
        } finally {
            pool.putback(connection);
        }
    }

    /**
     * @param id int id user
     * @return true if installation flag "active" to false successfully
     */
    @Override
    public boolean delUser(int id) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.retrieve();
        try (PreparedStatement ps = connection.prepareStatement(DEL_USER)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOG.error(e);
            throw new DaoException("DEL USER ERROR", e);
        } finally {
            pool.putback(connection);
        }
    }

    /**
     * @param sum int sum order receipt
     * @param id  int User id
     * @return true if payment successfully
     */
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
            LOG.error(e);
            throw new DaoException("PAY ERROR", e);
        } finally {
            pool.putback(connection);
        }
    }

    /**
     * @param id int user id
     * @return true if activate successfully
     */
    @Override
    public boolean activateUser(int id) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.retrieve();
        try (PreparedStatement ps = connection.prepareStatement(ACTIVATE_USER)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOG.error(e);
            throw new DaoException("ACTIVATE USER ERROR", e);
        } finally {
            pool.putback(connection);
        }
    }

    /**
     * @param sum int sum
     * @param id  int user id
     * @return true if adding money complete successfully
     */
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
            LOG.error(e);
            throw new DaoException("ADD MONEY ERROR", e);
        } finally {
            pool.putback(connection);
        }
    }

    /**
     * @param login string login
     * @return false if user contain in userlist
     */
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
            LOG.error(e);
            throw new DaoException("SELECT USER BY ID ERROR", e);
        } finally {
            pool.putback(connection);
        }
    }

    /**
     * @param id int user id
     * @return User object
     */
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
                type = UserType.valueOf(rs.getString(1).toUpperCase());
                active = rs.getBoolean(9);
                passNum = rs.getString(3);
                fullName = rs.getString(6);
                address = rs.getString(8);
                email = rs.getString(7);
                cash = rs.getInt(10);
                login = rs.getString(4);
                password = rs.getString(2);
            }
            return new User(login, password, fullName, passNum, email, address, cash, type, active, id);
        } catch (SQLException e) {
            LOG.error(e);
            throw new DaoException("GET USER BY ID ERROR", e);
        } finally {
            pool.putback(connection);

        }
    }

    /**
     * @param searchLogin String login or half login name
     * @return List of {@link User} objects
     */
    @Override
    public List<User> searchUser(String searchLogin) throws DaoException {
        String login;
        int id;
        String password;
        String fullName;
        String passNum;
        String email;
        String address;
        UserType type;
        int cash;
        boolean active;
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.retrieve();
        List<User> list = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(SEARCH_USER)) {
            ps.setString(1, "%" + searchLogin + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt(5);
                type = UserType.valueOf(rs.getString(1).toUpperCase());
                active = rs.getBoolean(9);
                passNum = rs.getString(3);
                fullName = rs.getString(6);
                address = rs.getString(8);
                email = rs.getString(7);
                cash = rs.getInt(10);
                login = rs.getString(4);
                password = rs.getString(2);
                list.add(new User(login, password, fullName, passNum, email, address, cash, type, active, id));
            }
            return list;
        } catch (SQLException e) {
            LOG.error(e);
            throw new DaoException("SEARCH USER ERROR", e);
        } finally {
            pool.putback(connection);

        }
    }
}
