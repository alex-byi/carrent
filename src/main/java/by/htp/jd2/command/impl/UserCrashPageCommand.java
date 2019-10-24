package by.htp.jd2.command.impl;

import java.io.IOException;
import java.util.ArrayList;
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
 * go to page where user can see all his additional bills
 *
 * @author alexey
 */
public class UserCrashPageCommand implements Command {
    private static final Logger LOG = LogManager.getLogger(UserCrashPageCommand.class.getName());
    private static final String debug = "Go to USER crash page command";
    private static final String error = "Go to USER crash page command ERROR";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") == null) {
            response.sendRedirect("index.jsp");
            LOG.error(error);
        } else {
            List<Crash> crashs = new ArrayList<>();
            User user = (User) session.getAttribute("user");
            try {
                crashs = ServiceProvider.getInstance().getCrashService().getUsersCrashs(user.getId());

                Set<Car> cars = new HashSet<>();
                for (Crash crash : crashs) {
                    cars.add(ServiceProvider.getInstance().getCarService().getCarById(crash.getIdCar()));
                }
                request.setAttribute("cars", cars);
            } catch (ServiceException e) {
                e.printStackTrace();
                LOG.error(error + e);
            }

            if (crashs.size() != 0) {
                request.setAttribute("allCrashs", crashs);
            } else {
                request.setAttribute("allCrashs", null);
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.USER_CRASH_PAGE);
            dispatcher.forward(request, response);
            LOG.debug(debug);
        }

    }

}
