package by.htp.jd2.dao;

import by.htp.jd2.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {

    User authorization(String login, String password) throws DaoException, SQLException;

    boolean registration(User user) throws DaoException;

    List<User> getAllUsers() throws DaoException;

    boolean delUser(int id) throws DaoException;

    boolean activateUser(int id) throws DaoException;

    boolean pay(int sum, int id) throws DaoException;

    boolean addMoney(int sum, int id) throws DaoException;

    boolean checkUser(String login) throws DaoException;

    User getUserById(int id) throws DaoException;

}
