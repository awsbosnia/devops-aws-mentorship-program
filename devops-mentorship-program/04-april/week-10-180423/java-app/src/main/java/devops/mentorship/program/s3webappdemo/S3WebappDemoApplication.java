package devops.mentorship.program.s3webappdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("devops.mentorship.program.s3webappdemo.model")
public class S3WebappDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(S3WebappDemoApplication.class, args);
	}

}
