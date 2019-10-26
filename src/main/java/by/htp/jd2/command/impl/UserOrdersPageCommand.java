package by.htp.jd2.command.impl;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import by.htp.jd2.entity.Order;
import by.htp.jd2.entity.User;
import by.htp.jd2.service.ServiceException;
import by.htp.jd2.service.ServiceProvider;

/**
 * go to page where user can see all his orders
 *
 * @author alexey
 */
public class UserOrdersPageCommand implements Command {
    private static final Logger LOG = LogManager.getLogger(UserOrdersPageCommand.class.getName());
    private static final String debug = "User orders page command";
    private static final String error = "User orders page command ERROR";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("user") == null) {
            response.sendRedirect("index.jsp");
            LOG.error(error);
        } else {
            User user = (User) session.getAttribute("user");
            try {
                List<Order> orders = ServiceProvider.getInstance().getOrderService().userOrders(user.getId());
                request.setAttribute("userOrders", orders);
                Set<Car> cars = new HashSet<>();
                Car car;
                for (Order order : orders) {
                    car = ServiceProvider.getInstance().getCarService().getCarById(order.getIdCar());
                    cars.add(car);
                }
                request.setAttribute("carsO", cars);

            } catch (ServiceException e) {
                e.printStackTrace();
                LOG.error(error + e);
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.USER_ORDERS);
            dispatcher.forward(request, response);
            LOG.debug(debug);
        }
    }
}
