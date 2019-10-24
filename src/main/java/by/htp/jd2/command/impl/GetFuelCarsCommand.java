package by.htp.jd2.command.impl;

import java.io.IOException;
import java.util.List;

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
import by.htp.jd2.service.ServiceException;
import by.htp.jd2.service.ServiceProvider;

/**
 * get list of cars with some fuel type
 *
 * @author alexey
 */
public class GetFuelCarsCommand implements Command {
    private static final Logger LOG = LogManager.getLogger(GetFuelCarsCommand.class.getName());
    private static final String error = "Get fuel cars USER ERROR";
    private static final String debug = "Get fuel cars command";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String fuel = request.getParameter("searchFuel");
        HttpSession session = request.getSession();
        if (session != null && session.getAttribute("user") == null) {
            response.sendRedirect("index.jsp");
            LOG.error(error);
        } else {
            try {
                List<Car> list = ServiceProvider.getInstance().getCarService().getFuelCars(fuel);
                request.setAttribute("fuelCar", list);
                request.setAttribute("fuelStr", fuel);
            } catch (ServiceException e) {
                LOG.error(error + e);
                e.printStackTrace();
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.CONTROL_CAR_PAGE);
            dispatcher.forward(request, response);
            LOG.debug(debug);
        }

    }

}
