package by.htp.jd2.command.impl.link;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp.jd2.dao.connectionpool.ConnectionListener;
import by.htp.jd2.service.CarService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.jd2.command.Command;
import by.htp.jd2.controller.JSPPageName;
import by.htp.jd2.entity.Car;
import by.htp.jd2.service.ServiceException;
import org.springframework.stereotype.Component;

/**
 * go to page where user can choose car
 *
 * @author alexey
 */
@Component
public class OrderPageStep2UserCommand implements Command {
    private static final Logger LOG = LogManager.getLogger(OrderPageStep2UserCommand.class.getName());
    private static final String DEBUG = "Order step 2 command";
    private static final String ERROR = "Order step 2 command ERROR";

    private CarService carService = (CarService) ConnectionListener.getContextBean(CarService.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        if (session != null && session.getAttribute("user") == null) {
            response.sendRedirect("index.jsp");
            LOG.error(ERROR);
        } else {
            String dates = request.getParameter("dates");
            String dateStart = dates.substring(0, 10);
            String dateEnd = dates.substring(13, 23);
            SimpleDateFormat dateFormat1 = new SimpleDateFormat("MM/dd/yyyy");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            try {
                Date dateS = dateFormat1.parse(dateStart);
                Date dateE = dateFormat1.parse(dateEnd);

                String startDate = dateFormat.format(dateS);
                String endDate = dateFormat.format(dateE);

                List<Car> availableCars = carService.getAllAvailableCars(startDate,
                        endDate);

                long milliseconds = dateE.getTime() - dateS.getTime();
                int days = (int) (milliseconds / (24 * 60 * 60 * 1000)) + 1;

                request.setAttribute("availableCars", availableCars);
                request.setAttribute("dayCol", days);
                request.setAttribute("startDate1", startDate);
                request.setAttribute("endDate1", endDate);

            } catch (ParseException | ServiceException e) {
                LOG.error(ERROR, e);
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.ORDER_PAGE_USER);
            dispatcher.forward(request, response);
            LOG.debug(DEBUG);

        }
    }

}
