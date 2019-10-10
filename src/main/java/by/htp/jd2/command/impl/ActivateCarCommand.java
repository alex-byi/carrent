package by.htp.jd2.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.jd2.command.Command;
import by.htp.jd2.controller.RequestParameterName;
import by.htp.jd2.service.ServiceException;
import by.htp.jd2.service.ServiceProvider;

public class ActivateCarCommand implements Command {
    private static final Logger LOG = LogManager.getLogger(ActivateCarCommand.class.getName());
    private static final String error = "Car activate ERROR";
    private static final String debug = "Activate car command";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("user") == null) {
            response.sendRedirect("index.jsp");
            LOG.error(error);
        } else {

            try {
                int id = Integer.parseInt(request.getParameter(RequestParameterName.ID_CAR));
                ServiceProvider.getInstance().getCarService().activateCar(id);
            } catch (ServiceException | NumberFormatException e) {
                LOG.error(error + e);
                e.printStackTrace();
                session.setAttribute("error", error);
            }
            response.sendRedirect("controller?command=CONTROL_CAR_PAGE");
            LOG.debug(debug);
        }
    }

}
