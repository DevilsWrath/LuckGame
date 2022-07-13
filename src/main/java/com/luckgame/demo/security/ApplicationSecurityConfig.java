package com.luckgame.demo.security;

import com.luckgame.demo.auth.ApplicationUserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.concurrent.TimeUnit;

import static com.luckgame.demo.security.ApplicationUserRole.ADMIN;
import static com.luckgame.demo.security.ApplicationUserRole.CUSTOMER;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class ApplicationSecurityConfig  extends WebSecurityConfigurerAdapter {


    private final PasswordEncoder passwordEncoder;
    private final ApplicationUserService applicationUserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                    .authorizeRequests()
                    .antMatchers("/")
                    .permitAll()
                    .antMatchers("/api/v1/registration")
                    .permitAll()
                    .antMatchers("/api//v1/Customer")
                    .hasRole(ADMIN.name())
                    .anyRequest()
                    .authenticated()
                    .and()
                    .formLogin()
                    .loginPage("/login").permitAll()
                    .defaultSuccessUrl("/matches", true)
                    .and()
                    .rememberMe().tokenValiditySeconds((int)TimeUnit.DAYS.toSeconds(21))
                    .key("luckgame-demo-remember-me-key")
                .and()
                .logout()
                    .logoutUrl("/logout")
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout","GET"))
                    .clearAuthentication(true)
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID", "remember-me")
                    .logoutSuccessUrl("/login");
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        User.UserBuilder savasAdmin = User.builder()
                .username("savas")
                .password(passwordEncoder.encode("password"))
                .roles(ADMIN.name()) // ROLE_ADMIN
                .authorities(ADMIN.getGrantedAuthorities());

        User.UserBuilder sevimUser = User.builder().
                username("sevim")
                .password(passwordEncoder.encode("password"))
                .roles(ApplicationUserRole.CUSTOMER.name()) // ROLE_CUSTOMER
                .authorities(CUSTOMER.getGrantedAuthorities());

        return new InMemoryUserDetailsManager(
                savasAdmin.build()
                , sevimUser.build()
        );
    }
}
