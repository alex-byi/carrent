package by.htp.jd2.dao.connectionpool;

import java.util.ResourceBundle;

/**
 * resource manager
 *
 * @author alexey
 *
 */
class DBResourceManager {
    private final static DBResourceManager instance = new DBResourceManager();
    //    private ResourceBundle bundle =
//            ResourceBundle.getBundle("db");
    private ResourceBundle bundle =
            ResourceBundle.getBundle("psql");

    static DBResourceManager getInstance() {
        return instance;
    }

    String getValue(String key) {
        return bundle.getString(key);
    }
}
