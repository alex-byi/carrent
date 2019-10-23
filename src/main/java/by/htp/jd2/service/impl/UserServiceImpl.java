package by.htp.jd2.service.impl;

import by.htp.jd2.dao.DaoException;
import by.htp.jd2.dao.DaoProvider;
import by.htp.jd2.dao.impl.SQLUserDao;
import by.htp.jd2.entity.User;
import by.htp.jd2.service.ServiceException;
import by.htp.jd2.service.UserService;
import by.htp.jd2.service.validation.UserDataValidator;

import java.util.List;

public class UserServiceImpl implements UserService {

    private static final UserDataValidator validator = UserDataValidator.getInstance();

    @Override
    public User authorization(String login, String password) throws ServiceException {
        if (validator.checkLoginInfo(login, password)) {
            throw new ServiceException("Login or password no valid");
        }
        SQLUserDao userDao = DaoProvider.getInstance().getUserDao();
        User user;
        try {
            user = userDao.authorization(login, password);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return user;
    }

    @Override
    public boolean registration(User user) throws ServiceException {
        SQLUserDao userDao = DaoProvider.getInstance().getUserDao();
        try {
            return userDao.registration(user);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> getAllUsers() throws ServiceException {
        SQLUserDao userDao = DaoProvider.getInstance().getUserDao();
        List<User> list;
        try {
            list = userDao.getAllUsers();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return list;
    }

    @Override
    public boolean delUser(int id) throws ServiceException {
        SQLUserDao userDao = DaoProvider.getInstance().getUserDao();
        try {
            return userDao.delUser(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean activateUser(int id) throws ServiceException {
        SQLUserDao userDao = DaoProvider.getInstance().getUserDao();
        try {
            return userDao.activateUser(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean pay(int sum, int id) throws ServiceException {
        SQLUserDao userDao = DaoProvider.getInstance().getUserDao();
        try {
            return userDao.pay(sum, id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean addMoney(int sum, int id) throws ServiceException {
        SQLUserDao userDao = DaoProvider.getInstance().getUserDao();
        try {
            return userDao.addMoney(sum, id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean checkUser(String login) throws ServiceException {
        SQLUserDao userDao = DaoProvider.getInstance().getUserDao();
        try {
            return userDao.checkUser(login);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User getUserById(int id) throws ServiceException {
        SQLUserDao userDao = DaoProvider.getInstance().getUserDao();
        User user;
        try {
            user = userDao.getUserById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return user;
    }
}
