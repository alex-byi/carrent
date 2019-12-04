package by.htp.jd2.command.impl.link;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.jd2.command.Command;

public class NoSuchCommand implements Command {
    private static final Logger LOG = LogManager.getLogger(NoSuchCommand.class.getName());
    private static final String ERROR = "No such COMMAND!";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.error(ERROR);

    }

}
