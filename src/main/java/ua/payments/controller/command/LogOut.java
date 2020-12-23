package ua.payments.controller.command;

import javax.servlet.http.HttpServletRequest;

public class LogOut implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        System.out.println("LogOut works !!!");
        CommandUtility.removeUserFromSessionAndContext(request);
        return "redirect:/index.jsp";
    }
}
