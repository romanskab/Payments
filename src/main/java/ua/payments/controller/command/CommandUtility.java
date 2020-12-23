package ua.payments.controller.command;

import ua.payments.model.entity.User;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;

public class CommandUtility {

    static void setUserInSession(HttpServletRequest request, User user) {
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
    }

    static boolean checkUserIsLogged(HttpServletRequest request, String username) {
        HashSet<String> loggedUsers =
                (HashSet<String>) request.getSession().getServletContext().getAttribute("loggedUsers");
        if (loggedUsers == null) {
            loggedUsers = new HashSet<>();
        }
        System.out.println("loggedUsers: " + loggedUsers);

        if (loggedUsers.stream().anyMatch(username::equals)) {
            return true;
        }

        loggedUsers.add(username);
        request.getSession().getServletContext().setAttribute("loggedUsers", loggedUsers);
        return false;
    }

    public static void removeUserFromSessionAndContext(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ServletContext servletContext = session.getServletContext();

        User user = (User) session.getAttribute("user");
        session.removeAttribute("user");

        HashSet<String> loggedUsers = (HashSet<String>) servletContext.getAttribute("loggedUsers");
        loggedUsers.remove(user.getUsername());
        servletContext.setAttribute("servletContext", loggedUsers);
    }

}
