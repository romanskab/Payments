package ua.payments.controller.command;

import javax.servlet.http.HttpServletRequest;

/**
 * This class is used for exceptions
 *
 * @author Roman Skab
 * @version 1.0
 */
public class Exception implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        throw new RuntimeException("Generated exception");
    }
}
