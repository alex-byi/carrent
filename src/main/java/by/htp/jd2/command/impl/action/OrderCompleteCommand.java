package by.htp.jd2.command.impl.action;

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
 * complete order
 *
 * @author alexey
 */
public class OrderCompleteCommand implements Command {
    private static final Logger LOG = LogManager.getLogger(OrderCompleteCommand.class.getName());
    private static final String debug = "Order complete command";
    private static final String error = "Complete order ERROR";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") == null) {
            response.sendRedirect("index.jsp");
            LOG.error(error);
        } else {
            try {
                int id = Integer.parseInt(request.getParameter("orderId"));
                ServiceProvider.getInstance().getOrderService().setComplete(id);
            } catch (ServiceException | NumberFormatException e) {
                LOG.error(error + e);
                e.printStackTrace();
                session.setAttribute("error", error);
            }
            response.sendRedirect("controller?command=ORDER_PAGE");
            LOG.debug(debug);
        }
    }

}
