package by.htp.jd2.dao.impl;

import by.htp.jd2.dao.DaoException;
import org.junit.Assert;
import org.junit.Test;


public class SQLCarDaoTest {

    @Test
    public void delCar() throws DaoException {
        SQLCarDao sqlCarDao = new SQLCarDao();
        boolean expected = sqlCarDao.delCar(1);
        Assert.assertTrue(expected);
    }

    @Test
    public void activateCar() throws DaoException {
        SQLCarDao sqlCarDao = new SQLCarDao();
        boolean expected = sqlCarDao.delCar(1);
        Assert.assertTrue(expected);
    }


}