package by.htp.jd2.service;

import java.util.List;

import by.htp.jd2.entity.Crash;

public interface CrashService {
    List<Crash> getAllCrashs() throws ServiceException;

    int addCrash(Crash crash, int orderId) throws ServiceException;

    List<Crash> getUsersCrashs(int idUser) throws ServiceException;

    boolean setCrashPayment(int idCrash) throws ServiceException;

    Crash getCrashById(int idCrash) throws ServiceException;

}
