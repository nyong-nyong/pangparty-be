package nyongnyong.pangparty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class PangpartyApplication {

	public static void main(String[] args) {
		SpringApplication.run(PangpartyApplication.class, args);
	}

}
