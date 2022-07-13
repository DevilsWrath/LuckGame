package com.luckgame.demo.auth;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public class ApplicationUserService  implements UserDetailsService {

    private final ApplicationUserDAO applicationUserDAO;

    public ApplicationUserService(@Qualifier("ApplicationUserDAOService") ApplicationUserDAO applicationUserDAO) {
        this.applicationUserDAO = applicationUserDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return applicationUserDAO.selectApplicationUserByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(String.format("Username %s not found",username)));
    }
    public String signUpUser(ApplicationUser user){
        return "";
    }
}
