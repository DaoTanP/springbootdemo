package demoproject.springbootdemo.repositories;

import demoproject.springbootdemo.models.User;

public interface UserRepositoryCustom {
    public User findByEmail(String email);

    public boolean validateUser(User user);
}