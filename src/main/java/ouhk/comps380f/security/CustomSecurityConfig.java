package ouhk.comps380f.security;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class CustomSecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthenticationProvider authenticationProvider;

    @Autowired
    public CustomSecurityConfig(AuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        System.out.println("CONFIGURING AuthenticationManager");
        auth
                .authenticationProvider(authenticationProvider)
                .inMemoryAuthentication()
                .withUser("root")
                .password("{noop}root1234")
                .roles("USER", "ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("CONFIGURING HttpSecurity");
        http.
                logout().logoutUrl("/logout").logoutSuccessUrl("/").invalidateHttpSession(true).deleteCookies("JSESSIONID")
                .and()
                .rememberMe().key("unique-key").tokenValiditySeconds(86400).rememberMeParameter("remember-me")
                .and()
                .formLogin().loginPage("/login").failureUrl("/login?error").usernameParameter("username").passwordParameter("password")
                .and()
                .authorizeRequests()
                .antMatchers("/admin").hasAnyRole("ADMIN");
    }
}
