package by.htp.jd2.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.jd2.command.Command;

public class NoSuchCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("!!!!!!!!!!!!!!!");

    }

}
