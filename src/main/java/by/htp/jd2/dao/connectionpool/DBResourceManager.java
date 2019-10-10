package by.htp.jd2.dao.connectionpool;

import java.util.ResourceBundle;

class DBResourceManager {
    private final static DBResourceManager instance = new DBResourceManager();
    private ResourceBundle bundle =
            ResourceBundle.getBundle("db");

    static DBResourceManager getInstance() {
        return instance;
    }

    String getValue(String key) {
        return bundle.getString(key);
    }
}
