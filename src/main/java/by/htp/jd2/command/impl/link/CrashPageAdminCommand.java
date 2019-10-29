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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.jd2.command.Command;
import by.htp.jd2.controller.JSPPageName;
import by.htp.jd2.entity.Car;
import by.htp.jd2.entity.Crash;
import by.htp.jd2.entity.User;
import by.htp.jd2.service.ServiceException;
import by.htp.jd2.service.ServiceProvider;

/**
 * go to page with all crashs(additional bills) of all users
 *
 * @author alexey
 */
public class CrashPageAdminCommand implements Command {
    private static final Logger LOG = LogManager.getLogger(CrashPageAdminCommand.class.getName());
    private static final String error = "go to crash page admin page error";
    private static final String debug = "Go to crash page admin command";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") == null) {
            response.sendRedirect("index.jsp");
            LOG.error(error);
        } else {
            try {
                List<Crash> crashs = ServiceProvider.getInstance().getCrashService().getAllCrashs();
                session.setAttribute("allCrashs", crashs);

                Set<Car> cars = new HashSet<Car>();
                Car car = null;
                for (Crash crash : crashs) {
                    car = ServiceProvider.getInstance().getCarService().getCarById(crash.getIdCar());
                    cars.add(car);
                }
                request.setAttribute("carsO", cars);

                Set<User> users = new HashSet<User>();
                User user = null;
                for (Crash crash : crashs) {
                    user = ServiceProvider.getInstance().getUserService().getUserById(crash.getIdUser());
                    users.add(user);
                }
                request.setAttribute("usersO", users);

            } catch (ServiceException e) {
                LOG.error(error + e);
                e.printStackTrace();
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.CRASH_PAGE_ADMIN);
            dispatcher.forward(request, response);
        }
        LOG.debug(debug);
    }

}
