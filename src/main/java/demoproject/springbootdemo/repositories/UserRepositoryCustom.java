package demoproject.springbootdemo.repositories;

import java.util.Optional;

import demoproject.springbootdemo.models.User;

public interface UserRepositoryCustom {
    public Optional<User> findByEmail(String email);
}