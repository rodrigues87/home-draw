package agilebit.homedraw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class HomeDrawApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(HomeDrawApplication.class, args);
	}
	
}
