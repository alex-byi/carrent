package by.htp.jd2.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.jd2.command.Command;
import by.htp.jd2.service.ServiceException;
import by.htp.jd2.service.ServiceProvider;

/**
 * @author alexey
 * cancel order
 */
public class OrderCancelCommand implements Command {
    private static final Logger LOG = LogManager.getLogger(OrderCancelCommand.class.getName());
    private static final String debug = "Order cancel command";
    private static final String error = "Cancel order ERROR";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") == null) {
            response.sendRedirect("index.jsp");
            LOG.error(error);
        } else {
            try {
                int id = Integer.parseInt(request.getParameter("orderId"));
                String reason = request.getParameter("reason");

                ServiceProvider.getInstance().getOrderService().setCanceled(id);
                ServiceProvider.getInstance().getOrderService().setRejectReason(reason, id);
            } catch (ServiceException | NumberFormatException e) {
                LOG.error(error + e);
                e.printStackTrace();
                session.setAttribute("error", error);
            }

            if (request.getParameter("userType").equals("user")) {
                response.sendRedirect("controller?command=user_orders_page");
            } else {
                response.sendRedirect("controller?command=ORDER_PAGE");
            }
            LOG.debug(debug);
        }
    }

}
