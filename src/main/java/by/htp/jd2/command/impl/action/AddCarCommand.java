package by.htp.jd2.command.impl.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp.jd2.dao.connectionpool.ConnectionListener;
import by.htp.jd2.service.CarService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.jd2.command.Command;
import by.htp.jd2.controller.RequestParameterName;
import by.htp.jd2.entity.Car;
import by.htp.jd2.entity.TransmissionType;
import by.htp.jd2.service.ServiceException;
import org.springframework.stereotype.Component;

/**
 * add new car to database
 *
 * @author alexey
 */
@Component
public class AddCarCommand implements Command {
    private static final Logger LOG = LogManager.getLogger(AddCarCommand.class.getName());
    private static final String ERROR = "Add car ERROR";
    private static final String DEBUG = "Add car command";

    private CarService carService = (CarService) ConnectionListener.getContextBean(CarService.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("user") == null) {
            response.sendRedirect("index.jsp");
            LOG.error(ERROR);
        } else {
            try {
                String name = request.getParameter(RequestParameterName.REQ_PARAM_CAR_NAME);
                int price = Integer.parseInt(request.getParameter(RequestParameterName.REQ_PARAM_CAR_PRICE));
                String fuel = request.getParameter(RequestParameterName.REQ_PARAM_CAR_FUEL);
                String color = request.getParameter(RequestParameterName.REQ_PARAM_CAR_COLOR);
                String body = request.getParameter(RequestParameterName.REQ_PARAM_CAR_BODY);
                TransmissionType transmission = TransmissionType
                        .valueOf(request.getParameter(RequestParameterName.REQ_PARAM_TRANSMISSION).toUpperCase());
                Car car = new Car(name, price, fuel, color, body, transmission, true);
                carService.addNewCar(car);
            } catch (ServiceException | NumberFormatException e) {
                LOG.error(ERROR, e);
                if (session != null) {
                    session.setAttribute("error", ERROR);
                }
            }
            response.sendRedirect("controller?command=CONTROL_CAR_PAGE");
            LOG.debug(DEBUG);
        }
    }
}
