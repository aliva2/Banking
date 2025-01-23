package com.example.Banking;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// when I start app spring looks at all classes in package and
// and looks if it knows any restControllers to make instance
// of this Banking Controller
public class BankingController {

    @GetMapping("/")
    //the user browser gonna sent https request to server
    public String hello() {
        return "Welcome to Anitas Bank!";
    }
}
