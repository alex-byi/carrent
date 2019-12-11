package by.htp.jd2.command.impl.link;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp.jd2.dao.connectionpool.ConnectionListener;
import by.htp.jd2.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.jd2.command.Command;
import by.htp.jd2.controller.JSPPageName;
import by.htp.jd2.entity.Car;
import by.htp.jd2.entity.Crash;
import by.htp.jd2.entity.User;
import org.springframework.stereotype.Component;

/**
 * go to page with all crashs(additional bills) of all users
 *
 * @author alexey
 */
@Component
public class CrashPageAdminCommand implements Command {
    private static final Logger LOG = LogManager.getLogger(CrashPageAdminCommand.class.getName());
    private static final String ERROR = "go to crash page admin page error";
    private static final String DEBUG = "Go to crash page admin command";

    private UserService userService = (UserService) ConnectionListener.getContextBean(UserService.class);
    private CarService carService = (CarService) ConnectionListener.getContextBean(CarService.class);
    private CrashService crashService = (CrashService) ConnectionListener.getContextBean(CrashService.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") == null) {
            response.sendRedirect("index.jsp");
            LOG.error(ERROR);
        } else {
            try {
                if (session != null) {
                    List<Crash> crashs = crashService.getAllCrashs();
                    session.setAttribute("allCrashs", crashs);

                    Set<Car> cars = new HashSet<Car>();
                    Car car;
                    for (Crash crash : crashs) {
                        car = carService.getCarById(crash.getIdCar());
                        cars.add(car);
                    }
                    request.setAttribute("carsO", cars);

                    Set<User> users = new HashSet<User>();
                    User user;
                    for (Crash crash : crashs) {
                        user = userService.getUserById(crash.getIdUser());
                        users.add(user);
                    }
                    request.setAttribute("usersO", users);
                }
            } catch (ServiceException e) {
                LOG.error(ERROR, e);
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.CRASH_PAGE_ADMIN);
            dispatcher.forward(request, response);
        }
        LOG.debug(DEBUG);
    }

}
