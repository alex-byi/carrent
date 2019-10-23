package by.htp.jd2.service.validation;

public class UserDataValidator {
    private static final UserDataValidator instance = new UserDataValidator();

    private UserDataValidator() {
    }

    public boolean checkLoginInfo(String login, String password) {
        return !loginVal(login) && !passwordVal(password);
    }

    private static boolean loginVal(String login) {
        return !login.isEmpty();
    }

    private static boolean passwordVal(String password) {
        return !password.isEmpty();
    }

    public static UserDataValidator getInstance() {
        return instance;
    }

}
