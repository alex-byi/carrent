package by.htp.jd2.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.jd2.command.Command;
import by.htp.jd2.command.CommandHelper;
import by.htp.jd2.dao.connectionpool.ConnectionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * controller for all application
 *
 * @author alexey
 */
@Component
public class Controller extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final CommandHelper helper = CommandHelper.getInstance();

//    private CommandHelper helper = (CommandHelper) ConnectionListener.getContextBean(CommandHelper.class);

//    static {
//        CommandHelper.Puts();
//    }

    public Controller() {
        super();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doPost(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String commandName;
        Command command;

        commandName = request.getParameter(RequestParameterName.REQ_PARAM_COMMAND_NAME);
        command = helper.getCommand(commandName);
        command.execute(request, response);

    }

}
