package com.stacksimplify.restservices.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloWorldController {
    @Autowired
    private ResourceBundleMessageSource messageSource;

    //    @RequestMapping(method = RequestMethod.GET, path = "/helloworld-1")
    @GetMapping(path = "/helloworld")
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping(path = "helloworld-bean")
    public UserDetails helloWorldBean() {
        return new UserDetails("John", "Doe", "Kano");
    }

    @GetMapping(path = "helloworld-i18n")
    public String helloWorldI18n(@RequestHeader(name = "Accept-Language", required = false, defaultValue = "") String locale) {
        return messageSource.getMessage("label.hello", null, new Locale(locale));
    }

    @GetMapping(path = "helloworld-i18n-2")
    public String helloWorldI18n() {
        return messageSource.getMessage("label.hello", null, LocaleContextHolder.getLocale());
    }
}
