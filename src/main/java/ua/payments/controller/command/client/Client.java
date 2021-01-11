package ua.payments.controller.command.client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.payments.controller.command.Command;

import javax.servlet.http.HttpServletRequest;

/**
 * This class is used for returning of base client' page.
 *
 * @author Roman Skab
 * @version 1.0
 */
public class Client implements Command {
    private static final Logger logger = LogManager.getLogger(Client.class);

    @Override
    public String execute(HttpServletRequest request) {
        logger.info("execute() started!");

        return "/WEB-INF/client/client-basis.jsp";
    }
}
