package ma.emsi.conferences.Auth;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VerifyService {

    @Autowired
    private VerifyREpo repo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    public VerifyService(VerifyREpo repo) {
        this.repo = repo;
    }

    public void addVerification(String code,User user)
    {
        repo.save(new Verification(0,code,user));
    }

    public boolean verify(String code,User user)
    {
        Optional<Verification> v = repo.getByUser(user.getId());
        if(v.isPresent() && v.get().getCode().equals(code))
        {
            try {
                userRepo.verify(user.getId());
            }
            catch (Exception e){
                System.out.println("updated");
            }
            return true;
        }
        return false;
    }

}
