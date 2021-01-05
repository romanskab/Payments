package ua.payments.controller.command.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.payments.controller.command.Command;
import ua.payments.model.entity.enums.State;
import ua.payments.model.service.AccountService;

import javax.servlet.http.HttpServletRequest;

public class AdminAccountUnblock implements Command {
    private static final Logger logger = LogManager.getLogger(AdminListRequestsCommand.class);

    private final AccountService accountService = new AccountService();

    @Override
    public String execute(HttpServletRequest request) {
        logger.info("execute() started!");
        String accountId = request.getParameter("account-id");
        logger.info("accountId - " + accountId);
        accountService.changeState(Long.parseLong(accountId), State.UNBLOCKED);
        return "redirect:/admin/requests";
    }
}
