package by.htp.jd2.command.impl.action;

import by.htp.jd2.command.Command;
import by.htp.jd2.dao.connectionpool.ConnectionListener;
import by.htp.jd2.entity.User;
import by.htp.jd2.service.ServiceException;
import by.htp.jd2.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * add money to user by USER
 *
 * @author alexey
 */
@Component
public class AddMoneyUserCommand implements Command {
    private static final Logger LOG = LogManager.getLogger(AddMoneyUserCommand.class.getName());
    private static final String ERROR = "ADD money by USER ERROR";
    private static final String DEBUG = "Add money by USER command";

    private UserService userService = (UserService) ConnectionListener.getContextBean(UserService.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession(false);
        int moneyCol = 0;
        int userId;
        if (session != null && session.getAttribute("user") == null) {
            response.sendRedirect("index.jsp");
            LOG.error(ERROR);
        } else {
            if (session != null) {
                User user = (User) session.getAttribute("user");

                try {
                    userId = Integer.parseInt(request.getParameter("idUser"));
                    moneyCol = Integer.parseInt(request.getParameter("moneyCol"));
                   userService.addMoney(moneyCol, userId);
                } catch (ServiceException | NumberFormatException e) {
                    LOG.error(ERROR, e);
                    session.setAttribute("error", ERROR);
                }
                user.setCash(user.getCash() + moneyCol);
                session.setAttribute("user", user);
                response.sendRedirect("controller?command=USER_PAGE");
                LOG.debug(DEBUG);
            }
        }

    }
}
