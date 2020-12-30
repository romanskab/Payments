package ua.payments.controller.command.client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.payments.controller.command.Command;
import ua.payments.model.entity.Account;
import ua.payments.model.entity.Card;
import ua.payments.model.entity.User;
import ua.payments.model.service.AccountService;
import ua.payments.model.service.CardService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ClientListAccountsCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ClientListAccountsCommand.class);

    private AccountService accountService = new AccountService();
    private CardService cardService = new CardService();

    @Override
    public String execute(HttpServletRequest request) {
        logger.info("execute() started!");
        User user = (User) request.getSession().getAttribute("user");
        List<Account> accounts = accountService.findByClientId(user.getId());
        request.setAttribute("accounts", accounts);

        String accountId = request.getParameter("account-id");
        System.out.println("parameter" + accountId);

        if (accountId != null) {
            Account account = accountService.findById(Long.valueOf(accountId));
            request.setAttribute("account", account);
            List<Card> cards = cardService.findByAccountId(account.getId());
            request.setAttribute("cards", cards);
            return "/WEB-INF/client/client-account.jsp";
        }

        return "/WEB-INF/client/client-listAccounts.jsp";
    }
}
