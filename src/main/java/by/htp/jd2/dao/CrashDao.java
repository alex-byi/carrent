package by.htp.jd2.dao;

import java.util.List;

import by.htp.jd2.entity.Crash;

/**
 * @author alexey
 */
public interface CrashDao {

    /**
     * Returns a list of all additional bills from the database
     *
     * @return List of crash objects
     */
    List<Crash> getAllCrashs() throws DaoException;

    /**
     * Adds information about a new accident to the database
     *
     * @param crash Crash object
     * @return int Last Added crash bill ID
     */
    int addCrash(Crash crash) throws DaoException;

    /**
     * Returns a list of all additional user bills from the database
     *
     * @param idUser int user ID
     * @return List crash objects
     */
    List<Crash> getUsersCrashs(int idUser) throws DaoException;

    /**
     * Sets the payment flag to true in the database
     *
     * @param idCrash int Crash ID
     * @return true if payment successfully
     */
    boolean setCrashPayment(int idCrash) throws DaoException;

    /**
     * Returns an Crash object by its ID from the database
     *
     * @param idCrash int Crash id
     * @return Crash object
     */
    Crash getCrashById(int idCrash) throws DaoException;
}
