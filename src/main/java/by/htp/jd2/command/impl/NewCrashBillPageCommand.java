package by.htp.jd2.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.jd2.command.Command;
import by.htp.jd2.controller.JSPPageName;

/**
 * add new additional bill(crash bill) to order
 *
 * @author alexey
 */
public class NewCrashBillPageCommand implements Command {
    private static final Logger LOG = LogManager.getLogger(NewCrashBillPageCommand.class.getName());
    private static final String debug = "Go to new crash bill page";
    private static final String error = "Go to new crash bill page ERROR";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") == null) {
            response.sendRedirect("index.jsp");
            LOG.error(error);
        } else {
            try {
                int orderId = Integer.parseInt(request.getParameter("orderId"));
                int carId = Integer.parseInt(request.getParameter("carId"));
                int userId = Integer.parseInt(request.getParameter("userId"));

                request.setAttribute("orderId", orderId);
                request.setAttribute("carId", carId);
                request.setAttribute("userId", userId);
                RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.ADD_CRASH);
                dispatcher.forward(request, response);
                LOG.debug(debug);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                LOG.error(error, e);
            }
        }
    }

}
