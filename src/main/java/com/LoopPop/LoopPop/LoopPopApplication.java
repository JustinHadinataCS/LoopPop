package com.LoopPop.LoopPop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class LoopPopApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoopPopApplication.class, args);
	}
	@Controller
	public class LoopPopController {
		@GetMapping("/index")
		public String index() {
            return "index";
        }



	}
}
