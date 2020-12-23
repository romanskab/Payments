package ua.payments.controller.listener;

import ua.payments.model.entity.User;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashSet;

public class SessionListener implements HttpSessionListener {
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        ServletContext servletContext = session.getServletContext();

        User user = (User) session.getAttribute("user");
        HashSet<String> loggedUsers = (HashSet<String>) servletContext.getAttribute("loggedUsers");

        loggedUsers.remove(user.getUsername());
        session.removeAttribute("user");
        servletContext.setAttribute("loggedUsers", loggedUsers);
    }
}
