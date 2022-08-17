package com.luckgame.demo.repo;

import com.luckgame.demo.user.AppUser;
import org.hibernate.annotations.Fetch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.FetchType;
import java.util.Optional;


@Repository
public interface UserRepo extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByUsername(String username);
    Optional<AppUser> findByEmail(String email);
    AppUser findUserByUsername(String username);
    AppUser findByID(Long ID);
}


