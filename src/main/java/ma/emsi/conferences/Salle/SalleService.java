package ma.emsi.conferences.Salle;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalleService {

    private final ISalleRepository SalleRepo;

    @Autowired
    public SalleService(ISalleRepository salleRepo) {
        SalleRepo = salleRepo;
    }

    public List<Salle> getSalles(){
        return SalleRepo.findAll();
    }

    public Optional<Salle> getSalle(int id){
        return SalleRepo.findById(id);
    }

    public void AddSalle(Salle s)
    {
        SalleRepo.save(s);
    }

    public boolean EditSalle(Salle s)
    {
        if(SalleRepo.existsById(s.getId())) {
            SalleRepo.Update(s.getId(),s.getNombre_places(),s.getName());
            return true;
        }
        return false;
    }

    public boolean DeleteSalle(int id)
    {
        if(SalleRepo.existsById(id)) {
            SalleRepo.deleteById(id);
            return true;
        }
        return false;
    }


}
