package ma.emsi.conferences.Auth;


import net.sf.jsqlparser.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("admin")
public class AuthController {

    @GetMapping("login")
    public String LoginPage(Model model)
    {
        return "login";
    }


}
