package by.htp.jd2.dao;

import by.htp.jd2.dao.impl.SQLCarDao;
import by.htp.jd2.dao.impl.SQLOrderDao;
import by.htp.jd2.dao.impl.SQLUserDao;
import by.htp.jd2.dao.impl.SqlCrashDao;

/**
 * Dao Provider
 *
 * @author alexey
 */
public class DaoProvider {
    private static final DaoProvider instance = new DaoProvider();

    private final SQLUserDao userDao = new SQLUserDao();
    private final SQLCarDao carDao = new SQLCarDao();
    private final SQLOrderDao orderDao = new SQLOrderDao();
    private final SqlCrashDao crashDao = new SqlCrashDao();

    public static DaoProvider getInstance() {
        return instance;
    }

    public SQLUserDao getUserDao() {
        return userDao;
    }

    public SQLCarDao getCarDao() {
        return carDao;
    }

    public SQLOrderDao getOrderDao() {
        return orderDao;
    }

    public SqlCrashDao getCrashDao() {
        return crashDao;
    }


}
