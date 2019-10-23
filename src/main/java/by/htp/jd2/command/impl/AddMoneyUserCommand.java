package by.htp.jd2.command.impl;

import by.htp.jd2.command.Command;
import by.htp.jd2.entity.User;
import by.htp.jd2.service.ServiceException;
import by.htp.jd2.service.ServiceProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class AddMoneyUserCommand implements Command {
    private static final Logger LOG = LogManager.getLogger(AddMoneyUserCommand.class.getName());
    private static final String error = "ADD money by USER ERROR";
    private static final String debug = "Add money by USER command";


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession(false);
        int moneyCol = 0;
        int userId = 0;
        if (session != null && session.getAttribute("user") == null) {
            response.sendRedirect("index.jsp");
            LOG.error(error);
        } else {
            User user = (User) session.getAttribute("user");
            try {
                userId = Integer.parseInt(request.getParameter("idUser"));
                moneyCol = Integer.parseInt(request.getParameter("moneyCol"));
                ServiceProvider.getInstance().getUserService().addMoney(moneyCol, userId);
            } catch (ServiceException | NumberFormatException e) {
                LOG.error(error + e);
                e.printStackTrace();
                session.setAttribute("error", error);
            }
            user.setCash(user.getCash() + moneyCol);
            session.setAttribute("user", user);
            response.sendRedirect("controller?command=USER_PAGE");
            LOG.debug(debug);
        }

    }
}
