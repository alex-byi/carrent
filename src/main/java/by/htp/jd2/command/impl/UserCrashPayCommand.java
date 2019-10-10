package by.htp.jd2.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.jd2.command.Command;
import by.htp.jd2.entity.Crash;
import by.htp.jd2.entity.User;
import by.htp.jd2.service.ServiceException;
import by.htp.jd2.service.ServiceProvider;

public class UserCrashPayCommand implements Command {
    private static final Logger LOG = LogManager.getLogger(UserCrashPayCommand.class.getName());
    private static final String debug = "User crash pay command";
    private static final String error = "Pay ERROR";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("user") == null) {
            response.sendRedirect("index.jsp");
            LOG.error(error);
        } else {
            try {
                User user = (User) session.getAttribute("user");
                int userId = user.getId();
                int crashId = Integer.parseInt(request.getParameter("crashId"));
                int amount = Integer.parseInt(request.getParameter("amount"));
                Crash crash;
                crash = ServiceProvider.getInstance().getCrashService().getCrashById(crashId);
                ServiceProvider.getInstance().getCrashService().setCrashPayment(crashId);
                ServiceProvider.getInstance().getUserService().pay(amount, userId);
                user.setCash(user.getCash() - crash.getAmount());
                session.setAttribute("user", user);
            } catch (ServiceException | NumberFormatException e) {
                LOG.error(error + e);

                e.printStackTrace();
                session.setAttribute("error", error);
            }
            response.sendRedirect("controller?command=USER_CRASH_PAGE");
            LOG.debug(debug);
        }
    }

}
