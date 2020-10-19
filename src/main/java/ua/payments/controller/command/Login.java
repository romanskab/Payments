package ua.payments.controller.command;

import ua.payments.model.entity.User;

import javax.servlet.http.HttpServletRequest;

public class Login implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String name = request.getParameter("username");
        String pass = request.getParameter("password");
        System.out.println(name + " " + pass);
        if( name == null || name.equals("") || pass == null || pass.equals("")  ){
            return "/login.jsp";
        }
        // TODO go to Service
//        return "redirect:/client/client-basis.jsp";
        return "/WEB-INF/client/client-basis.jsp";
    }
}
