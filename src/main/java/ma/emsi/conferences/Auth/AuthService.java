package ma.emsi.conferences.Auth;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.net.http.HttpRequest;

@Service
public class AuthService {

    private final UserRepo userrepo;

    @Autowired
    public AuthService(UserRepo userrepo) {
        this.userrepo = userrepo;
    }


    public User register(UserDTO u, String role)
    {
        User user = new User(0,u.getEmail().toLowerCase(),u.getPassword(),u.getName(),u.getPhone(),role,false);
        userrepo.save(user);
        return user;
    }

    public boolean isEnabled()
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth.isAuthenticated())
        {
            UserDetails user = (UserDetails) auth.getPrincipal();
            return user.isEnabled();
        }
        return false;
    }


}
