package midgard.code.thor.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ThorApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThorApiApplication.class, args);
	}

}
