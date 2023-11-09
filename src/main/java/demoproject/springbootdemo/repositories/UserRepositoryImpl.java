package demoproject.springbootdemo.repositories;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import demoproject.springbootdemo.models.User;

class UserRepositoryImpl implements UserRepositoryCustom {

    @Autowired
    @Lazy
    UserRepository userRepository;

    @Override
    public User findByEmail(String email) {
        Iterator<User> users = userRepository.findAll().iterator();

        while (users.hasNext()) {
            User user = users.next();
            if (user.getEmail().equals(email)) {
                return user;
            }
        }

        return null;
    }

    @Override
    public boolean validateUser(User user) {
        if (user.getName() == null ||
                user.getName() == "" ||
                user.getEmail() == null ||
                user.getEmail() == "" ||
                user.getEmail().indexOf("@") == -1 ||
                user.getPassword() == null ||
                user.getPassword() == "") {
            return false;
        }

        return true;
    }
}
