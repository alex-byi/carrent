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
import by.htp.jd2.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.jd2.command.Command;
import by.htp.jd2.controller.JSPPageName;
import by.htp.jd2.entity.Car;
import by.htp.jd2.entity.Order;
import by.htp.jd2.entity.User;

/**
 * go to page with all orders of all users
 *
 * @author alexey
 */
public class OrderPageCommand implements Command {

    private static final Logger LOG = LogManager.getLogger(OrderPageCommand.class.getName());
    private static final String DEBUG = "Go to order page command";
    private static final String ERROR = "Go to order page command ERROR";
    private static final String CURRENT_PAGE_PARAMETER = "currentPage";

    private UserService userService = (UserService) ConnectionListener.getContextBean(UserService.class);

    private CarService carService = (CarService) ConnectionListener.getContextBean(CarService.class);

    private OrderService orderService = (OrderService) ConnectionListener.getContextBean(OrderService.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(false);

        int currentPage;

        if (session != null && session.getAttribute("user") == null) {
            response.sendRedirect("index.jsp");
            LOG.error(ERROR);
        } else {
            try {

                if (request.getParameter(CURRENT_PAGE_PARAMETER) == null) {
                    currentPage = 0;
                } else {
                    currentPage = Integer.parseInt(request.getParameter(CURRENT_PAGE_PARAMETER));
                }
                int page = currentPage * 5;

                List<Order> orders = orderService.getAllOrders(page);
                request.setAttribute("allOrders", orders);
                request.setAttribute(CURRENT_PAGE_PARAMETER, currentPage);

                Set<Car> cars = new HashSet<>();
                Car car;
                for (Order order : orders) {
                    car = carService.getCarById(order.getIdCar());
                    cars.add(car);
                }
                request.setAttribute("carsO", cars);

                Set<User> users = new HashSet<>();
                User user;
                for (Order order : orders) {
                    user = userService.getUserById(order.getIdUser());
                    users.add(user);
                }
                request.setAttribute("usersO", users);

            } catch (ServiceException e) {
                LOG.error(ERROR, e);
                request.getSession().invalidate();
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.ORDER_PAGE);
            dispatcher.forward(request, response);
            LOG.debug(DEBUG);
        }
    }

}
