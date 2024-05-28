package ma.emsi.conferences.Auth;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Controller
public class VerifyController {

    @Autowired
    private VerifyService service;

    @Autowired
    private UserService user;

    @GetMapping("/verify")
    public String getVerifyPage(Model model)
    {
        return "verify";
    }

    @PostMapping("/verify")
    public String getVerifyPage(@RequestParam("code")String code,@RequestParam("email") String email, Model model)
    {
        service.verify(code,user.getUserByEmail(email).get());
        model.addAttribute("email",email);
        return "verify";
    }

}
