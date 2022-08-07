package com.luckgame.demo.security;

import com.luckgame.demo.service.MyUserDetails;
import com.luckgame.demo.service.MyUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.concurrent.TimeUnit;

import static com.luckgame.demo.security.ApplicationUserRole.ADMIN;
import static com.luckgame.demo.security.ApplicationUserRole.CUSTOMER;


@AllArgsConstructor
@EnableWebSecurity
public class ApplicationSecurityConfig  extends WebSecurityConfigurerAdapter {


    @Bean
    public UserDetailsService userDetailsService() {
        return new MyUserDetailsService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Autowired
    private CustomAuthenticationSuccessHandler loginSuccessHandler;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/fonts/**","/css/**","/images/**","/js/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                    .authorizeRequests().antMatchers("/", "/fonts/**","/css/**","/images/**","/js/**").
                permitAll()
                    .antMatchers("/").permitAll()
                    .antMatchers("/api//v1/Customer").hasRole("ADMIN")
                    .antMatchers("/admin/**").hasRole("ADMIN")
                    .antMatchers("/matches/**").hasRole("CUSTOMER")
                    .antMatchers("/transactions/**").hasRole("CUSTOMER")
                    .antMatchers("/bet/**").hasRole("CUSTOMER")
                    .anyRequest()
                    .authenticated()
                .and()
                    .formLogin()
                .successHandler(loginSuccessHandler).permitAll()
                    .loginPage("/login").permitAll()
                    .failureUrl("/login?error=true")
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





    /*@Override
    @Bean
    protected UserDetailsService userDetailsService() {
        AppUser.UserBuilder savasAdmin = AppUser.builder()
                .username("savas")
                .password(passwordEncoder.encode("password"))
                .roles(ADMIN.name()) // ROLE_ADMIN
                .authorities(ADMIN.getGrantedAuthorities());

        AppUser.UserBuilder sevimUser = AppUser.builder().
                username("sevim")
                .password(passwordEncoder.encode("password"))
                .roles(ApplicationUserRole.CUSTOMER.name()) // ROLE_CUSTOMER
                .authorities(CUSTOMER.getGrantedAuthorities());

        return new InMemoryUserDetailsManager(
                savasAdmin.build()
                , sevimUser.build()
        );
    }*/
}
