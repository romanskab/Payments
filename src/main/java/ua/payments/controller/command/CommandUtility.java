package ua.payments.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.payments.model.entity.User;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;

public class CommandUtility {
    private static final Logger logger = LogManager.getLogger(CommandUtility.class);

    public static void setUserInSession(HttpServletRequest request, User user) {
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
    }

    public static boolean checkUserIsLogged(HttpServletRequest request, String username) {
        HashSet<String> loggedUsers =
                (HashSet<String>) request.getSession().getServletContext().getAttribute("loggedUsers");
        if (loggedUsers == null) {
            loggedUsers = new HashSet<>();
        }
        logger.info("loggedUsers: " + loggedUsers);
        return loggedUsers.stream().anyMatch(username::equals);
    }

    public static void addUserToLoggedUsers(HttpServletRequest request, String username){
        HashSet<String> loggedUsers =
                (HashSet<String>) request.getSession().getServletContext().getAttribute("loggedUsers");
        if (loggedUsers == null) {
            loggedUsers = new HashSet<>();
        }
        loggedUsers.add(username);
        logger.info("user added to loggedUsers");
        request.getSession().getServletContext().setAttribute("loggedUsers", loggedUsers);
    }

    public static void removeUserFromSessionAndContext(HttpServletRequest request){
        HttpSession session = request.getSession();
        ServletContext servletContext = session.getServletContext();

        User user = (User) session.getAttribute("user");
        session.removeAttribute("user");

        HashSet<String> loggedUsers = (HashSet<String>) servletContext.getAttribute("loggedUsers");
        loggedUsers.remove(user.getUsername());
        servletContext.setAttribute("loggedUsers", loggedUsers);
    }

    public static void removeUserFromContext(HttpSession session){
        User user = (User) session.getAttribute("user");

        ServletContext servletContext = session.getServletContext();
        HashSet<String> loggedUsers = (HashSet<String>) servletContext.getAttribute("loggedUsers");

        loggedUsers.remove(user.getUsername());
        servletContext.setAttribute("loggedUsers", loggedUsers);
    }
}
