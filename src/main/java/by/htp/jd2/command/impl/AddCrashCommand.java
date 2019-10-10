package by.htp.jd2.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.jd2.command.Command;
import by.htp.jd2.entity.Crash;
import by.htp.jd2.service.ServiceException;
import by.htp.jd2.service.ServiceProvider;

public class AddCrashCommand implements Command {
    private static final Logger LOG = LogManager.getLogger(AddCrashCommand.class.getName());
    private static final String error = "ADD crash bill ERROR";
    private static final String debug = "Add crash command";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("user") == null) {
            response.sendRedirect("index.jsp");
            LOG.error(error);
        } else {
            try {
                String description = request.getParameter("description");
                int amount = Integer.parseInt(request.getParameter("amount"));
                int userId = Integer.parseInt(request.getParameter("userId"));
                int orderId = Integer.parseInt(request.getParameter("orderId"));
                int carId = Integer.parseInt(request.getParameter("carId"));
                Crash crash = new Crash(description, amount, carId, userId);
                int crashId = ServiceProvider.getInstance().getCrashService().addCrash(crash, orderId);
                ServiceProvider.getInstance().getOrderService().setCrash(orderId, crashId);
            } catch (ServiceException | NumberFormatException e) {
                LOG.error(error + e);
                e.printStackTrace();
                session.setAttribute("error", error);
            }
            response.sendRedirect("controller?command=CRASH_PAGE_ADMIN");
            LOG.debug(debug);
        }
    }
}
