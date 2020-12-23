package ua.payments.controller.filters;

import ua.payments.model.entity.User;
import ua.payments.model.entity.enums.Role;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {
        System.out.println("AuthFilter:");
        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse res = (HttpServletResponse) response;

        HttpSession session = req.getSession();
        ServletContext context = request.getServletContext();
        System.out.println(session);
        System.out.println(session.getAttribute("user"));
        System.out.println(context.getAttribute("loggedUsers"));

        String path = req.getRequestURI();
        System.out.println("path: " + path);

        if (session.getAttribute("user") != null && path.equals("/payments/")){
            User user = (User) session.getAttribute("user");
            System.out.println(user.getRole().toString());
            if (user.getRole().equals(Role.ROLE_CLIENT)){
                ((HttpServletResponse) response).sendRedirect("/payments/client");
            }
            if (user.getRole().equals(Role.ROLE_ADMIN)){
                ((HttpServletResponse) response).sendRedirect("/payments/admin");
            }
        }

        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
