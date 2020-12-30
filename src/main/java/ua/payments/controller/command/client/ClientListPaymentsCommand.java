package ua.payments.controller.command.client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.payments.controller.command.Command;
import ua.payments.model.entity.Account;
import ua.payments.model.entity.Payment;
import ua.payments.model.service.AccountService;
import ua.payments.model.service.PaymentService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ClientListPaymentsCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ClientListPaymentsCommand.class);

    private AccountService accountService = new AccountService();
    private PaymentService paymentService = new PaymentService();


    @Override
    public String execute(HttpServletRequest request) {
        logger.info("execute() started!");
        String accountId = request.getParameter("account-id");
        logger.info("accountId - " + accountId);
        Account account = accountService.findById(Long.parseLong(accountId));
        request.setAttribute("account", account);
        final List<Payment> payments = paymentService.findByAccountId(Long.parseLong(accountId));
        request.setAttribute("payments", payments);

        return "/WEB-INF/client/client-listPayments.jsp";
    }
}
