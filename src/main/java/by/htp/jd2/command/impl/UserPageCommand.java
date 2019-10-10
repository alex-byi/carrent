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
import by.htp.jd2.entity.User;
import by.htp.jd2.entity.UserType;

public class UserPageCommand implements Command {
    private static final Logger LOG = LogManager.getLogger(UserPageCommand.class.getName());
    private static final String debug = "Go to userpage page command";
    private static final String error = "Go to userpage page command ERROR";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession(false);
        User user = null;
        if (session != null && session.getAttribute("user") == null) {
            response.sendRedirect("index.jsp");
            LOG.error(error);
        } else {
            user = (User) session.getAttribute("user");
            if (user.getType() == UserType.ADMIN) {
                RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.ADMIN_AUTH_PAGE);
                session.setAttribute("user", user);
                dispatcher.forward(request, response);
                LOG.debug(debug);
            } else {
                RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.USER_AUTH_PAGE);
                session.setAttribute("user", user);
                dispatcher.forward(request, response);
                LOG.debug(debug);
            }
        }
    }

}
