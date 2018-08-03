package com.projectMVC.configuratin;


import com.projectMVC.entity.User;
import com.projectMVC.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    final static Logger log = Logger.getLogger(SecurityConfig.class.getName());

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder(8);
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/","/registration","/activate/*","/home").permitAll()
                    .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .successHandler(successHandler)
                    .failureHandler(failureHandler)
                    .permitAll()
                .and()
                    .rememberMe()
                .and()
                    .logout()
                    .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(passwordEncoder);
    }

    private final AuthenticationSuccessHandler successHandler = new AuthenticationSuccessHandler() {
        @Override
        public void onAuthenticationSuccess(HttpServletRequest request,
                                            HttpServletResponse response,
                                            Authentication authentication) throws IOException, ServletException {

            HttpSession session = request.getSession();
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User user = userService.findByUserName(userDetails.getUsername());
            request.getSession().setAttribute("currentUserId", user.getId());
            request.getSession().setAttribute("currentUserName", user.getUsername());
            request.getSession().setAttribute("isAdmin", user.isAdmin());
            request.getSession().setAttribute("currentUser", user != null);

            response.setStatus(HttpServletResponse.SC_OK);
            log.info(response.getHeaderNames());
            response.sendRedirect("/main");

            System.out.println("---------------------------------" + user.getId());
            System.out.println("---------------------------------" + user.getPassword());
            System.out.println("---------------------------------" + request.getRemoteAddr());
        }
    };

    private final AuthenticationFailureHandler failureHandler = new AuthenticationFailureHandler() {
        @Override
        public void onAuthenticationFailure(HttpServletRequest request,
                                            HttpServletResponse response,
                                            AuthenticationException e) throws IOException, ServletException {

            HttpSession session = request.getSession(false);
            request.getSession().setAttribute("error", "Wrong data");
            String url = null;

            if(session != null){
                url = (String) request.getSession().getAttribute("url_prior_login");
            }
            System.err.println(" ===================================== Referrer URL : " + url);

            if(url != null){
                response.sendRedirect("/login");
            }
        }
    };
}
