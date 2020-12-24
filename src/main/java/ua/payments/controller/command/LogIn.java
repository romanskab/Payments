package ua.payments.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.payments.exception.UserAlreadyLoggedException;
import ua.payments.exception.UserNotFoundException;
import ua.payments.model.entity.User;
import ua.payments.model.entity.enums.Role;
import ua.payments.model.service.UserService;
import ua.payments.model.util.Encryptor;

import javax.servlet.http.HttpServletRequest;

public class LogIn implements Command {
    private static final Logger logger = LogManager.getLogger(LogIn.class);

    private UserService userService = new UserService();

    @Override
    public String execute(HttpServletRequest request) {
        logger.info("execute() started!");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        logger.info("username: " + username + ", password: " + password);

        if (username == null || username.equals("") || password == null || password.equals("")) {
            return "/login.jsp";
        }

        if (CommandUtility.checkUserIsLogged(request, username)){
            UserAlreadyLoggedException exception = new UserAlreadyLoggedException("User already logged!");
            logger.error("This user already logged!", exception);
            throw exception;
        }

        User user;
        try {
            user = userService.findByUsernameAndPassword(username, Encryptor.encrypt(password));
        }catch (java.lang.Exception e){
            UserNotFoundException exception = new UserNotFoundException("User not found!");
            logger.error("There is no user with such username and password!", exception);
            throw exception;
        }
        if (user.getRole().equals(Role.ROLE_CLIENT)) {
            CommandUtility.setUserInSession(request, user);
            CommandUtility.addUserToLoggedUsers(request, username);
            return "redirect:/client";
        } else if (user.getRole().equals(Role.ROLE_ADMIN)) {
            CommandUtility.setUserInSession(request, user);
            CommandUtility.addUserToLoggedUsers(request, username);
            return "redirect:/admin";
        } else {
            return "/login.jsp";
        }

    }
}
