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
 * go to page with all orders of all users
 *
 * @author alexey
 */
public class OrderPageCommand implements Command {

    private static final Logger LOG = LogManager.getLogger(OrderPageCommand.class.getName());
    private static final String debug = "Go to order page command";
    private static final String error = "Go to order page command ERROR";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(false);

        int currentPage;

        if (session != null && session.getAttribute("user") == null) {
            response.sendRedirect("index.jsp");
            LOG.error(error);
        } else {
            try {

                if (request.getParameter("currentPage") == null) {
                    currentPage = 0;
                } else {
                    currentPage = Integer.parseInt(request.getParameter("currentPage"));
                }
                int page = currentPage * 5;

                List<Order> orders = ServiceProvider.getInstance().getOrderService().getAllOrders(page);
                request.setAttribute("allOrders", orders);
                request.setAttribute("currentPage", currentPage);

                Set<Car> cars = new HashSet<>();
                Car car;
                for (Order order : orders) {
                    car = ServiceProvider.getInstance().getCarService().getCarById(order.getIdCar());
                    cars.add(car);
                }
                request.setAttribute("carsO", cars);

                Set<User> users = new HashSet<>();
                User user;
                for (Order order : orders) {
                    user = ServiceProvider.getInstance().getUserService().getUserById(order.getIdUser());
                    users.add(user);
                }
                request.setAttribute("usersO", users);

            } catch (ServiceException e) {
                e.printStackTrace();
                LOG.error(error + e);
                request.getSession().invalidate();
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.ORDER_PAGE);
            dispatcher.forward(request, response);
            LOG.debug(debug);
        }
    }

}
