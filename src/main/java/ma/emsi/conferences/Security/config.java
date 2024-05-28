package ma.emsi.conferences.Security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;

@Configuration
public class config  implements ApplicationContextAware {
    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity security) throws Exception {
        security.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(auth -> auth.requestMatchers("/admin/home","/admin/conferences","/admin/conferences/add").authenticated()
                .requestMatchers("/admin/users","/admin/salles","/admin/users/**","/admin/salles/**","/admin/conferences/ticket/**").hasAnyRole("ADMIN").anyRequest().permitAll()).cors(AbstractHttpConfigurer::disable).formLogin(f -> f.loginPage("/admin/login").permitAll().defaultSuccessUrl("/admin/home").failureHandler(new AuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
                response.sendRedirect("/admin/login?error="+exception.getMessage());
            }
        }));

//        security.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(auth -> auth.requestMatchers("/*jpg","/*png","/admin/signup","/api/**").permitAll()
//                .requestMatchers("/admin/users","/admin/salles").hasAnyRole("ADMIN").anyRequest().authenticated()).cors(AbstractHttpConfigurer::disable).formLogin(f -> f.loginPage("/admin/login").permitAll().defaultSuccessUrl("/admin/home").failureHandler(new AuthenticationFailureHandler() {
//            @Override
//            public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
//                response.sendRedirect("/admin/login?error="+exception.getMessage());
//            }
//        }));

        return security.build();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }

    @Bean
    PasswordEncoder passwordEncoder()
    {
        return NoOpPasswordEncoder.getInstance();
    }
}
