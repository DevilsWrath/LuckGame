package com.luckgame.demo.repo;

import com.luckgame.demo.user.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepo extends JpaRepository<AppUser, Long> {
    @Query("select u from AppUser u where u.username = ?1")
    Optional<AppUser> findByUsername(String username);
    @Query("select u from AppUser u where u.username = ?1")
    Optional<AppUser> findByEmail(String email);
    @Query("select u from AppUser u where u.username = ?1")
    AppUser findUserByUsername(String username);
    @Query("select u from AppUser u where u.ID = ?1")
    AppUser findByID(Long ID);
    AppUser findIDByUsername(String username);


}


