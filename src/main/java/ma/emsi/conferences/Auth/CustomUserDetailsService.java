package ma.emsi.conferences.Auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    public final UserRepo userrepo ;

    @Autowired
    private Environment env;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        username =  username.toLowerCase();
        if(username.equals(env.getProperty("spring.security.user.name")))
        {
            return new CustomUserDetails(env.getProperty("spring.security.user.name"),env.getProperty("spring.security.user.password"),new ArrayList<>(Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"))),true);
        }

        Optional<User> u = userrepo.getUserByEmail(username);
        if(u.isEmpty())
            return null;
        return new CustomUserDetails(u.get().getEmail(),u.get().getPassword(),new ArrayList<>(Collections.singleton(new SimpleGrantedAuthority("ROLE_"+u.get().getRole()))),u.get().getEnabled());
    }
}
