package com.pizzaDeliveryApp.pizzaApi;

import com.pizzaDeliveryApp.pizzaApi.service.IMPL.JwtFilter;
import com.pizzaDeliveryApp.pizzaApi.constant.Constants;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;


@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class PizzaApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PizzaApiApplication.class, args);
	}
   //use logging slf4j/ logback logger instead of syso.
	//ToDo remove unused imports

	//TODO move all urls in constants
	@Bean
	public FilterRegistrationBean jwtFilter() {
		FilterRegistrationBean frb = new FilterRegistrationBean();
		frb.setFilter(new JwtFilter());
		frb.addUrlPatterns(Constants.Paths.VIEW_MENU, Constants.Paths.HELLO, Constants.Paths.CREATE_ORDER, Constants.Paths.TRACK_GROUP_ORDER);
		return frb;
	}

}