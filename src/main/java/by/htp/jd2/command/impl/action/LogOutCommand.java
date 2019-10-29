package by.htp.jd2.command.impl.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.jd2.command.Command;
import by.htp.jd2.controller.JSPPageName;

/**
 * log out user command + invalidate session
 *
 * @author alexey
 */
public class LogOutCommand implements Command {
    private static final Logger LOG = LogManager.getLogger(LogOutCommand.class.getName());
    private static final String debug = "Log out command";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getSession().invalidate();
        RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.INDEX_PAGE);
        dispatcher.forward(request, response);
        LOG.debug(debug);
    }

}
