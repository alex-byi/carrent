package by.htp.jd2.command.impl.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp.jd2.dao.connectionpool.ConnectionListener;
import by.htp.jd2.service.CrashService;
import by.htp.jd2.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.jd2.command.Command;
import by.htp.jd2.entity.Crash;
import by.htp.jd2.entity.User;
import by.htp.jd2.service.ServiceException;
import org.springframework.stereotype.Component;

/**
 * pay additional bill by user
 *
 * @author alexey
 */
@Component
public class UserCrashPayCommand implements Command {
    private static final Logger LOG = LogManager.getLogger(UserCrashPayCommand.class.getName());
    private static final String DEBUG = "User crash pay command";
    private static final String ERROR = "Pay ERROR";

    private CrashService crashService = (CrashService) ConnectionListener.getContextBean(CrashService.class);
    private UserService userService = (UserService) ConnectionListener.getContextBean(UserService.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("user") == null) {
            response.sendRedirect("index.jsp");
            LOG.error(ERROR);
        } else {
            try {
                if (session != null) {
                    User user = (User) session.getAttribute("user");
                    int userId = user.getId();
                    int crashId = Integer.parseInt(request.getParameter("crashId"));
                    int amount = Integer.parseInt(request.getParameter("amount"));
                    Crash crash;
                    crash = crashService.getCrashById(crashId);
                    crashService.setCrashPayment(crashId);
                    userService.pay(amount, userId);
                    user.setCash(user.getCash() - crash.getAmount());
                    session.setAttribute("user", user);
                }
            } catch (ServiceException | NumberFormatException e) {
                LOG.error(ERROR, e);
                if (session != null) {
                    session.setAttribute("error", ERROR);
                }
            }
            response.sendRedirect("controller?command=USER_CRASH_PAGE");
            LOG.debug(DEBUG);
        }
    }

}
