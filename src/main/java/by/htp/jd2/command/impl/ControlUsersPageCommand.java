package by.htp.jd2.command.impl;

import java.io.IOException;
import java.util.List;

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
import by.htp.jd2.service.ServiceException;
import by.htp.jd2.service.ServiceProvider;

/**
 * @author alexey
 * go to "Control users" page
 */
public class ControlUsersPageCommand implements Command {
    private static final Logger LOG = LogManager.getLogger(ControlUsersPageCommand.class.getName());
    private static final String error = "go to Control users page error";
    private static final String debug = "Go to User control page command";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") == null) {
            response.sendRedirect("index.jsp");
            LOG.error(error);
        } else {
            try {
                List<User> users = ServiceProvider.getInstance().getUserService().getAllUsers();
                session.setAttribute("allUsers", users);
            } catch (ServiceException e) {
                LOG.error(error + e);
                e.printStackTrace();
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.CONTROL_USERS_PAGE);
            dispatcher.forward(request, response);
        }
        LOG.debug(debug);
    }

}
