package ma.emsi.conferences.Conference;

import ma.emsi.conferences.Salle.Salle;
import ma.emsi.conferences.Salle.SalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/conferences")
public class ConferenceAPIController {

    private ConferenceService Service;


    @Autowired
    public ConferenceAPIController(ConferenceService service) {
        Service = service;
    }

    @GetMapping(path = "")
    public List<Conference> getConfs(Model model)
    {
        return Service.getConfs();
    }

    @GetMapping(path = "{id}")
    public Optional<Conference> getConf(@PathVariable(value = "id")int id)
    {
        return Service.getConf(id);
    }
    @GetMapping(path = "?q={q}")
    public List<Conference> searchConf(@PathVariable(value = "q")String q)
    {
        return Service.searchConfs(q);
    }


}