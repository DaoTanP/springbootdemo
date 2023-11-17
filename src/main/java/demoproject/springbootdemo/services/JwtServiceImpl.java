package demoproject.springbootdemo.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demoproject.springbootdemo.models.User;
import demoproject.springbootdemo.repositories.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;

@Service
public class JwtServiceImpl implements JwtService {
    private static String secret = "This_is_secret";
    private static long expirationDuration = 60;

    @Autowired
    UserRepository userRepository;

    public String generateToken(User user) {

        long milliTime = System.currentTimeMillis();
        long expirationTime = milliTime + expirationDuration * 1000;

        Date issuedAt = new Date(milliTime);
        Date expiredAt = new Date(expirationTime);

        // claims
        Claims claims = Jwts.claims()
                .setSubject(user.getEmail())
                .setIssuedAt(issuedAt)
                .setExpiration(expiredAt);

        // optional claims
        claims.put("name", user.getName());

        // generate jwt using claims
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public String getEmailFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject().toString();
    }

    public boolean isTokenValid(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException ex) {
            System.err.println("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            System.err.println("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            System.err.println("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            System.err.println("JWT claims string is empty.");
        }
        return false;
    }
}
