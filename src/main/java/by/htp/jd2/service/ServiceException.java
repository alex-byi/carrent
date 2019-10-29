package by.htp.jd2.service;


/**
 * Service exception
 *
 * @author alexey
 */
public class ServiceException extends Exception {

    private static final long serialVersionUID = 1L;

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Exception e) {
        super(e);
    }

    public ServiceException(String string, Exception e) {
        super(string, e);
    }

}
