package by.htp.jd2.command.impl.link;

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

/**
 * command which is redirect to User or Admin page
 *
 * @author alexey
 */
public class UserPageCommand implements Command {
    private static final Logger LOG = LogManager.getLogger(UserPageCommand.class.getName());
    private static final String DEBUG = "Go to userpage page command";
    private static final String ERROR = "Go to userpage page command ERROR";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession(false);
        User user;
        if (session != null && session.getAttribute("user") == null) {
            response.sendRedirect("index.jsp");
            LOG.error(ERROR);
        } else {
            if (session != null) {

                user = (User) session.getAttribute("user");
                if (user.getType() == UserType.ADMIN) {
                    RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.ADMIN_AUTH_PAGE);
                    session.setAttribute("user", user);
                    dispatcher.forward(request, response);
                    LOG.debug(DEBUG);
                } else {
                    RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.USER_AUTH_PAGE);
                    session.setAttribute("user", user);
                    dispatcher.forward(request, response);
                    LOG.debug(DEBUG);
                }
            }
        }
    }

}
