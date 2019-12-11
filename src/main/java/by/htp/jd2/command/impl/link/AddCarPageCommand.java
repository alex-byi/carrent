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

/**
 * go to "Add car" page
 *
 * @author alexey
 */

public class AddCarPageCommand implements Command {
    private static final Logger LOG = LogManager.getLogger(AddCarPageCommand.class.getName());
    private static final String ERROR = "Go to add car page without session";
    private static final String DEBUG = "Go to add car page";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") == null) {
            response.sendRedirect("index.jsp");
            LOG.error(ERROR);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.ADD_CAR_PAGE);
            dispatcher.forward(request, response);
            LOG.debug(DEBUG);

        }
    }

}
