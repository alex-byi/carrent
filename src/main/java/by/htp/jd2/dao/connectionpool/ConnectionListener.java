package by.htp.jd2.dao.connectionpool;

import by.htp.jd2.config.AppConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

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

    private static ApplicationContext context;

    @Override
    public void contextInitialized(ServletContextEvent sce) {

         context = new AnnotationConfigApplicationContext(AppConfig.class);

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

    public static Object getContextBean(Class<?> bean) {

        return context.getBean(bean);
    }

}
