package demoproject.springbootdemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import demoproject.springbootdemo.services.JwtService;

@Controller
public class DemoController {
    @Autowired
    private JwtService authService;

    @GetMapping("/")
    public String index() {
        return "homePage";
    }

    @RequestMapping("/privateApi")
    public @ResponseBody ResponseEntity<String> privateApi(
            @RequestHeader(value = "authorization", defaultValue = "") String token) {
        if (authService.isTokenValid(token))
            return ResponseEntity.status(200).body("Private access");

        return ResponseEntity.status(401).body("Access denied");
    }
}
