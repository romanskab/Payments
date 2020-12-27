package ua.payments.controller.command.client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.payments.controller.command.Command;
import ua.payments.model.entity.Account;
import ua.payments.model.entity.User;
import ua.payments.model.service.AccountService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ClientListAccountsCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ClientListAccountsCommand.class);

    private AccountService accountService = new AccountService();

    @Override
    public String execute(HttpServletRequest request) {
        logger.info("execute() started!");
        User user = (User) request.getSession().getAttribute("user");
        List<Account> accounts = accountService.findByClientId(user.getId());
        request.setAttribute("accounts", accounts);

        String account = request.getParameter("account-id");
        System.out.println("parameter" + account);

        if (account != null) {
            Account account1 = accountService.findById(Long.valueOf(account));
            request.setAttribute("account", account1);
            return "/WEB-INF/client/client-account.jsp";
        }

        return "/WEB-INF/client/client-listAccounts.jsp";
    }
}
