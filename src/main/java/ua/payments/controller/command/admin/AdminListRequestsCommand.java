package ua.payments.controller.command.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.payments.controller.command.Command;

import javax.servlet.http.HttpServletRequest;

public class AdminListRequestsCommand implements Command {
    private static final Logger logger = LogManager.getLogger(AdminListRequestsCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        logger.info("execute() started!");
        return "/WEB-INF/admin/admin-listRequests.jsp";
    }
}
