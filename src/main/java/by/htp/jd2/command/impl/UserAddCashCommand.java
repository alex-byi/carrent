package by.htp.jd2.command.impl;

import by.htp.jd2.command.Command;
import by.htp.jd2.controller.JSPPageName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserAddCashCommand implements Command {
    private static final Logger LOG = LogManager.getLogger(UserAddCashCommand.class.getName());
    private static final String error = "Go to USER add cash page without session";
    private static final String debug = "Go to USER add cash page";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") == null) {
            response.sendRedirect("index.jsp");
            LOG.error(error);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.USER_ADD_CASH);
            dispatcher.forward(request, response);
            LOG.debug(debug);
        }
    }
}
