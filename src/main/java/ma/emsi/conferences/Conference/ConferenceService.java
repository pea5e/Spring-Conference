package ma.emsi.conferences.Conference;

import ma.emsi.conferences.Salle.ISalleRepository;
import ma.emsi.conferences.Salle.Salle;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ConferenceService {

    private final IConferenceRepository ConfRepo;

    @Autowired
    public ConferenceService(IConferenceRepository Repo) {
        ConfRepo = Repo;
    }

    public List<Conference> getConfs(){
        return ConfRepo.findAll();
    }

    public Optional<Conference> getConf(int id){
        return ConfRepo.findById(id);
    }

    public void addConf(Conference s)
    {
        ConfRepo.save(s);
    }

    public boolean deleteConf(int id)
    {
        if(ConfRepo.existsById(id)) {
            ConfRepo.deleteById(id);
            return true;
        }
        return false;
    }

    public Page<Conference> getConfsPage(int page){
        return ConfRepo.findAll(PageRequest.of(page,10));
    }

    public List<Conference> searchConfs(String filter)
    {
        return getConfs().stream().filter(c -> c.getTitle().toLowerCase().contains(filter)).collect(Collectors.toList());
    }


}
