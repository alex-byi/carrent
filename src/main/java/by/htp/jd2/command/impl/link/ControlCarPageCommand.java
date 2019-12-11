package by.htp.jd2.command.impl.link;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp.jd2.dao.connectionpool.ConnectionListener;
import by.htp.jd2.service.CarService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.jd2.command.Command;
import by.htp.jd2.controller.JSPPageName;
import by.htp.jd2.entity.Car;
import by.htp.jd2.service.ServiceException;
import org.springframework.stereotype.Component;

/**
 * go to "Control car" page
 *
 * @author alexey
 */
@Component
public class ControlCarPageCommand implements Command {
    private static final Logger LOG = LogManager.getLogger(ControlCarPageCommand.class.getName());
    private static final String ERROR = "go to Control car page error";
    private static final String DEBUG = "Go to Car control page command";

    private CarService carService = (CarService) ConnectionListener.getContextBean(CarService.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("user") == null) {
            response.sendRedirect("index.jsp");
            LOG.error(ERROR);
        } else {
            try {
                if (session != null) {
                    List<Car> cars = carService.getAllCars();
                    session.setAttribute("cars", cars);
                }
            } catch (ServiceException e) {
                LOG.error(ERROR, e);
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.CONTROL_CAR_PAGE);
            dispatcher.forward(request, response);
        }
        LOG.debug(DEBUG);
    }

}
