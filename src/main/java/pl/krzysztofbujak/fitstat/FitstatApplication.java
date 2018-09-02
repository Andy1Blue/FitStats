package pl.krzysztofbujak.fitstat;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class FitstatApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(FitstatApplication.class, args);
	}

}
