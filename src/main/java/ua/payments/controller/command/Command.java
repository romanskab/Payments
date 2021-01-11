package ua.payments.controller.command;

import javax.servlet.http.HttpServletRequest;

/**
 * This interface is used for classes, that process requests
 *
 * @author Roman Skab
 * @version 1.0
 */
public interface Command {
    String execute(HttpServletRequest request);
}
