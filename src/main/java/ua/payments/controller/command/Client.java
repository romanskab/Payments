package ua.payments.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Client implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object currentUser = session.getAttribute("currentUser");
        System.out.println("CLIENT: user - " + currentUser);
        return "WEB-INF/client/client-basis.jsp";
    }
}
