package info.pobu.blb.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@EntityScan("info.pobu.blb.entities")
@ComponentScan("info.pobu.blb.controllers")
@EnableJpaRepositories("info.pobu.blb.repositories")
@CrossOrigin(origins = "http://localhost:4200")
public class Application {
    
    @RequestMapping("/")
    public  @ResponseBody  String greet() {
        System.out.println("jesttttes w greet");
        return "hello Angular. Welcome on birdwatchers-log-book";
    }

	public static void main(String... args) {
		SpringApplication.run(Application.class, args);
	}
}
