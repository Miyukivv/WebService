package com.ProjektUmcs.firstWebService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
//@RestController //Oznacz to, że w tej klasie będą rzeczy, które będą się wyświetlać w aplikacji webowej
public class FirstWebServiceApplication {
//Napisz aplikację, która uruchomi serwer webowy, który po połączeniu wyświetli napis “Hello World”.
	public static void main(String[] args) {
		SpringApplication.run(FirstWebServiceApplication.class, args);
	}

//	@GetMapping("/hello")
//	public String sayHello(@RequestParam String who){
//		return "Hello " + who;
//	}
}
