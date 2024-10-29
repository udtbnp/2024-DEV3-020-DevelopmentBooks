package be.bnp.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages={"be.bnp.demo", "be.bnp.processor"})
public class DemoApplication {
	
		public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		
	}
	
	public static String upper(String s) {
		return s.toUpperCase();
	}
}
