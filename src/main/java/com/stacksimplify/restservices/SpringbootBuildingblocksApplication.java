package com.stacksimplify.restservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.Locale;

@SpringBootApplication
public class SpringbootBuildingblocksApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootBuildingblocksApplication.class, args);
    }

    @Bean
    public LocaleResolver getLocalResolver() {
        AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
        localeResolver.setDefaultLocale(Locale.getDefault());
        return localeResolver;
    }

    @Bean
    public ResourceBundleMessageSource getResourceBundleMessageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		return messageSource;
	}

}
