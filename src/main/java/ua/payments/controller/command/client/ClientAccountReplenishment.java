package ua.payments.controller.command.client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.payments.controller.command.Command;
import ua.payments.model.service.AccountService;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

/**
 * This class is used for replenishment of client's account.
 *
 * @author Roman Skab
 * @version 1.0
 */
public class ClientAccountReplenishment implements Command {
    private static final Logger logger = LogManager.getLogger(ClientCardCommand.class);

    private AccountService accountService = new AccountService();

    @Override
    public String execute(HttpServletRequest request) {
        logger.info("execute() started!");
        String accountId = request.getParameter("account-id");
        String sum = request.getParameter("sum");

        if (sum != null) {
            Long account = Long.valueOf(accountId);
            logger.info("accountLong - " + account);
            BigDecimal decimalSum = BigDecimal.valueOf(Double.parseDouble(sum));
            logger.info("decimalSum - " + decimalSum);
            accountService.increaseBalance(account, decimalSum);
            return "redirect:/client/accounts";
        }

        request.setAttribute("accountId", accountId);
        return "/WEB-INF/client/client-accountReplenishment.jsp";
    }
}
