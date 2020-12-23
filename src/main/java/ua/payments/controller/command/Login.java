package ua.payments.controller.command;

import ua.payments.model.entity.User;
import ua.payments.model.entity.enums.Role;
import ua.payments.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class Login implements Command {
//    private UserService userService;

    @Override
    public String execute(HttpServletRequest request) {
        String name = request.getParameter("username");
        String pass = request.getParameter("password");
        System.out.println(name + " " + pass);

        if (name == null || name.equals("") || pass == null || pass.equals("")) {
            return "/login.jsp";
        }

        try{
            UserService userService = new UserService();
            User userFromDB = userService.findByUsername(name);

            if (userFromDB.getPassword().equals(pass)){
                userFromDB.setPassword(null);
                System.out.println("user: " + userFromDB);

                if (CommandUtility.checkUserIsLogged(request, name)){
                    return "/login.jsp";
                }
                if (userFromDB.getRole().equals(Role.ROLE_ADMIN)){
                    CommandUtility.setUserInSession(request, userFromDB);
                    return "redirect:/admin";
                }
                if (userFromDB.getRole().equals(Role.ROLE_CLIENT)){
                    CommandUtility.setUserInSession(request, userFromDB);
                    return "redirect:/client";
                }
            }else {
                return "/login.jsp";
            }

        }catch (java.lang.Exception e){
            e.printStackTrace();
        }

        return "/login.jsp";
    }
}
