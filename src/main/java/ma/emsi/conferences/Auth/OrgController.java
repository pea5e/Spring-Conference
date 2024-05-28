package ma.emsi.conferences.Auth;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class OrgController {

    @GetMapping("home")
    public String getHome(Model model)
    {
        String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().findFirst().toString();

        model.addAttribute("role",role.substring(14,role.length()-1));

        return "home";
    }

}
