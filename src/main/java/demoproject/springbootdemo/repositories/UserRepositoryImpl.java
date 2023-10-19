package demoproject.springbootdemo.repositories;

import java.util.Iterator;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import demoproject.springbootdemo.models.User;

class UserRepositoryImpl implements UserRepositoryCustom {

    @Autowired
    @Lazy
    UserRepository userRepository;

    @Override
    public Optional<User> findByEmail(String email) {
        Iterator<User> users = userRepository.findAll().iterator();

        while (users.hasNext()) {
            User user = users.next();
            if (user.getEmail().equals(email)) {
                return Optional.of(user);
            }
        }

        return Optional.empty();
    }
}
