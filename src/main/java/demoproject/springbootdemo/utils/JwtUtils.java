package demoproject.springbootdemo.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import demoproject.springbootdemo.models.User;

import java.util.Date;

@Component
public class JwtUtils {

    private static String secret = "This_is_secret";
    private static long expirationDuration = 60 * 60;

    public String generateJwt(User user) {

        long milliTime = System.currentTimeMillis();
        long expirationTime = milliTime + expirationDuration * 1000;

        Date issuedAt = new Date(milliTime);
        Date expiredAt = new Date(expirationTime);

        // claims
        Claims claims = Jwts.claims()
                .setIssuer(user.getId().toString())
                .setIssuedAt(issuedAt)
                .setExpiration(expiredAt);

        // optional claims
        // claims.put("type", user.getUserType());
        claims.put("name", user.getName());
        claims.put("email", user.getEmail());

        // generate jwt using claims
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public Claims verify(String authorization) {

        try {
            Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(authorization).getBody();
            return claims;
        } catch (Exception e) {
            return null;
        }

    }
}