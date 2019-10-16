package by.htp.jd2.dao;

import java.util.List;

import by.htp.jd2.entity.Crash;

public interface CrashDao {
    List<Crash> getAllCrashs() throws DaoException;

    int addCrash(Crash crash) throws DaoException;

    List<Crash> getUsersCrashs(int idUser) throws DaoException;

    boolean setCrashPayment(int idCrash) throws DaoException;

    Crash getCrashById(int idCrash) throws DaoException;
}
