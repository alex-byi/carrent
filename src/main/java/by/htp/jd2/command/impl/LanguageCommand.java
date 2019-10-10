package by.htp.jd2.command.impl;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp.jd2.command.Command;

public class LanguageCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(false);

        String language = request.getParameter("language");
        Locale locale = null;

        String page = request.getParameter("page");
        switch (language.toUpperCase()) {
            case "EN":
                locale = new Locale("en");
                break;
            case "RU":
                locale = new Locale("ru");
                break;
        }

        switch (page) {
            case "index":
                response.sendRedirect("index.jsp");
                break;
            case "userorders":
                response.sendRedirect("controller?command=USER_ORDERS_PAGE");
                break;
            case "registration":
                response.sendRedirect("jsp/registration_user.jsp");
                break;
            case "auth":
                response.sendRedirect("controller?command=USER_PAGE");
                break;
            case "user_car_page":
                response.sendRedirect("controller?command=CAR_PAGE");
                break;
            case "user_order_page":
                response.sendRedirect("controller?command=ORDER_PAGE_USER");
                break;
            case "user_crashs":
                response.sendRedirect("controller?command=USER_CRASH_PAGE");
                break;
            case "control_car":
                response.sendRedirect("controller?command=CONTROL_CAR_PAGE");
                break;
            case "control_users":
                response.sendRedirect("controller?command=ALL_USERS");
                break;
            case "crash_page_admin":
                response.sendRedirect("controller?command=CRASH_PAGE_ADMIN");
                break;
            case "order_page_admin":
                response.sendRedirect("controller?command=ORDER_PAGE");
                break;

        }

        session.setAttribute("locale", locale);

    }
}
