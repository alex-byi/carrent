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
 * @author alexey
 * @ go to "Add car" page
 */
public class AddCarPageCommand implements Command {
    private static final Logger LOG = LogManager.getLogger(AddCarPageCommand.class.getName());
    private static final String error = "Go to add car page without session";
    private static final String debug = "Go to add car page";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") == null) {
            response.sendRedirect("index.jsp");
            LOG.error(error);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.ADD_CAR_PAGE);
            dispatcher.forward(request, response);
            LOG.debug(debug);

        }
    }

}
