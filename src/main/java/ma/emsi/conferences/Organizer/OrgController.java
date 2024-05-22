package ma.emsi.conferences.Organizer;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class OrgController {

    @GetMapping("home")
    public String getHome()
    {
        return "home";
    }

}
