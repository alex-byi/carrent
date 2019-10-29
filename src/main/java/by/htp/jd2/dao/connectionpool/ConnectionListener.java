package by.htp.jd2.dao.connectionpool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Connection Listener. Load connection pool in memory
 *
 * @author alexey
 */
public class ConnectionListener implements ServletContextListener {

    private static final Logger LOG = LogManager.getLogger(ConnectionListener.class.getName());
    private static DBResourceManager dbResourseManager = DBResourceManager.getInstance();
    private final static String DRIVER = dbResourseManager.getValue(DBParameter.DB_DRIVER);

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        try {
            Class.forName(DRIVER);
            ConnectionPool.getInstance();
        } catch (ClassNotFoundException e) {
            LOG.error(e);
            throw new RuntimeException(e);
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
