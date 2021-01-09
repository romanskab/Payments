package ua.payments.controller.command.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.payments.controller.command.Command;
import ua.payments.controller.command.client.ClientListAccountsCommand;
import ua.payments.model.entity.Account;
import ua.payments.model.entity.User;
import ua.payments.model.entity.enums.Role;
import ua.payments.model.service.AccountService;
import ua.payments.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AdminListClientsCommand implements Command {
    private static final Logger logger = LogManager.getLogger(AdminListClientsCommand.class);

    private UserService userService = new UserService();
    private AccountService accountService = new AccountService();

    @Override
    public String execute(HttpServletRequest request) {
        logger.info("execute() started!");

        final String clientId = request.getParameter("client-id");
        if (clientId != null && !clientId.equals("")) {
            logger.info("clientId - " + clientId);

            final User client = userService.findById(Integer.parseInt(clientId));
            request.setAttribute("client", client);
            final List<Account> accounts = accountService.findByClientId(client.getId());
            request.setAttribute("accounts", accounts);

            return "/WEB-INF/admin/admin-listAccountsOfClient.jsp";
        }

        final List<User> clients = userService.findByRole(Role.ROLE_CLIENT);
        request.setAttribute("clients", clients);
        return "/WEB-INF/admin/admin-listClients.jsp";
    }
}
