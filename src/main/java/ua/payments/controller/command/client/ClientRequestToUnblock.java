package ua.payments.controller.command.client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.payments.controller.command.Command;
import ua.payments.model.entity.enums.State;
import ua.payments.model.service.AccountService;

import javax.servlet.http.HttpServletRequest;

/**
 * This class is used for creating requests for unblocking
 *
 * @author Roman Skab
 * @version 1.0
 */
public class ClientRequestToUnblock implements Command {
    private static final Logger logger = LogManager.getLogger(ClientCardCommand.class);

    private AccountService accountService = new AccountService();

    @Override
    public String execute(HttpServletRequest request) {
        logger.info("execute() started!");
        String accountId = request.getParameter("account-id");
        logger.info("accountId - " + accountId);
        accountService.changeState(Long.parseLong(accountId), State.WAITING_TO_UNBLOCK);
        return "redirect:/client/accounts?account-id=" + accountId;
    }
}
