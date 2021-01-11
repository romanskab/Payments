package ua.payments.controller.filters;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.payments.exception.ForbiddenException;
import ua.payments.model.entity.User;
import ua.payments.model.entity.enums.Role;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.stream.Stream;

/**
 * This filter-class checks users' access to resources
 *
 * @author Roman Skab
 * @version 1.0
 */
public class AuthFilter implements Filter {
    private static final Logger logger = LogManager.getLogger(AuthFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) request;

        HttpSession session = req.getSession();

        String path = req.getRequestURI();
        logger.info("path: " + path);

        if (session.getAttribute("user") == null) {
            Stream<String> urls = Stream.of(
                    "/payments/",
                    "/payments/registration.jsp",
                    "/payments/login.jsp",
                    "/payments/registration",
                    "/payments/login");
            if (urls.noneMatch(path::equals)) {
                HttpServletResponse resp = (HttpServletResponse) response;
                resp.sendRedirect("/payments/");
                return;
            }

        }

        if (session.getAttribute("user") != null && !path.equals("/payments/logout")) {
            User user = (User) session.getAttribute("user");
            logger.info("current user: " + user);

            Role role = user.getRole();
            String roleInStringPathFormat = role.name().toLowerCase().substring(5);

            if (path.equals("/payments/")) {
                HttpServletResponse resp = (HttpServletResponse) response;
                resp.sendRedirect("/payments/" + roleInStringPathFormat);
                return;
            }

            String pathForAccess = path.replace("/payments/", "");
            if (!pathForAccess.startsWith(roleInStringPathFormat)){
                ForbiddenException exception = new ForbiddenException("User doesn't have access to the resource");
                logger.info("Access denied !!!", exception);
                throw exception;
            }
        }

        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
