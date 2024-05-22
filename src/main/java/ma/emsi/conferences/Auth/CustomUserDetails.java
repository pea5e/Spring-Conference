package ma.emsi.conferences.Auth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private final String email;
    private final String password;
    private final List<GrantedAuthority> roles;
    private final boolean activated;

    public CustomUserDetails(String email, String password, List<GrantedAuthority> roles, boolean activated) {
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.activated = activated;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.isAccountNonLocked();
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isEnabled();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.isAccountNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return this.activated;
    }
}
