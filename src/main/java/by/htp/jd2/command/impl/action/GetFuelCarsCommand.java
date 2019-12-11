package by.htp.jd2.command.impl.action;

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
 * get list of cars with some fuel type
 *
 * @author alexey
 */
@Component
public class GetFuelCarsCommand implements Command {
    private static final Logger LOG = LogManager.getLogger(GetFuelCarsCommand.class.getName());
    private static final String ERROR = "Get fuel cars USER ERROR";
    private static final String DEBUG = "Get fuel cars command";

    private CarService carService = (CarService) ConnectionListener.getContextBean(CarService.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String fuel = request.getParameter("searchFuel");
        HttpSession session = request.getSession();
        if (session != null && session.getAttribute("user") == null) {
            response.sendRedirect("index.jsp");
            LOG.error(ERROR);
        } else {
            try {
                List<Car> list = carService.getFuelCars(fuel);
                request.setAttribute("fuelCar", list);
                request.setAttribute("fuelStr", fuel);
            } catch (ServiceException e) {
                LOG.error(ERROR, e);
                if (session != null) {
                    session.setAttribute("error", ERROR);
                }
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.CONTROL_CAR_PAGE);
            dispatcher.forward(request, response);
            LOG.debug(DEBUG);
        }

    }

}
