package ma.emsi.conferences.Auth;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.http.HttpRequest;

@Service
public class AuthService {

    private final UserRepo userrepo;

    @Autowired
    public AuthService(UserRepo userrepo) {
        this.userrepo = userrepo;
    }


    public void register(UserDTO u, String role)
    {
        User user = new User(0,u.getEmail(),u.getPassword(),u.getName(),u.getPhone(),role,! false);
        userrepo.save(user);
    }


}
