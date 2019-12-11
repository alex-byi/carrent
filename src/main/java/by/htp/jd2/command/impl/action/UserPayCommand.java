package by.htp.jd2.command.impl.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp.jd2.dao.connectionpool.ConnectionListener;
import by.htp.jd2.service.OrderService;
import by.htp.jd2.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.jd2.command.Command;
import by.htp.jd2.entity.Order;
import by.htp.jd2.entity.User;
import by.htp.jd2.service.ServiceException;
import org.springframework.stereotype.Component;

/**
 * user pay order
 *
 * @author alexey
 */
@Component
public class UserPayCommand implements Command {
    private static final Logger LOG = LogManager.getLogger(UserPayCommand.class.getName());
    private static final String DEBUG = "User pay command";
    private static final String ERROR = "Pay ERROR";

    private UserService userService = (UserService) ConnectionListener.getContextBean(UserService.class);
    private OrderService orderService = (OrderService) ConnectionListener.getContextBean(OrderService.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("user") == null) {
            response.sendRedirect("index.jsp");
            LOG.error(ERROR);
        } else {
            try {
                if (session != null) {
                    int userId = Integer.parseInt(request.getParameter("userId"));
                    int orderId = Integer.parseInt(request.getParameter("orderId"));
                    Order order;
                    User user = (User) session.getAttribute("user");
                    order = orderService.getOrderById(orderId);
                    userService.pay(order.getAmount(), userId);
                    orderService.setPayment(orderId);
                    user.setCash(user.getCash() - order.getAmount());
                    session.setAttribute("user", user);
                }
            } catch (ServiceException | NumberFormatException e) {
                LOG.error(ERROR, e);
                if (session != null) {
                    session.setAttribute("error", ERROR);
                }
            }
            response.sendRedirect("controller?command=USER_ORDERS_PAGE");
            LOG.debug(DEBUG);
        }
    }
}
