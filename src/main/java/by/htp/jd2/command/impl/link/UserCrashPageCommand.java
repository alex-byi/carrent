package by.htp.jd2.command.impl.link;

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

import by.htp.jd2.dao.connectionpool.ConnectionListener;
import by.htp.jd2.service.CarService;
import by.htp.jd2.service.CrashService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.jd2.command.Command;
import by.htp.jd2.controller.JSPPageName;
import by.htp.jd2.entity.Car;
import by.htp.jd2.entity.Crash;
import by.htp.jd2.entity.User;
import by.htp.jd2.service.ServiceException;

/**
 * go to page where user can see all his additional bills
 *
 * @author alexey
 */
public class UserCrashPageCommand implements Command {
    private static final Logger LOG = LogManager.getLogger(UserCrashPageCommand.class.getName());
    private static final String DEBUG = "Go to USER crash page command";
    private static final String ERROR = "Go to USER crash page command ERROR";


    private CarService carService = (CarService) ConnectionListener.getContextBean(CarService.class);
    private CrashService crashService = (CrashService) ConnectionListener.getContextBean(CrashService.class);



    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") == null) {
            response.sendRedirect("index.jsp");
            LOG.error(ERROR);
        } else {
            if (session != null) {
                List<Crash> crashs = new ArrayList<>();
                User user = (User) session.getAttribute("user");

                try {
                    crashs = crashService.getUsersCrashs(user.getId());

                    Set<Car> cars = new HashSet<>();
                    for (Crash crash : crashs) {
                        cars.add(carService.getCarById(crash.getIdCar()));
                    }
                    request.setAttribute("cars", cars);
                } catch (ServiceException e) {
                    LOG.error(ERROR, e);
                }

                if (!crashs.isEmpty()) {
                    request.setAttribute("allCrashs", crashs);
                } else {
                    request.setAttribute("allCrashs", null);
                }

                RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.USER_CRASH_PAGE);
                dispatcher.forward(request, response);
                LOG.debug(DEBUG);
            }
        }

    }

}
