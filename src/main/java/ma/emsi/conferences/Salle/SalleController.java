package ma.emsi.conferences.Salle;


import ma.emsi.conferences.EnvLinks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/admin/salles")
public class SalleController {

    private SalleService Service;


    @Autowired
    public SalleController(SalleService service) {
        Service = service;
    }


    @GetMapping(path = "")
    public String getSalles(Model model)
    {
        model.addAttribute("salles",Service.getSalles());
        return "salles";
    }

//    @GetMapping(path = "{id}")
//    public String getSalle(@PathVariable(value = "id")int id,Model model)
//    {
//        model.addAttribute("salle",Service.getSalle(id));
//        return "salle";
//    }


    @GetMapping(path = "add")
    public String AjouterSallePage(Model model)
    {
        model.addAttribute("salle",new Salle(-1,1,null));
        return "formsalle";
    }

    @GetMapping(path = "edit/{id}")
    public String ModifierSallePage(@PathVariable(value = "id")int id,Model model)
    {
        model.addAttribute("salle",Service.getSalle(id).get());
        return "formsalle";
    }

    @PostMapping(path = "add")
    public String AjouterSalle(Salle s,Model model)
    {
        Service.AddSalle(s);
        model.addAttribute("url", EnvLinks.SERVER.URL()+"/admin/salles");
        return "redirect";
    }

    @PostMapping(path = "edit/{id}")
    public String PutSalle(Salle s,Model model)
    {
        Service.AddSalle(s);
        model.addAttribute("url", EnvLinks.SERVER.URL()+"/admin/salles");
        return "redirect";
    }

    @GetMapping(path = "delete/{id}")
    public String RemoveSalle(@PathVariable(value = "id")int id,Model model)
    {
            Service.DeleteSalle(id);
            model.addAttribute("url", EnvLinks.SERVER.URL()+"/admin/salles");
            return "redirect";
    }


}
