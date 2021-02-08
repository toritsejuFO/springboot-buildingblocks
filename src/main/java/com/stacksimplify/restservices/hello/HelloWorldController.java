package com.stacksimplify.restservices.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    //    @RequestMapping(method = RequestMethod.GET, path = "/helloworld-1")
    @GetMapping(path = "/helloworld")
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping(path = "helloworld-bean")
    public UserDetails helloWorldBean() {
        return new UserDetails("John", "Doe", "Kano");
    }
}
