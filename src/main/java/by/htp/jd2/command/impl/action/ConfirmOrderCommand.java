package by.htp.jd2.command.impl.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.jd2.command.Command;
import by.htp.jd2.controller.JSPPageName;
import by.htp.jd2.entity.Car;
import by.htp.jd2.entity.Order;
import by.htp.jd2.service.ServiceException;
import by.htp.jd2.service.ServiceProvider;

/**
 * confirmation order by user
 *
 * @author alexey
 */
public class ConfirmOrderCommand implements Command {
    private static final Logger LOG = LogManager.getLogger(ConfirmOrderCommand.class.getName());
    private static final String error = "Confirm order ERROR";
    private static final String debug = "Confirm order Command";
    private static final String errorConfirmOrder = "Автомобиль уже заказал кто-то другой. Попробуйте еще раз";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("user") == null) {
            response.sendRedirect("index.jsp");
            LOG.error(error);
        } else {
            try {
                int idCar = Integer.parseInt(request.getParameter("orderIdCar"));
                String date1 = request.getParameter("orderStart");
                String date2 = request.getParameter("orderEnd");
                String date = request.getParameter("orderDate");
                int idUser = Integer.parseInt(request.getParameter("orderIdUser"));
                int amount = Integer.parseInt(request.getParameter("amountOrder"));
                int dayCol = Integer.parseInt(request.getParameter("dayCol"));
                Car car;
                Order order = new Order(date, date1, date2, idCar, idUser, amount, dayCol);
                List<Car> availableCars = ServiceProvider.getInstance().getCarService().getAllAvailableCars(date1,
                        date2);
                car = ServiceProvider.getInstance().getCarService().getCarById(idCar);
                if (availableCars.contains(car)) {
                    ServiceProvider.getInstance().getOrderService().addNewOrder(order);
                } else {
                    session.setAttribute("error", errorConfirmOrder);
                    RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.ORDER_PAGE_USER);
                    dispatcher.forward(request, response);
                }

            } catch (ServiceException | NumberFormatException e) {
                LOG.error(error + e);
                e.printStackTrace();
                session.setAttribute("error", error);
            }
            response.sendRedirect("controller?command=USER_ORDERS_PAGE");
            LOG.debug(debug);
        }
    }

}
