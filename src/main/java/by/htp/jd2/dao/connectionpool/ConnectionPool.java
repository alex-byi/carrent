package by.htp.jd2.dao.connectionpool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author alexey
 * connection pool singleton
 */
public final class ConnectionPool {

    private static final Logger LOG = LogManager.getLogger(ConnectionPool.class.getName());

    private static DBResourceManager dbResourseManager = DBResourceManager.getInstance();
    private final static String URL = dbResourseManager.getValue(DBParameter.DB_URL);
    private final static String LOGIN = dbResourseManager.getValue(DBParameter.DB_USER);
    private final static String PASSWORD = dbResourseManager.getValue(DBParameter.DB_PASSWORD);
    private final static int POLL_SIZE = getPoll();
    private final static String DRIVER = dbResourseManager.getValue(DBParameter.DB_DRIVER);

    private static ConnectionPool INSTANCE;
    private BlockingQueue<Connection> connections;

    private static int getPoll() {
        try {
            return Integer.parseInt(dbResourseManager.getValue(DBParameter.DB_POLL_SIZE));
        } catch (NumberFormatException e) {
            LOG.error(e);
            return 4;
        }
    }

    private ConnectionPool() {
        Connection connection;

        try {
            connections = new ArrayBlockingQueue<>(POLL_SIZE);
            Class.forName(DRIVER);

            for (int i = 0; i < POLL_SIZE; i++) {
                connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
                if (connection != null) {
                    connections.put(connection);
                }
            }
        } catch (InterruptedException | SQLException | ClassNotFoundException e) {
            LOG.error(e);
            throw new RuntimeException(e);
        }

    }

    public static synchronized ConnectionPool getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ConnectionPool();
        }
        return INSTANCE;
    }

    /**
     * take connection from pool
     *
     * @return free connection
     */
    public Connection retrieve() {
        Connection connection = null;
        try {
            connection = connections.take();
        } catch (InterruptedException e) {
            LOG.error(e);
            throw new RuntimeException(e);
        }
        return connection;
    }

    /**
     * put back connection to pool
     */
    public void putback(Connection connection) {
        if (connection != null) {
            try {
                connection.setAutoCommit(true);
                connections.put(connection);
            } catch (InterruptedException | SQLException e) {
                LOG.error(e);
                throw new RuntimeException(e);
            }
        }
    }
}
