package ua.payments.controller.command.client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.payments.controller.command.Command;

import javax.servlet.http.HttpServletRequest;

public class ClientListCardsCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ClientListCardsCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        logger.info("execute() started!");
        return "/WEB-INF/client/client-listCards.jsp";
    }
}
