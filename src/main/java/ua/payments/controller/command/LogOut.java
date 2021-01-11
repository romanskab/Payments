package ua.payments.controller.command;

import javax.servlet.http.HttpServletRequest;

/**
 * This class is used for logout
 *
 * @author Roman Skab
 * @version 1.0
 */
public class LogOut implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        System.out.println("LogOut works !!!");
        CommandUtility.removeUserFromSessionAndContext(request);
        return "redirect:/index.jsp";
    }
}
