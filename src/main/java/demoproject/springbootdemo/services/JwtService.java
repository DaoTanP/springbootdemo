package demoproject.springbootdemo.services;

import demoproject.springbootdemo.models.User;

public interface JwtService {
    public String generateToken(User user);

    public String getEmailFromJWT(String token);

    public boolean isTokenValid(String token);
}
