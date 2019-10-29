package by.htp.jd2.dao;

import java.sql.SQLException;

/**
 * Dao Exceptions
 *
 * @author alexey
 */
public class DaoException extends Exception {

    private static final long serialVersionUID = 1L;

    public DaoException() {
        super();
    }

    public DaoException(String autorization_error, SQLException e) {
        super(autorization_error, e);
    }

    public DaoException(String autorization_error, Exception e) {
        super(autorization_error, e);

    }

}
