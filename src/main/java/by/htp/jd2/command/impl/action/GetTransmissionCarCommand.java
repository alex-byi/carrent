package by.htp.jd2.command.impl.action;

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
 * get list of cars with some transmission type
 *
 * @author alexey
 */
public class GetTransmissionCarCommand implements Command {
    private static final Logger LOG = LogManager.getLogger(GetTransmissionCarCommand.class.getName());
    private static final String debug = "Get transmission cars command";
    private static final String error = "Get transmission cars USER ERROR";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String transmission = request.getParameter("searchtransmission");
        HttpSession session = request.getSession();
        if (session != null && session.getAttribute("user") == null) {
            response.sendRedirect("index.jsp");
            LOG.error(error);
        } else {
            try {
                List<Car> list = ServiceProvider.getInstance().getCarService().getTransmissionCar(transmission);
                request.setAttribute("transmissionCar", list);
                request.setAttribute("transmissionStr", transmission);
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
