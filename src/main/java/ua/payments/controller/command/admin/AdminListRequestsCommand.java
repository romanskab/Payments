package ua.payments.controller.command.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.payments.controller.command.Command;
import ua.payments.model.entity.Account;
import ua.payments.model.entity.enums.State;
import ua.payments.model.service.AccountService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * This class is used for returning of requests' list.
 *
 * @author Roman Skab
 * @version 1.0
 */
public class AdminListRequestsCommand implements Command {
    private static final Logger logger = LogManager.getLogger(AdminListRequestsCommand.class);

    private AccountService accountService = new AccountService();

    @Override
    public String execute(HttpServletRequest request) {
        logger.info("execute() started!");
        final List<Account> accounts = accountService.findByState(State.WAITING_TO_UNBLOCK);
        request.setAttribute("accounts", accounts);
        return "/WEB-INF/admin/admin-listRequests.jsp";
    }
}
