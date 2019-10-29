package by.htp.jd2.command.impl.link;

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
 * go to "Control car" page
 *
 * @author alexey
 */
public class ControlCarPageCommand implements Command {
    private static final Logger LOG = LogManager.getLogger(ControlCarPageCommand.class.getName());
    private static final String error = "go to Control car page error";
    private static final String debug = "Go to Car control page command";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("user") == null) {
            response.sendRedirect("index.jsp");
            LOG.error(error);
        } else {
            try {
                List<Car> cars = ServiceProvider.getInstance().getCarService().getAllCars();
                session.setAttribute("cars", cars);
            } catch (ServiceException e) {
                LOG.error(error + e);
                e.printStackTrace();
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.CONTROL_CAR_PAGE);
            dispatcher.forward(request, response);
        }
        LOG.debug(debug);
    }

}
