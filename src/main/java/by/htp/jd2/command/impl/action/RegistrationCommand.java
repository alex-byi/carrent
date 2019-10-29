package by.htp.jd2.command.impl.action;

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
import by.htp.jd2.controller.RequestParameterName;
import by.htp.jd2.entity.User;
import by.htp.jd2.service.ServiceException;
import by.htp.jd2.service.ServiceProvider;

public class RegistrationCommand implements Command {
    private static final Logger LOG = LogManager.getLogger(RegistrationCommand.class.getName());
    private static final String debug = "Registration command";
    private static final String error = "Registration ERROR";
    private static final String duplicate = "Пользователь с таким логином уже существует. Выберите другой";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession(true);
        String login = request.getParameter(RequestParameterName.REQ_PARAM_LOGIN);
        String password = request.getParameter(RequestParameterName.REQ_PARAM_PASSWORD);
        String fullName = request.getParameter(RequestParameterName.REQ_PARAM_FULLNAME);
        String passNum = request.getParameter(RequestParameterName.REQ_PARAM_PASSNUMBER);
        String email = request.getParameter(RequestParameterName.REQ_PARAM_EMAIL);
        String address = request.getParameter(RequestParameterName.REQ_PARAM_ADDRESS);

        User user = new User(login, password, fullName, passNum, email, address);

        try {
            if (!ServiceProvider.getInstance().getUserService().checkUser(login)) {
                session.setAttribute("duplicateLogin", duplicate);
                RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.REGISTRATION_PAGE);
                dispatcher.forward(request, response);
            } else {
                ServiceProvider.getInstance().getUserService().registration(user);
                session.setAttribute("user", user);
                response.sendRedirect("controller?command=AUTHORIZATION");
                LOG.debug(debug);
            }

        } catch (ServiceException e) {
            LOG.error(error + e);
            e.printStackTrace();
            session.setAttribute("error", error);
        }

    }
}
