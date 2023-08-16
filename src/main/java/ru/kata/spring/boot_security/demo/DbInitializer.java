package ru.kata.spring.boot_security.demo;

;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class DbInitializer {
    private final RoleService roleService;
    private final UserService userService;

    @Autowired
    public DbInitializer(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @PostConstruct
    public void initialization() {
        Role adminRole = new Role("ROLE_ADMIN");
        Role userRole = new Role("ROLE_USER");

        roleService.save(adminRole);
        roleService.save(userRole);
        User admin = new User("Admin", "Admin", (byte) 20, "admin", "password", Set.of(adminRole));
        userService.addUser(admin);

        roleService.save(userRole);
        User user = new User("User", "User", (byte) 20, "user", "password", Set.of(userRole));
        userService.addUser(user);
    }
}