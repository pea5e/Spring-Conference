package ma.emsi.conferences.Auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(username.equals("admin"))
        {
            return new CustomUserDetails("admin","admin",new ArrayList<>(Collections.singleton(new SimpleGrantedAuthority("ROLE_admin"))),true);
        }

        Optional<User> u = userrepo.getUserByEmail(username);
        if(u.isEmpty())
            return null;
        return new CustomUserDetails(u.get().getEmail(),u.get().getPassword(),new ArrayList<>(Collections.singleton(new SimpleGrantedAuthority("ROLE_"+u.get().getRole()))),u.get().getEnabled());
    }
}
