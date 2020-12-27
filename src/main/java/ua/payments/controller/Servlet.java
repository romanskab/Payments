package ua.payments.controller;

import ua.payments.controller.command.Exception;
import ua.payments.controller.command.*;
import ua.payments.controller.command.admin.Admin;
import ua.payments.controller.command.admin.AdminListClientsCommand;
import ua.payments.controller.command.admin.AdminListRequestsCommand;
import ua.payments.controller.command.client.*;
import ua.payments.model.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
        commands.put("client/payments", new ClientListPaymentsCommand());
        commands.put("client/cards", new ClientListCardsCommand());
        commands.put("client/cards/create", new ClientCreateCardCommand());
        commands.put("admin", new Admin());
        commands.put("admin/clients", new AdminListClientsCommand());
        commands.put("admin/requests", new AdminListRequestsCommand());
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
        path = path.replaceAll(".*/payments/", "");
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
