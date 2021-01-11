package ua.payments.controller;

import ua.payments.controller.command.Exception;
import ua.payments.controller.command.*;
import ua.payments.controller.command.admin.*;
import ua.payments.controller.command.client.*;
import ua.payments.model.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * This class meets all requests.
 *
 * @author Roman Skab
 * @version 1.0
 */
public class Servlet extends HttpServlet {
    private Map<String, Command> commands = new HashMap<>();

    public void init() {
        commands.put("registration", new Registration(new UserService()));
        commands.put("login", new LogIn());
        commands.put("logout", new LogOut());
        commands.put("exception", new Exception());
        commands.put("client", new Client());
        commands.put("client/accounts", new ClientListAccountsCommand());
        commands.put("client/accounts/create", new ClientCreateAccountCommand());
        commands.put("client/accounts/replenishment", new ClientAccountReplenishment());
        commands.put("client/accounts/block", new ClientAccountBlock());
        commands.put("client/accounts/unblock", new ClientRequestToUnblock());
        commands.put("client/payments", new ClientListPaymentsCommand());
        commands.put("client/payments/new", new ClientPaymentCommand());
        commands.put("client/payments/send", new ClientSendPayment());
        commands.put("client/cards", new ClientCardCommand());
        commands.put("client/cards/create", new ClientCreateCardCommand());
        commands.put("admin", new Admin());
        commands.put("admin/clients", new AdminListClientsCommand());
        commands.put("admin/clients/block", new AdminClientBlockCommand());
        commands.put("admin/clients/unblock", new AdminClientUnblockCommand());
        commands.put("admin/clients/account/block", new AdminClientAccountBlock());
        commands.put("admin/clients/account/unblock", new AdminClientAccountUnblock());
        commands.put("admin/requests", new AdminListRequestsCommand());
        commands.put("admin/accounts/unblock", new AdminAccountUnblock());
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getRequestURI();
        System.out.println("RequestURI - " + path);
        path = path.replaceFirst("/payments/", "");
        System.out.println(path);
        Command command = commands.getOrDefault(path,
                (r) -> "/payments/index.jsp");
        String page = command.execute(request);
        System.out.println("page - " + page);
        if (page.contains("redirect:")) {
            response.sendRedirect(page.replace("redirect:", "/payments"));
        } else {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}
