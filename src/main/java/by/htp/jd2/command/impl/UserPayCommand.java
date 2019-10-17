package by.htp.jd2.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.jd2.command.Command;
import by.htp.jd2.entity.Order;
import by.htp.jd2.entity.User;
import by.htp.jd2.service.ServiceException;
import by.htp.jd2.service.ServiceProvider;

/**
 * @author alexey
 * user pay order
 */
public class UserPayCommand implements Command {
    private static final Logger LOG = LogManager.getLogger(UserPayCommand.class.getName());
    private static final String debug = "User pay command";
    private static final String error = "Pay ERROR";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("user") == null) {
            response.sendRedirect("index.jsp");
            LOG.error(error);
        } else {
            try {
                int userId = Integer.parseInt(request.getParameter("userId"));
                int orderId = Integer.parseInt(request.getParameter("orderId"));
                Order order;
                User user = (User) session.getAttribute("user");
                order = ServiceProvider.getInstance().getOrderService().getOrderById(orderId);
                ServiceProvider.getInstance().getUserService().pay(order.getAmount(), userId);
                ServiceProvider.getInstance().getOrderService().setPayment(orderId);
                user.setCash(user.getCash() - order.getAmount());
                session.setAttribute("user", user);
            } catch (ServiceException | NumberFormatException e) {
                LOG.error(error + e);
                e.printStackTrace();
                session.setAttribute("error", error);
            }
            response.sendRedirect("controller?command=USER_ORDERS_PAGE");
            LOG.debug(debug);
        }
    }
}
