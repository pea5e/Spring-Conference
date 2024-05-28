package ma.emsi.conferences.Salle;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/salles")
public class SalleAPIController {

    private SalleService Service;


    @Autowired
    public SalleAPIController(SalleService service) {
        Service = service;
    }

    @GetMapping(path = "")
    public List<Salle> getSalles(Model model)
    {
        return Service.getSalles();
    }

    @GetMapping(path = "{id}")
    public Optional<Salle> getSalle(@PathVariable(value = "id")int id)
    {
        return Service.getSalle(id);
    }




}
