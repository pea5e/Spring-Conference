package ma.emsi.conferences;


import jakarta.mail.MessagingException;
import ma.emsi.conferences.Mail.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@SpringBootApplication
@Controller
public class ConferencesApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConferencesApplication.class, args);
    }

//    @EventListener(ApplicationReadyEvent.class)
//    public void SendMail() throws MessagingException {
//        return;
//    }




    @GetMapping("/error")
    public String  error(Model model)
    {
        model.addAttribute("error", HttpStatus.INTERNAL_SERVER_ERROR);
        return "error";
    }


}
