package by.htp.jd2.service;

import java.util.List;

import by.htp.jd2.entity.Crash;

/**
 * Service of additional crash bills
 *
 * @author alexey
 */
public interface CrashService {

    /**
     * Service of Returns a list of all additional bills from the database
     *
     * @return List of crash objects
     */
    List<Crash> getAllCrashs() throws ServiceException;

    /**
     * Service of Adds information about a new accident to the database
     *
     * @param crash Crash object
     * @return int Last Added crash bill ID
     */
    int addCrash(Crash crash) throws ServiceException;

    /**
     * Service of Returns a list of all additional user bills from the database
     *
     * @param idUser int user ID
     * @return List crash objects
     */
    List<Crash> getUsersCrashs(int idUser) throws ServiceException;

    /**
     * Service of Sets the payment flag to true in the database
     *
     * @param idCrash int Crash ID
     * @return true if payment successfully
     */
    boolean setCrashPayment(int idCrash) throws ServiceException;

    /**
     * Service of Returns an Crash object by its ID from the database
     *
     * @param idCrash int Crash id
     * @return Crash object
     */
    Crash getCrashById(int idCrash) throws ServiceException;

}
