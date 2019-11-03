package by.htp.jd2.service.validation;

import by.htp.jd2.entity.User;

import java.util.List;

public class UserDataValidator {
    private static final UserDataValidator instance = new UserDataValidator();

    private UserDataValidator() {
    }

    public boolean checkLoginInfo(String login, String password) {
        return !loginVal(login) && !passwordVal(password);
    }

    public boolean checkUserInfo(User user) {
        return user == null || user.getLogin().isEmpty() || user.getFullName().isEmpty() || user.getPassNum().isEmpty()
                || user.getEmail().isEmpty() || user.getAddress().isEmpty();
    }

    public boolean checkUsersList(List<User> list) {
        User trueUser = new User();
        for (User user : list) {
            if (user.getClass() != trueUser.getClass()) {
                return false;
            }
        }
        return true;
    }

    public boolean checkUserLogin(String login) {
        return login.isEmpty();
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
