package fr.ewaux.scrapping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication(scanBasePackages = {
	"fr.ewaux"
})
@EnableWebMvc
public class ScrappingApplication {

	public static void main(String... args) {
		SpringApplication.run(ScrappingApplication.class, args);
	}
}
