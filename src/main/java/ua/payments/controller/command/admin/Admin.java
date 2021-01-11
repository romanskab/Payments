package ua.payments.controller.command.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.payments.controller.command.Command;

import javax.servlet.http.HttpServletRequest;

/**
 * This class returns basis page of admin
 *
 * @author Roman Skab
 * @version 1.0
 */
public class Admin implements Command {
    private static final Logger logger = LogManager.getLogger(Admin.class);

    @Override
    public String execute(HttpServletRequest request) {
        logger.info("execute() started!");

        return "/WEB-INF/admin/admin-basis.jsp";
    }
}
