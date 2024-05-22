package ma.emsi.conferences.Auth;


import ma.emsi.conferences.EnvLinks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserController {


    @Autowired
    private final UserService service;


    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/admin/users")
    public String getAllUsers(Model model)
    {
        model.addAttribute("users", service.getAllUsers());
        return "users";
    }

}
