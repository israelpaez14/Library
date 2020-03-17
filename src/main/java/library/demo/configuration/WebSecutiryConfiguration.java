package library.demo.configuration;

import library.demo.models.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
public class WebSecutiryConfiguration extends WebSecurityConfigurerAdapter {


    @Autowired
    UserRepository userRepository;

    @Autowired
    UserDetailServiceImplementation userDetailServiceImplementation;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/index","/css/**","/signup").permitAll().anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").usernameParameter("username").passwordParameter(
                "password").failureUrl(
                "/login?error=true").defaultSuccessUrl(
                "/other").permitAll().and().logout().permitAll();
    }

    @Autowired
    public void configGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailServiceImplementation);
    }




    @Bean
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder passwordEncoder1 = new BCryptPasswordEncoder();
        return passwordEncoder1;
    }
}
