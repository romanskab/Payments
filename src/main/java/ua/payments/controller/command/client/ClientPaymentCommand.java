package ua.payments.controller.command.client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.payments.controller.command.Command;
import ua.payments.model.entity.Account;
import ua.payments.model.entity.Payment;
import ua.payments.model.service.AccountService;
import ua.payments.model.service.PaymentService;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

/**
 * This class is used for creating new payments.
 *
 * @author Roman Skab
 * @version 1.0
 */
public class ClientPaymentCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ClientPaymentCommand.class);

    private AccountService accountService = new AccountService();
    private PaymentService paymentService = new PaymentService();

    @Override
    public String execute(HttpServletRequest request) {
        logger.info("execute() started!");
        String accountId = request.getParameter("account-id");
        String sum = request.getParameter("sum");
        String comment = request.getParameter("comment");
        logger.info("accountId - " + accountId);
        logger.info("sum - " + sum);
        logger.info("comment - " + comment);
        Account account = accountService.findById(Long.parseLong(accountId));
        request.setAttribute("account", account);
        if (sum != null) {
            logger.info("sum != null: " + sum);
            final BigDecimal sumDecimal = BigDecimal.valueOf(Double.parseDouble(sum));
            Payment payment = new Payment();
            payment.setSum(sumDecimal);
            payment.setAccount(account);
            payment.setComment(comment);
            paymentService.create(payment);
            request.setAttribute("account-id", accountId);
            return "redirect:/client/payments?account-id=" + accountId;
        }

        return "/WEB-INF/client/client-payment.jsp";
    }
}
