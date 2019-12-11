package by.htp.jd2.command.impl.action;

import by.htp.jd2.command.Command;
import by.htp.jd2.controller.JSPPageName;
import by.htp.jd2.dao.connectionpool.ConnectionListener;
import by.htp.jd2.entity.Car;
import by.htp.jd2.entity.Order;
import by.htp.jd2.service.CarService;
import by.htp.jd2.service.OrderService;
import by.htp.jd2.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

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
@Component
public class UserOrdersSearchCommand implements Command {

    private static final Logger LOG = LogManager.getLogger(UserOrdersSearchCommand.class.getName());
    private static final String ERROR = "SEARCH USER ORDERS ERROR";
    private static final String DEBUG = "SEARCH USER ORDERS command";

    private CarService carService = (CarService) ConnectionListener.getContextBean(CarService.class);
    private OrderService orderService = (OrderService) ConnectionListener.getContextBean(OrderService.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int idUser;
        HttpSession session = request.getSession();
        if (session != null && session.getAttribute("user") == null) {
            response.sendRedirect("index.jsp");
            LOG.error(ERROR);
        } else {
            try {
                idUser = Integer.parseInt(request.getParameter("idUser"));
                List<Order> list = orderService.userOrders(idUser);
                request.setAttribute("userOrders", list);

                Set<Car> cars = new HashSet<>();
                Car car;
                for (Order order : list) {
                    car = carService.getCarById(order.getIdCar());
                    cars.add(car);
                }
                request.setAttribute("carsO", cars);

            } catch (ServiceException | NumberFormatException e) {
                LOG.error(ERROR, e);
                if (session != null) {
                    session.setAttribute("error", ERROR);
                }
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.CONTROL_USERS_PAGE);
            dispatcher.forward(request, response);
            LOG.debug(DEBUG);
        }
    }
}
