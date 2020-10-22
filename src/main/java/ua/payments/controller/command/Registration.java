package ua.payments.controller.command;

import ua.payments.model.entity.User;
import ua.payments.model.entity.enums.Role;
import ua.payments.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class Registration implements Command {

    private UserService userService;

    public Registration(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (firstName == null || firstName.equals("")
        || lastName == null || lastName.equals("")
        || username == null || username.equals("")
        || password == null || password.equals("")){
            return "/registration.jsp";
        }else {
            User user = new User();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setUsername(username);
            user.setPassword(password);
            user.setRole(Role.ROLE_CLIENT);
            System.out.println(user);

            userService.create(user);
            return "redirect:/login.jsp";
        }

//        return "/registration.jsp";
    }
}
