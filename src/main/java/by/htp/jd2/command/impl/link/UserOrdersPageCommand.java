package by.htp.jd2.command.impl.link;

import java.io.IOException;
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
import by.htp.jd2.service.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.jd2.command.Command;
import by.htp.jd2.controller.JSPPageName;
import by.htp.jd2.entity.Car;
import by.htp.jd2.entity.Order;
import by.htp.jd2.entity.User;
import by.htp.jd2.service.ServiceException;
import org.springframework.stereotype.Component;

/**
 * go to page where user can see all his orders
 *
 * @author alexey
 */
@Component
public class UserOrdersPageCommand implements Command {
    private static final Logger LOG = LogManager.getLogger(UserOrdersPageCommand.class.getName());
    private static final String DEBUG = "User orders page command";
    private static final String ERROR = "User orders page command ERROR";

    private OrderService orderService = (OrderService) ConnectionListener.getContextBean(OrderService.class);
    private CarService carService = (CarService) ConnectionListener.getContextBean(CarService.class);


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("user") == null) {
            response.sendRedirect("index.jsp");
            LOG.error(ERROR);
        } else {
            if (session != null) {
                User user = (User) session.getAttribute("user");
                try {
                    List<Order> orders = orderService.userOrders(user.getId());
                    request.setAttribute("userOrders", orders);
                    Set<Car> cars = new HashSet<>();
                    Car car;
                    for (Order order : orders) {
                        car = carService.getCarById(order.getIdCar());
                        cars.add(car);
                    }
                    request.setAttribute("carsO", cars);

                } catch (ServiceException e) {
                    LOG.error(ERROR, e);
                }
                RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.USER_ORDERS);
                dispatcher.forward(request, response);
                LOG.debug(DEBUG);

            }
        }
    }
}
