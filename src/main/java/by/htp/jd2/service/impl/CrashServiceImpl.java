package by.htp.jd2.service.impl;

import java.util.ArrayList;
import java.util.List;

import by.htp.jd2.dao.DaoException;
import by.htp.jd2.dao.DaoProvider;
import by.htp.jd2.dao.impl.SqlCrashDao;
import by.htp.jd2.entity.Crash;
import by.htp.jd2.service.CrashService;
import by.htp.jd2.service.ServiceException;

public class CrashServiceImpl implements CrashService {

    @Override
    public List<Crash> getAllCrashs() throws ServiceException {
        SqlCrashDao crashDao = DaoProvider.getInstance().getCrashDao();
        List<Crash> list = new ArrayList<>();
        try {
            list = crashDao.getAllCrashs();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return list;
    }

    @Override
    public int addCrash(Crash crash, int orderId) throws ServiceException {
        int id = 0;
        SqlCrashDao crashDao = DaoProvider.getInstance().getCrashDao();
        try {
            id = crashDao.addCrash(crash, orderId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return id;
    }

    @Override
    public List<Crash> getUsersCrashs(int idUser) throws ServiceException {
        SqlCrashDao crashDao = DaoProvider.getInstance().getCrashDao();
        List<Crash> list = new ArrayList<>();
        try {
            list = crashDao.getUsersCrashs(idUser);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return list;
    }

    @Override
    public boolean setCrashPayment(int idCrash) throws ServiceException {
        SqlCrashDao crashDao = DaoProvider.getInstance().getCrashDao();
        try {
            return crashDao.setCrashPayment(idCrash);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Crash getCrashById(int idCrash) throws ServiceException {
        SqlCrashDao crashDao = DaoProvider.getInstance().getCrashDao();
        try {
            return crashDao.getCrashById(idCrash);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

    }

}
