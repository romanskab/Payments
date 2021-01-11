package ua.payments.controller.command.client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.payments.controller.command.Command;
import ua.payments.model.entity.User;
import ua.payments.model.service.AccountService;

import javax.servlet.http.HttpServletRequest;

/**
 * This class is used for creating accounts of clients.
 *
 * @author Roman Skab
 * @version 1.0
 */
public class ClientCreateAccountCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ClientCreateAccountCommand.class);

    private final AccountService accountService = new AccountService();

    @Override
    public String execute(HttpServletRequest request) {
        logger.info("execute() started!");
        User user = (User) request.getSession().getAttribute("user");
        accountService.createForClient(user.getId());
        return "redirect:/client/accounts";
    }
}
