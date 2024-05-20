package ma.emsi.conferences.Salle;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/salle")
public class SalleController {

    private SalleService Service;


    @Autowired
    public SalleController(SalleService service) {
        Service = service;
    }


    @GetMapping(path = "{id}")
    public Optional<Salle> getSalle(@PathVariable(value = "id")int id)
    {
        return Service.getSalle(id);
    }


    @PostMapping(path = "add")
    public Boolean AjouterSalle(@RequestBody Salle s)
    {
        Service.AddSalle(s);
        return true;
    }

    @PostMapping(path = "/update")
    public Boolean PutSalle(@RequestBody Salle s)
    {
        return Service.EditSalle(s);
    }

    @DeleteMapping(path = "/delete/{id}")
    public Boolean RemoveSalle(@PathVariable(value = "id")int id)
    {
        return Service.DeleteSalle(id);
    }


}
