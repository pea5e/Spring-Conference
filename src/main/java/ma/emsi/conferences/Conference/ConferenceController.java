package ma.emsi.conferences.Conference;


import ma.emsi.conferences.Auth.UserService;
import ma.emsi.conferences.EnvLinks;
import ma.emsi.conferences.Salle.Salle;
import ma.emsi.conferences.Salle.SalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin/conferences")
public class ConferenceController {


    private ConferenceService Service;
    private SalleService sService;
    private UserService uService;


    @Autowired
    public ConferenceController(ConferenceService service,SalleService s,UserService u) {
        Service = service;
        sService = s;
        uService = u;
    }


    @GetMapping(path = "")
    public String getConfs(Model model)
    {

        List<Conference> confs = Service.getConfs();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getAuthorities().stream().findFirst().toString().contains("ORG"))
        {
            confs = confs.stream().filter(c -> c.getOrg().getEmail().equals(((UserDetails)auth.getPrincipal()).getUsername())).collect(Collectors.toList());
        }
        model.addAttribute("confs",confs);
        return "conferences";
    }

    @GetMapping(path = "add")
    public String AjouterConfPage(Model model)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("salles", sService.getSalles());
        model.addAttribute("user", uService.getUserByEmail(((UserDetails) auth.getPrincipal()).getUsername()));
        System.out.println(((UserDetails) auth.getPrincipal()).getUsername());
        return "formconference";
    }

//    @GetMapping(path = "edit/{id}")
//    public String ModifierConfPage(@PathVariable(value = "id")int id, Model model)
//    {
//        model.addAttribute("conf",Service.getConf(id).get());
//        return "formconference";
//    }

    @PostMapping(path = "add")
    public String AjouterConf(Conference s,Model model)
    {
        Service.addConf(s);
        model.addAttribute("url", EnvLinks.SERVER.URL()+"/admin/conferences");
        return "redirect";
    }

//    @PostMapping(path = "edit/{id}")
//    public String editConf(Conference s,Model model)
//    {
//        Service.addConf(s);
//        model.addAttribute("url", EnvLinks.SERVER.URL()+"/admin/salles");
//        return "redirect";
//    }
//
//    @GetMapping(path = "delete/{id}")
//    public String Removeconf(@PathVariable(value = "id")int id,Model model)
//    {
//        Service.deleteConf(id);
//        model.addAttribute("url", EnvLinks.SERVER.URL()+"/admin/salles");
//        return "redirect";
//    }

}
