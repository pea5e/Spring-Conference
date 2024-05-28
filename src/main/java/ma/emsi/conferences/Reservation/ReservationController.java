package ma.emsi.conferences.Reservation;


import jakarta.mail.MessagingException;
import ma.emsi.conferences.Conference.Conference;
import ma.emsi.conferences.Conference.ConferenceService;
import ma.emsi.conferences.Mail.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping("/api/v1/reserver")
public class ReservationController {
    @Autowired
    ReservationService Res;
    @Autowired
    ConferenceService Conf;
    @Autowired
    private EmailService emailService;

    @PostMapping("")
    public void reserver(@RequestBody HashMap<String ,String> data) throws MessagingException {
        String email = data.get("email");
        System.out.println(email);
        int id = Integer.parseInt(data.get("id"));

        Conference c = Conf.getConf(id).get();

        String code = "";
        Random rand = new Random();
        for(int i=0;i<30;i++)
        {
            code += (char) 97+rand.nextInt(26);
        }
        System.out.println(code);
        Res.add(new Reservation(0,c.getReservations().size()+1,code,c,email));
        emailService.Sendticket(code,email);

    }

    @PostMapping("ticket")
    public ReservationDTO check(@RequestBody HashMap<String ,String> data) throws MessagingException {
        String code = data.get("code");
        Optional<Reservation> r = Res.get(code);
        if(r.isPresent())
        {
            return new ReservationDTO(r.get().getSiege(),r.get().getConference().getTitle(),r.get().getConference().getDate()
            ,r.get().getConference().getSalle().getName(),r.get().getConference().getDuree_minutes(),r.get().getUserEmail());
        }
        return new ReservationDTO();
    }






}
