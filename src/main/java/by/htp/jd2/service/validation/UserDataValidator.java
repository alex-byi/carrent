package by.htp.jd2.service.validation;

public class UserDataValidator {

    private static final UserDataValidator instance = new UserDataValidator();

    private UserDataValidator() {
    }

    public boolean check(String login, String password) {
        return true; //stub
    }

    public static UserDataValidator getInstance() {
        return instance;
    }

}
