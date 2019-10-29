package by.htp.jd2.command.impl.action;

import by.htp.jd2.command.Command;
import by.htp.jd2.controller.JSPPageName;
import by.htp.jd2.entity.Car;
import by.htp.jd2.entity.Order;
import by.htp.jd2.service.ServiceException;
import by.htp.jd2.service.ServiceProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Get list orders by user id
 *
 * @author alexey
 */
public class UserOrdersSearchCommand implements Command {

    private static final Logger LOG = LogManager.getLogger(UserOrdersSearchCommand.class.getName());
    private static final String error = "SEARCH USER ORDERS ERROR";
    private static final String debug = "SEARCH USER ORDERS command";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int idUser;
        HttpSession session = request.getSession();
        if (session != null && session.getAttribute("user") == null) {
            response.sendRedirect("index.jsp");
            LOG.error(error);
        } else {
            try {
                idUser = Integer.parseInt(request.getParameter("idUser"));
                List<Order> list = ServiceProvider.getInstance().getOrderService().userOrders(idUser);
                request.setAttribute("userOrders", list);

                Set<Car> cars = new HashSet<>();
                Car car;
                for (Order order : list) {
                    car = ServiceProvider.getInstance().getCarService().getCarById(order.getIdCar());
                    cars.add(car);
                }
                request.setAttribute("carsO", cars);


            } catch (ServiceException | NumberFormatException e) {
                LOG.error(error + e);
                e.printStackTrace();
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.CONTROL_USERS_PAGE);
            dispatcher.forward(request, response);
            LOG.debug(debug);
        }
    }
}
