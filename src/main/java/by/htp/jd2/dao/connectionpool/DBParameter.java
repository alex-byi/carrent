package by.htp.jd2.dao.connectionpool;


/**
 * parameters for connection to database
 *
 * @author alexey
 */
final class DBParameter {
    private DBParameter() {
    }

    static final String DB_DRIVER = "db.driver";
    static final String DB_URL = "db.url";
    static final String DB_USER = "db.user";
    static final String DB_PASSWORD = "db.password";
    static final String DB_POLL_SIZE = "db.poolsize";
}