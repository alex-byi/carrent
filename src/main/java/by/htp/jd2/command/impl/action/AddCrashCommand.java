package by.htp.jd2.command.impl.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp.jd2.dao.connectionpool.ConnectionListener;
import by.htp.jd2.service.CrashService;
import by.htp.jd2.service.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.jd2.command.Command;
import by.htp.jd2.entity.Crash;
import by.htp.jd2.service.ServiceException;
import org.springframework.stereotype.Component;

/**
 * add additional bill to order
 *
 * @author alexey
 */
@Component
public class AddCrashCommand implements Command {
    private static final Logger LOG = LogManager.getLogger(AddCrashCommand.class.getName());
    private static final String ERROR = "ADD crash bill ERROR";
    private static final String DEBUG = "Add crash command";

    private CrashService crashService = (CrashService) ConnectionListener.getContextBean(CrashService.class);

    private OrderService orderService = (OrderService) ConnectionListener.getContextBean(OrderService.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("user") == null) {
            response.sendRedirect("index.jsp");
            LOG.error(ERROR);
        } else {
            try {
                String description = request.getParameter("description");
                int amount = Integer.parseInt(request.getParameter("amount"));
                int userId = Integer.parseInt(request.getParameter("userId"));
                int orderId = Integer.parseInt(request.getParameter("orderId"));
                int carId = Integer.parseInt(request.getParameter("carId"));
                Crash crash = new Crash(description, amount, carId, userId);
                int crashId = crashService.addCrash(crash);
                orderService.setCrash(orderId, crashId);
            } catch (ServiceException | NumberFormatException e) {
                LOG.error(ERROR, e);
                if (session != null) {
                    session.setAttribute("error", ERROR);
                }
            }
            response.sendRedirect("controller?command=CRASH_PAGE_ADMIN");
            LOG.debug(DEBUG);
        }
    }
}
