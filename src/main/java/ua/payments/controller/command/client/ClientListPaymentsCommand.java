package ua.payments.controller.command.client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.payments.controller.command.Command;
import ua.payments.model.entity.Account;
import ua.payments.model.entity.Payment;
import ua.payments.model.entity.User;
import ua.payments.model.service.AccountService;
import ua.payments.model.service.PaymentService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * This class is used for returning payments' list.
 *
 * @author Roman Skab
 * @version 1.0
 */
public class ClientListPaymentsCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ClientListPaymentsCommand.class);

    private AccountService accountService = new AccountService();
    private PaymentService paymentService = new PaymentService();


    @Override
    public String execute(HttpServletRequest request) {
        logger.info("execute() started!");
        String accountId = request.getParameter("account-id");
        logger.info("accountId - " + accountId);
        final String sortParameter = request.getParameter("sort-by");

        final int LIMIT = 10;

        if (accountId == null || accountId.equals("")) {
            final User user = (User) request.getSession().getAttribute("user");
            final int userId = user.getId();
            logger.info("userId - " + userId);

            List<Payment> payments;
            if (sortParameter != null && !sortParameter.equals("")) {
                logger.info("sortParameter - " + sortParameter);
                final int count = paymentService.findCountByUserId(userId);
                logger.info("count - " + count);

                final int currentPage;
                final String chosenPage = request.getParameter("page");
                if (chosenPage == null || chosenPage.equals("")) {
                    currentPage = 1;
                } else {
                    currentPage = Integer.parseInt(chosenPage);
                }
                if (count > LIMIT) {
                    request.setAttribute("page", currentPage);
                }
                if (count > LIMIT * currentPage) {
                    int next = currentPage + 1;
                    logger.info("next - " + next);
                    request.setAttribute("next", next);
                }
                if (currentPage > 1) {
                    int previous = currentPage - 1;
                    logger.info("previous - " + previous);
                    request.setAttribute("previous", previous);
                }
                final int offset = (currentPage - 1) * LIMIT;

                payments = paymentService.findByUserAndSortByFieldAndPagination(userId, sortParameter, offset, LIMIT);
                request.setAttribute("sort", sortParameter);
            } else {
                final int count = paymentService.findCountByUserId(userId);
                logger.info("count - " + count);

                final int currentPage;
                final String chosenPage = request.getParameter("page");
                if (chosenPage == null || chosenPage.equals("")) {
                    currentPage = 1;
                } else {
                    currentPage = Integer.parseInt(chosenPage);
                }
                if (count > LIMIT) {
                    request.setAttribute("page", currentPage);
                }
                if (count > LIMIT * currentPage) {
                    int next = currentPage + 1;
                    logger.info("next - " + next);
                    request.setAttribute("next", next);
                }
                if (currentPage > 1) {
                    int previous = currentPage - 1;
                    logger.info("previous - " + previous);
                    request.setAttribute("previous", previous);
                }
                final int offset = (currentPage - 1) * LIMIT;

                payments = paymentService.findByUserIdAndPagination(userId, offset, LIMIT);
            }

            request.setAttribute("payments", payments);
            return "/WEB-INF/client/client-allPayments.jsp";
        }

        Account account = accountService.findById(Long.parseLong(accountId));
        request.setAttribute("account", account);
        final List<Payment> payments = paymentService.findByAccountId(Long.parseLong(accountId));
        request.setAttribute("payments", payments);

        return "/WEB-INF/client/client-listPayments.jsp";
    }
}
