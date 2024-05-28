package ma.emsi.conferences.Auth;


import jakarta.mail.MessagingException;
import ma.emsi.conferences.EnvLinks;
import ma.emsi.conferences.Mail.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Random;


@Controller
@RequestMapping("/admin")
public class AuthController {

    @Autowired
    private AuthService auth;

    @Autowired
    private EmailService emailService;

    @Autowired
    private VerifyService verify;


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

    @RequestMapping (value = "signup", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String SignPage(UserDTO user, @RequestHeader(HttpHeaders.REFERER) String ref, RedirectAttributes atts,Model model) throws MessagingException {
        Role r = Role.USER ;
        if(ref.startsWith(EnvLinks.SERVER.URL()))
            r = Role.ORG;

        User u = auth.register(user,r.ROLE());
        String code = "";
        Random rand = new Random();
        for(int i=0;i<6;i++)
        {
            code += String.valueOf(rand.nextInt(10));
        }

        emailService.SendMail(code,user.getEmail(), user.getName(), r.ROLE());
        verify.addVerification(code,u);

        model.addAttribute("url", EnvLinks.SERVER.URL()+"/verify?email="+user.getEmail());
        return "redirect";
    }

}
