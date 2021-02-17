package ru.stepintegrator.task.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.stepintegrator.task.AuthorizedUser;
import ru.stepintegrator.task.model.User;
import ru.stepintegrator.task.repository.CrudUserRepository;

import java.util.List;

import static ru.stepintegrator.task.util.UserUtil.prepareToSave;

@Service
public class UserService implements UserDetailsService {

    private final CrudUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(CrudUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.getByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User " + email + " is not found");
        }
        return new AuthorizedUser(user);
    }

    public User create(User user) {
        Assert.notNull(user, "user must not be null");
        return prepareAndSave(user);
    }

    public void delete(int id) {
        userRepository.delete(id);
    }

    public User get(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public User getByEmail(String email) {
        Assert.notNull(email, "email must not be null");
        return userRepository.getByEmail(email);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public void update(User user) {
        Assert.notNull(user, "user must not be null");
        prepareAndSave(user);
    }

    private User prepareAndSave(User user) {
        return userRepository.save(prepareToSave(user, passwordEncoder));
    }
}
