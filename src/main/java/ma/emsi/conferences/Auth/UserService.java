package ma.emsi.conferences.Auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepo repo;

    @Autowired
    public UserService(UserRepo repo) {
        this.repo = repo;
    }

    public List<User> getAllUsers()
    {
        return repo.findAll();
    }

    public Optional<User> getUserByEmail(String email)
    {
        return repo.getUserByEmail(email);
    }



}
