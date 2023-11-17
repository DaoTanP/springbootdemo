package demoproject.springbootdemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import demoproject.springbootdemo.repositories.UserRepository;
import demoproject.springbootdemo.services.JwtService;

@Controller
public class DemoController {
    @Autowired
    private JwtService authService;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/privateAccess")
    public @ResponseBody ResponseEntity<String> privateAccess(
            @RequestHeader(value = "authorization", defaultValue = "") String token) {

        if (authService.isTokenValid(token)) {
            String email = authService.getEmailFromJWT(token);
            if (userRepository.findByEmail(email) != null) {
                return ResponseEntity.status(200).body("Private access");
            }
        }

        return ResponseEntity.status(401).body("Access denied");
    }
}
