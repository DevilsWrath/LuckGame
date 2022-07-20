package com.luckgame.demo;

import static org.assertj.core.api.Assertions.assertThat;

import com.luckgame.demo.Role.Role;
import com.luckgame.demo.repo.RoleRepo;
import com.luckgame.demo.repo.UserRepo;
import com.luckgame.demo.user.AppUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)

public class UserRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Test
    public void testAddRoleToExistingUser() {

        AppUser user2 = userRepo.findById(2L).get();
        Role roleCustomer = roleRepo.findByName("CUSTOMER");

        user2.addRole(roleCustomer);
        AppUser savedUser2 = userRepo.save(user2);

        assertThat(savedUser2.getRoles().size()).isEqualTo(1);
    }

    @Test
    public void testAddRoleToExistingAdmin() {
        AppUser user = userRepo.findById(1L).get();
        Role roleAdmin = roleRepo.findByName("ADMIN");

        user.addRole(roleAdmin);

        AppUser savedUser = userRepo.save(user);

        assertThat(savedUser.getRoles().size()).isEqualTo(2);
    }
}
