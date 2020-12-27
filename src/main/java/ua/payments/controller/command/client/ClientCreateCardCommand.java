package ua.payments.controller.command.client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.payments.controller.command.Command;
import ua.payments.model.service.AccountService;
import ua.payments.model.service.CardService;

import javax.servlet.http.HttpServletRequest;

public class ClientCreateCardCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ClientCreateCardCommand.class);

    private final CardService cardService = new CardService();
    private final AccountService accountService = new AccountService();

    @Override
    public String execute(HttpServletRequest request) {
        logger.info("execute() started!");
        long accountId = Long.parseLong(request.getParameter("account-id"));
        System.out.println("accountId: " + accountId);
        cardService.createForAccount(accountId);
        request.setAttribute("account", accountService.findById(accountId));
        return "redirect:/client/accounts?account-id=" + accountId;
    }
}
