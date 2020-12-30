package ua.payments.controller.command.client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.payments.controller.command.Command;
import ua.payments.model.entity.Account;
import ua.payments.model.entity.Payment;
import ua.payments.model.entity.enums.Status;
import ua.payments.model.service.AccountService;
import ua.payments.model.service.PaymentService;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

public class ClientSendPayment implements Command {
    private static final Logger logger = LogManager.getLogger(ClientListPaymentsCommand.class);

    private AccountService accountService = new AccountService();
    private PaymentService paymentService = new PaymentService();

    @Override
    public String execute(HttpServletRequest request) {
        logger.info("execute() started!");
        final String paymentId = request.getParameter("payment-id");
        logger.info("paymentId - " + paymentId);
        final Payment payment = paymentService.findById(Integer.parseInt(paymentId));
        logger.info("payment - " + payment);
        final BigDecimal sum = payment.getSum();
        final BigDecimal balance = payment.getAccount().getBalance();
        if (sum.compareTo(balance) > 0){
            logger.info("sum > balance");
            paymentService.changeStatus(payment.getId(), Status.REJECTED);
        }else {
            logger.info("sum <= balance");
            accountService.decreaseBalance(payment.getAccount().getId(), sum, payment.getId());
        }

        return "redirect:/client/payments?account-id=" + payment.getAccount().getId();
    }
}
