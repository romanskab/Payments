package ua.payments.controller.command.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.payments.controller.command.Command;
import ua.payments.model.entity.enums.State;
import ua.payments.model.service.AccountService;

import javax.servlet.http.HttpServletRequest;

/**
 * This class is used for unblocking of clients' accounts.
 *
 * @author Roman Skab
 * @version 1.0
 */
public class AdminClientAccountUnblock implements Command {
    private static final Logger logger = LogManager.getLogger(AdminListClientsCommand.class);

    private AccountService accountService = new AccountService();

    @Override
    public String execute(HttpServletRequest request) {
        logger.info("execute() started!");
        final String accountId = request.getParameter("account-id");
        final String clientId = request.getParameter("client-id");

        accountService.changeState(Long.parseLong(accountId), State.UNBLOCKED);
        return "redirect:/admin/clients?client-id=" + clientId;
    }
}
