package by.htp.jd2.service.impl;

import by.htp.jd2.dao.DaoException;
import by.htp.jd2.dao.UserDao;
import by.htp.jd2.entity.User;
import by.htp.jd2.service.ServiceException;
import by.htp.jd2.service.UserService;
import by.htp.jd2.service.validation.UserDataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static final UserDataValidator validator = UserDataValidator.getInstance();
    private static final Logger LOG = LogManager.getLogger(UserServiceImpl.class.getName());


    UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User authorization(String login, String password) throws ServiceException {
        if (validator.checkLoginInfo(login, password)) {
            throw new ServiceException("Login or password no valid");
        } else {
//            SQLUserDao userDao = DaoProvider.getInstance().getUserDao();
            User user;
            try {
                user = userDao.authorization(login, password);
            } catch (DaoException e) {
                LOG.error(e);
                throw new ServiceException(e);
            }
            return user;
        }
    }

    @Override
    public boolean registration(User user) throws ServiceException {
        if (validator.checkUserInfo(user)) {
            throw new ServiceException("Registration info no valid");
        } else {
//            SQLUserDao userDao = DaoProvider.getInstance().getUserDao();
            try {
                return userDao.registration(user);
            } catch (DaoException e) {
                LOG.error(e);
                throw new ServiceException(e);
            }
        }
    }

    @Override
    public List<User> getAllUsers(int page) throws ServiceException {
//        SQLUserDao userDao = DaoProvider.getInstance().getUserDao();
        List<User> list;
        try {
            list = userDao.getAllUsers(page);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        if (!validator.checkUsersList(list)) {
            throw new ServiceException("List<User> no valid");
        }
        return list;
    }

    @Override
    public boolean delUser(int id) throws ServiceException {
//        SQLUserDao userDao = DaoProvider.getInstance().getUserDao();
        try {
            return userDao.delUser(id);
        } catch (DaoException e) {
            LOG.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean activateUser(int id) throws ServiceException {
//        SQLUserDao userDao = DaoProvider.getInstance().getUserDao();
        try {
            return userDao.activateUser(id);
        } catch (DaoException e) {
            LOG.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean pay(int sum, int id) throws ServiceException {
//        SQLUserDao userDao = DaoProvider.getInstance().getUserDao();
        try {
            return userDao.pay(sum, id);
        } catch (DaoException e) {
            LOG.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean addMoney(int sum, int id) throws ServiceException {
//        SQLUserDao userDao = DaoProvider.getInstance().getUserDao();
        try {
            return userDao.addMoney(sum, id);
        } catch (DaoException e) {
            LOG.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean checkUser(String login) throws ServiceException {
        if (validator.checkUserLogin(login)) {
            throw new ServiceException("Login no valid");
        }
//        SQLUserDao userDao = DaoProvider.getInstance().getUserDao();
        try {
            return userDao.checkUser(login);
        } catch (DaoException e) {
            LOG.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public User getUserById(int id) throws ServiceException {
//        SQLUserDao userDao = DaoProvider.getInstance().getUserDao();
        User user;
        try {
            user = userDao.getUserById(id);
        } catch (DaoException e) {
            LOG.error(e);
            throw new ServiceException(e);
        }
        if (validator.checkUserInfo(user)) {
            throw new ServiceException("User no valid");
        }
        return user;
    }

    @Override
    public List<User> searchUser(String searchLogin) throws ServiceException {
//        SQLUserDao userDao = DaoProvider.getInstance().getUserDao();
        List<User> list;
        try {
            list = userDao.searchUser(searchLogin);
        } catch (DaoException e) {
            LOG.error(e);
            throw new ServiceException(e);
        }
        return list;
    }
}
