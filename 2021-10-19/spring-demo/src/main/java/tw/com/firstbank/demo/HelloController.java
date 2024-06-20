package tw.com.firstbank.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	// get, post, put, delete

	@GetMapping
	// @GetMapping("/")
	String hello() {
		return "Hello World";
	}

	// @PostMapping("/{greeting}")
	@RequestMapping(method = { RequestMethod.POST, RequestMethod.GET }, 
					path = "/{greeting}")
	String hello(@PathVariable("greeting") String name) {
		return "Hello " + name;
	}

	
	
	
	
	
	
}
