package org.alticelabs.user.control;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.alticelabs.user.boundary.UserRequest;
import org.alticelabs.user.entity.User;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class UserService {

    public List<User> getAll() {
        return User.listAll();
    }

    public User getById(UUID id) {
        return User.findById(id);
    }

    @Transactional
    public User create(UserRequest req) {
        User user = new User();
        user.setName(req.name);
        user.setEmail(req.email);
        user.setPassword(req.password);
        user.persist();
        return user;
    }

    @Transactional
    public User update(UUID id, UserRequest req) {
        User user = User.findById(id);

        if (user == null) {
            return null;
        }

        user.setName(req.name);
        user.setEmail(req.email);
        user.setPassword(req.password);

        return user;
    }

    @Transactional
    public boolean delete(UUID id) {
        return User.deleteById(id);
    }

    public List<User> activeUsers() {
        return User.list("active", true);
    }
}