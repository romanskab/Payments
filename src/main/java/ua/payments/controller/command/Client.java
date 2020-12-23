package ua.payments.controller.command;

import ua.payments.model.entity.User;
import ua.payments.model.entity.enums.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Client implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        System.out.println("CLIENT: user - " + user);

        if (user.getRole().equals(Role.ROLE_CLIENT)){
            return "WEB-INF/client/client-basis.jsp";
        }

        return "/login.jsp";
    }
}
