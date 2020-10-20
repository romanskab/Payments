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
        User currentUser = new User();
        if (name == null || name.equals("") || pass == null || pass.equals("")) {
            return "/login.jsp";
        } else {
            UserService userService = new UserService();
            List<User> users = userService.findAll();
            for (User user : users) {
                if (user.getUsername().equals(name) && user.getPassword().equals(pass)) {
                    currentUser = user;
                    System.out.println("currentUser: " + currentUser);
                    return "/WEB-INF/client/client-basis.jsp";
                }
            }
        }
        // TODO go to Service

//        return "redirect:/client/client-basis.jsp";
        return "/login.jsp";
    }
}
