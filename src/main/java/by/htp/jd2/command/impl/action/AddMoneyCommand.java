package by.htp.jd2.command.impl.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp.jd2.dao.connectionpool.ConnectionListener;
import by.htp.jd2.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.jd2.command.Command;
import by.htp.jd2.service.ServiceException;
import org.springframework.stereotype.Component;

/**
 * add money to user by ADMIN
 *
 * @author alexey
 */
@Component
public class AddMoneyCommand implements Command {
    private static final Logger LOG = LogManager.getLogger(AddMoneyCommand.class.getName());
    private static final String ERROR = "ADD money ERROR";
    private static final String DEBUG = "Add money command";

    private UserService userService = (UserService) ConnectionListener.getContextBean(UserService.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") == null) {
            response.sendRedirect("index.jsp");
            LOG.error(ERROR);
        } else {
            try {
                int userId = Integer.parseInt(request.getParameter("idUser"));
                int moneyCol = Integer.parseInt(request.getParameter("moneyCol"));
                userService.addMoney(moneyCol, userId);
            } catch (ServiceException | NumberFormatException e) {
                LOG.error(ERROR, e);
                if (session != null) {
                    session.setAttribute("error", ERROR);
                }
            }
            response.sendRedirect("controller?command=ALL_USERS");
            LOG.debug(DEBUG);
        }
    }
}
