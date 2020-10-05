package ua.payments.controller.command;

import javax.servlet.http.HttpServletRequest;

public class LogOut implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        System.out.println("LogOut works !!!");
        return "redirect:/index.jsp";
    }
}
