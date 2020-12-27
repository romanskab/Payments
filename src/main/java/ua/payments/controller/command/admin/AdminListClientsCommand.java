package ua.payments.controller.command.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.payments.controller.command.Command;
import ua.payments.controller.command.client.ClientListAccountsCommand;

import javax.servlet.http.HttpServletRequest;

public class AdminListClientsCommand implements Command {
    private static final Logger logger = LogManager.getLogger(AdminListClientsCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        logger.info("execute() started!");
        return "/WEB-INF/admin/admin-listClients.jsp";
    }
}
