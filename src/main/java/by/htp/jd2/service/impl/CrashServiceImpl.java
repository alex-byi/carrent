package by.htp.jd2.service.impl;

import java.util.List;

import by.htp.jd2.dao.DaoException;
import by.htp.jd2.dao.DaoProvider;
import by.htp.jd2.dao.impl.SqlCrashDao;
import by.htp.jd2.entity.Crash;
import by.htp.jd2.service.CrashService;
import by.htp.jd2.service.ServiceException;
import by.htp.jd2.service.validation.CrashDataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CrashServiceImpl implements CrashService {

    private static final CrashDataValidator validator = CrashDataValidator.getInstance();
    private static final Logger LOG = LogManager.getLogger(CrashServiceImpl.class.getName());

    @Override
    public List<Crash> getAllCrashs() throws ServiceException {
        SqlCrashDao crashDao = DaoProvider.getInstance().getCrashDao();
        List<Crash> list;
        try {
            list = crashDao.getAllCrashs();
        } catch (DaoException e) {
            LOG.error(e);
            throw new ServiceException(e);
        }
        if (validator.checkCrashsList(list)) {
            throw new ServiceException("List<Crash> no valid");
        }
        return list;
    }

    @Override
    public int addCrash(Crash crash) throws ServiceException {
        int id;
        if (!validator.checkCrashInfo(crash)) {
            throw new ServiceException("Crash data is no valid");
        }
        SqlCrashDao crashDao = DaoProvider.getInstance().getCrashDao();
        try {
            id = crashDao.addCrash(crash);
        } catch (DaoException e) {
            LOG.error(e);
            throw new ServiceException(e);
        }
        return id;
    }

    @Override
    public List<Crash> getUsersCrashs(int idUser) throws ServiceException {
        SqlCrashDao crashDao = DaoProvider.getInstance().getCrashDao();
        List<Crash> list;
        try {
            list = crashDao.getUsersCrashs(idUser);
        } catch (DaoException e) {
            LOG.error(e);
            throw new ServiceException(e);
        }
        if (validator.checkCrashsList(list)) {
            throw new ServiceException("List<Crash> no valid");
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
