package demoproject.springbootdemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demoproject.springbootdemo.utils.JwtUtils;

@RestController
public class DemoController {
    @Autowired
    private JwtUtils jwtUtils;

    @RequestMapping("/")
    public String index() {
        return "index page";
    }

    @RequestMapping("/privateApi")
    public ResponseEntity<String> privateApi(@RequestHeader(value = "authorization", defaultValue = "") String auth) {
        if(jwtUtils.verify(auth) != null)
            return ResponseEntity.status(200).body("Private access");
        
            return ResponseEntity.status(401).body("Access denied");
    }
}
