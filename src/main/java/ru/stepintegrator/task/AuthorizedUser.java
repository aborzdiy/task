package ru.stepintegrator.task;

import org.springframework.security.core.GrantedAuthority;
import ru.stepintegrator.task.model.User;

import java.util.Collection;

public class AuthorizedUser extends org.springframework.security.core.userdetails.User {

    public AuthorizedUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public AuthorizedUser(User user) {
        super(user.getEmail(), user.getPassword(), user.getRoles());
    }
}
