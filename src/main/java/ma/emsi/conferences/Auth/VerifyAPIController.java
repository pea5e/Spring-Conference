package ma.emsi.conferences.Auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/verify")
public class VerifyAPIController {

        @Autowired
        private VerifyService service;

        @Autowired
        private UserService user;


        @PostMapping("code")
        public String Verify(@RequestBody HashMap<String,String> data)
        {
            return String.valueOf(service.verify(data.get("code"),user.getUserByEmail(data.get("email")).get()));
        }

        @PostMapping("isVerified")
        public String isVerify(@RequestBody HashMap<String,String> data)
        {
            return String.valueOf(user.getUserByEmail(data.get("email")).get().getEnabled());
        }



}
