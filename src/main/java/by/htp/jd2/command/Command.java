package by.htp.jd2.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author alexey
 * interface for all commands
 */
public interface Command {

    /**
     * @param request from jsp page
     */
    void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;

}
