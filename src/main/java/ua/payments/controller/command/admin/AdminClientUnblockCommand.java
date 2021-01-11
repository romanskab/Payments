package ua.payments.controller.command.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.payments.controller.command.Command;
import ua.payments.model.entity.User;
import ua.payments.model.entity.enums.Role;
import ua.payments.model.entity.enums.State;
import ua.payments.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * This class is used for unblocking of clients.
 *
 * @author Roman Skab
 * @version 1.0
 */
public class AdminClientUnblockCommand implements Command {
    private static final Logger logger = LogManager.getLogger(AdminListClientsCommand.class);

    private UserService userService = new UserService();

    @Override
    public String execute(HttpServletRequest request) {
        logger.info("execute() started!");
        final String clientId = request.getParameter("client-id");
        userService.changeState(State.UNBLOCKED, Integer.parseInt(clientId));

        final List<User> clients = userService.findByRole(Role.ROLE_CLIENT);
        request.setAttribute("clients", clients);
        return "redirect:/admin/clients";
    }
}
