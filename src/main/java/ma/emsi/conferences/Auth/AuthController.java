package ma.emsi.conferences.Auth;


import ma.emsi.conferences.EnvLinks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/admin")
public class AuthController {

    @Autowired
    private AuthService auth;


    @GetMapping("login")
    public String LoginPage(Model model)
    {
        return "login";
    }

    @GetMapping("signup")
    public String SignPage(Model model)
    {
        return "signup";
    }

//    @PostMapping("signup")
    @RequestMapping (value = "signup", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String SignPage( UserDTO user, @RequestHeader(HttpHeaders.REFERER) String ref, Model model)
    {
        Role r = Role.USER ;
        if(ref.startsWith(EnvLinks.SERVER.URL()))
            r = Role.ORG;

        auth.register(user,r.ROLE());
        model.addAttribute("url", EnvLinks.SERVER.URL()+"/admin/login");
        return "redirect";
    }

}
