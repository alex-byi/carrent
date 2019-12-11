package by.htp.jd2.command.impl.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp.jd2.dao.connectionpool.ConnectionListener;
import by.htp.jd2.service.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.jd2.command.Command;
import by.htp.jd2.service.ServiceException;
import org.springframework.stereotype.Component;

/**
 * complete order
 *
 * @author alexey
 */
@Component
public class OrderCompleteCommand implements Command {
    private static final Logger LOG = LogManager.getLogger(OrderCompleteCommand.class.getName());
    private static final String DEBUG = "Order complete command";
    private static final String ERROR = "Complete order ERROR";

    private OrderService orderService = (OrderService) ConnectionListener.getContextBean(OrderService.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") == null) {
            response.sendRedirect("index.jsp");
            LOG.error(ERROR);
        } else {
            try {
                int id = Integer.parseInt(request.getParameter("orderId"));
               orderService.setComplete(id);
            } catch (ServiceException | NumberFormatException e) {
                LOG.error(ERROR, e);
                if (session != null) {
                    session.setAttribute("error", ERROR);
                }
            }
            response.sendRedirect("controller?command=ORDER_PAGE");
            LOG.debug(DEBUG);
        }
    }

}
