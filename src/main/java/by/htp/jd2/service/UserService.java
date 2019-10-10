package by.htp.jd2.service;

import by.htp.jd2.entity.User;

import java.util.List;

public interface UserService {

    User authorization(String login, String password) throws ServiceException;

    boolean registration(User user) throws ServiceException;

    List<User> getAllUsers() throws ServiceException;

    boolean delUser(int id) throws ServiceException;

    boolean activateUser(int id) throws ServiceException;

    boolean pay(int sum, int id) throws ServiceException;

    boolean addMoney(int sum, int id) throws ServiceException;

    boolean checkUser(String login) throws ServiceException;

    User getUserById(int id) throws ServiceException;
}
