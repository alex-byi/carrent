package by.htp.jd2.command.impl;

import by.htp.jd2.command.Command;
import by.htp.jd2.controller.JSPPageName;
import by.htp.jd2.entity.User;
import by.htp.jd2.service.ServiceException;
import by.htp.jd2.service.ServiceProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Search user by login or by half login
 *
 * @author alexey
 */
public class UserSearchCommand implements Command {

    private static final Logger LOG = LogManager.getLogger(UserSearchCommand.class.getName());
    private static final String error = "SEARCH USER ERROR";
    private static final String debug = "SEARCH USER command";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String searchLogin = request.getParameter("searchLogin");
        HttpSession session = request.getSession();

        if (session != null && session.getAttribute("user") == null) {
            response.sendRedirect("index.jsp");
            LOG.error(error);
        } else {
            try {
                List<User> list = ServiceProvider.getInstance().getUserService().searchUser(searchLogin);
                request.setAttribute("searchedUsers", list);
                System.out.println(list.size());
            } catch (ServiceException e) {
                LOG.error(error + e);
                e.printStackTrace();
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.CONTROL_USERS_PAGE);
            dispatcher.forward(request, response);
            LOG.debug(debug);
        }

    }
}
