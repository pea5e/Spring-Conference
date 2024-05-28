package ma.emsi.conferences.Auth;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthAPIController {

    @Autowired
    private UserRepo Repo;

    @Autowired
    private Environment env;
    @PostMapping("used")
    public String getUser(@RequestBody HashMap<String,String> data)
    {
        String mail = data.get("mail").toLowerCase();
        if(mail.equals(env.getProperty("spring.security.user.name")))
        {
            return "found";
        }
        Optional<User> u = Repo.getUserByEmail(mail);
        if(u.isPresent())
        {
            return "found";
        }
        return "not found";
    }


}
