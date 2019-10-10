package by.htp.jd2.dao;

import java.sql.SQLException;

public class DaoException extends Exception {


    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public DaoException(String autorization_error, SQLException e) {
    }

    public DaoException(String autorization_error, Exception e) {

    }

}
