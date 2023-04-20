package devops.mentorship.program.s3webappdemo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;

@SpringBootTest
class S3WebappDemoApplicationTest {

  @MockBean
  private S3Client s3Client;

  @MockBean
  private SecretsManagerClient secretsManagerClient;

  @Test
  void contextLoads() {}

}
