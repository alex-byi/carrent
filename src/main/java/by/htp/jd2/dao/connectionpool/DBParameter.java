package by.htp.jd2.dao.connectionpool;


/**
 * parameters for connection to database
 *
 * @author alexey
 */
final class DBParameter {
    private DBParameter() {
    }

//    static final String DB_DRIVER = "db.driver";
//    static final String DB_URL = "db.url";
//    static final String DB_USER = "db.user";
//    static final String DB_PASSWORD = "db.password";
//    static final String DB_POLL_SIZE = "db.poolsize";

    static final String PSQL_DRIVER = "psql.driver";
    static final String PSQL_URL = "psql.url";
    static final String PSQL_USER = "psql.user";
    static final String PSQL_PASSWORD = "psql.password";
    static final String PSQL_POLL_SIZE = "psql.poolsize";
}