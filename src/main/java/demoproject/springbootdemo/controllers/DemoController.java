package demoproject.springbootdemo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    @RequestMapping("/")
    public String index() {
        return "index page";
    }

    @RequestMapping("/home")
    public String home() {
        return "home page";
    }
}
