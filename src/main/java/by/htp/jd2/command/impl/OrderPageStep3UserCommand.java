package by.htp.jd2.command.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import by.htp.jd2.entity.Car;
import by.htp.jd2.entity.Order;
import by.htp.jd2.service.ServiceException;
import by.htp.jd2.service.ServiceProvider;

/**
 * go to page where user can confirm order
 *
 * @author alexey
 */
public class OrderPageStep3UserCommand implements Command {
    private static final Logger LOG = LogManager.getLogger(OrderPageStep3UserCommand.class.getName());
    private static final String debug = "Order step 3 command";
    private static final String error = "Order step 3 command ERROR";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        if (session != null && session.getAttribute("user") == null) {
            response.sendRedirect("index.jsp");
            LOG.error(error);
        } else {
            try {
                int idCar = Integer.parseInt(request.getParameter(RequestParameterName.ID_CAR));
                String date1 = request.getParameter(RequestParameterName.START_DATE);
                String date2 = request.getParameter(RequestParameterName.END_DATE);
                int idUser = Integer.parseInt(request.getParameter(RequestParameterName.ID_USER));
                int amount = Integer.parseInt(request.getParameter("amount"));
                int dayCol = Integer.parseInt(request.getParameter("dayCol"));
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = new Date();
                Car car;
                car = ServiceProvider.getInstance().getCarService().getCarById(idCar);
                Order order = new Order(dateFormat.format(date), date1, date2, idCar, idUser, amount, dayCol);

                request.setAttribute("selectCar", car);
                request.setAttribute("selectOrder", order);
                request.setAttribute("orderAmount", amount);
                request.setAttribute("dayCol", dayCol);

            } catch (ServiceException | NumberFormatException e) {
                e.printStackTrace();
                LOG.error(error + e);
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.FINAL_ORDER_PAGE_USER);
            dispatcher.forward(request, response);
            LOG.debug(debug);
        }
    }
}
