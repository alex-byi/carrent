package by.htp.jd2.command.impl.link;

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
 * go to "Control users" page
 *
 * @author alexey
 */
public class ControlUsersPageCommand implements Command {
    private static final Logger LOG = LogManager.getLogger(ControlUsersPageCommand.class.getName());
    private static final String ERROR = "go to Control users page error";
    private static final String DEBUG = "Go to User control page command";
    private static final String CURRENT_PAGE_PARAMETER = "currentPage";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession(false);
        int currentPage;

        if (session != null && session.getAttribute("user") == null) {
            response.sendRedirect("index.jsp");
            LOG.error(ERROR);
        } else {
            try {
                if (request.getParameter(CURRENT_PAGE_PARAMETER) == null) {
                    currentPage = 0;
                } else {
                    currentPage = Integer.parseInt(request.getParameter(CURRENT_PAGE_PARAMETER));
                }
                int page = currentPage * 5;

                List<User> users = ServiceProvider.getInstance().getUserService().getAllUsers(page);
                request.setAttribute("allUsers", users);
                request.setAttribute(CURRENT_PAGE_PARAMETER, currentPage);
            } catch (ServiceException e) {
                LOG.error(ERROR, e);
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.CONTROL_USERS_PAGE);
            dispatcher.forward(request, response);
        }
        LOG.debug(DEBUG);
    }

}
