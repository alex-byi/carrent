package by.htp.jd2.command.impl.action;

import java.io.IOException;
import java.io.InputStream;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

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

/**
 * authorization to application
 *
 * @author alexey
 */
public class AuthorizationCommand implements Command {

    private static final Logger LOG = LogManager.getLogger(AuthorizationCommand.class.getName());
    private static final String error = "User activate ERROR";
    private static final String debug = "Authorization command";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        InputStream mfStream = getClass().getClassLoader().getResourceAsStream("META-INF/MANIFEST.MF");
        Manifest mf = new Manifest();
        mf.read(mfStream);
        Attributes atts = mf.getMainAttributes();
        System.out.println("version: " + atts.getValue(Attributes.Name.IMPLEMENTATION_VERSION));


        String login;
        String password;
        User user;
        HttpSession session = request.getSession(false);

        try {
            if (session != null && session.getAttribute("user") == null) {
                login = request.getParameter(RequestParameterName.REQ_PARAM_LOGIN);
                password = request.getParameter(RequestParameterName.REQ_PARAM_PASSWORD);
                user = ServiceProvider.getInstance().getUserService().authorization(login, password);

                if (user.getLogin() != null && user.getLogin().equals(login)) {
                    session = request.getSession(true);
                    session.setAttribute("user", user);
                    response.sendRedirect("controller?command=USER_PAGE");
                } else {
                    RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.NO_SUCH_USER);
                    dispatcher.forward(request, response);
                }

            } else {

                User regUser = (User) session.getAttribute("user");
                user = ServiceProvider.getInstance().getUserService().authorization(regUser.getLogin(),
                        regUser.getPassword());
                if (user.getLogin().equals(regUser.getLogin())) {
                    session.setAttribute("user", user);
                    response.sendRedirect("controller?command=USER_PAGE");
                } else {
                    RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.NO_SUCH_USER);
                    dispatcher.forward(request, response);
                }
            }
        } catch (ServiceException e) {
            e.printStackTrace();
            LOG.error(error + e);
            session.setAttribute("error", error);
        }
        LOG.debug(debug);
    }

}