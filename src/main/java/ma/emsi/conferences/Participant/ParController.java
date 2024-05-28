package ma.emsi.conferences.Participant;


import ma.emsi.conferences.Auth.UserService;
import ma.emsi.conferences.Conference.ConferenceService;
import ma.emsi.conferences.EnvLinks;
import ma.emsi.conferences.Salle.SalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.stream.Collectors;

@Controller
public class ParController {

    private ConferenceService Service;


    @Autowired
    public ParController(ConferenceService service,SalleService s,UserService u) {
        Service = service;
    }

    @GetMapping("/")
    public String  home(Model model)
    {
        model.addAttribute("confs",Service.getConfs().stream().filter(c -> c.getReservations().size()<c.getSalle().getNombre_places()).collect(Collectors.toList()));
        return "index";
    }
    @GetMapping("/?q={q}")
    public String  home(@PathVariable("q")String q, Model model)
    {
        model.addAttribute("confs",Service.searchConfs(q).stream().filter(c -> c.getReservations().size()<c.getSalle().getNombre_places()).collect(Collectors.toList()));
        return "index";
    }

}
