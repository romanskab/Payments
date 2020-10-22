package ua.payments.controller.command;

import ua.payments.model.entity.User;
import ua.payments.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class Login implements Command {
//    private UserService userService;

    @Override
    public String execute(HttpServletRequest request) {
        String name = request.getParameter("username");
        String pass = request.getParameter("password");
        System.out.println(name + " " + pass);
        User currentUser;
        if (name == null || name.equals("") || pass == null || pass.equals("")) {
            return "/login.jsp";
        }
        try{
            UserService userService = new UserService();
            currentUser = userService.findByUsername(name);
            System.out.println("currentUser: " + currentUser);
            return "redirect:/client/client-basis.jsp";
        }catch (java.lang.Exception e){
            e.printStackTrace();
        }
        // TODO go to Service

//        return "redirect:/client/client-basis.jsp";
        return "/login.jsp";
    }
}
