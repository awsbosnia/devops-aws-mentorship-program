package devops.mentorship.program.s3webappdemo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.InstanceProfileCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;

@Configuration
public class AWSConfig {
	
	@Value("${app.profile.image.s3.access:}")
	private String accessKey;
	
	@Value("${app.profile.image.s3.secret:}")
	private String secretKey;

	@Bean
	@Profile("!local & !test")
	public AwsCredentialsProvider instanceCredentialsProvider() {
		return InstanceProfileCredentialsProvider.create();
	}
	
	@Bean
	@Profile("local")
	public AwsCredentialsProvider basicCredentialsProvider() {
		return StaticCredentialsProvider.create(AwsBasicCredentials.create(this.accessKey, this.secretKey));
	}
	
	@Bean
	@Profile("!test")
	public S3Client amazonS3(AwsCredentialsProvider credentialsProvider) {
		return S3Client.builder().credentialsProvider(credentialsProvider).build();
	}
	
	@Bean
	@Profile("!test")
	public SecretsManagerClient secretsManager(AwsCredentialsProvider credentialsProvider) {
		return SecretsManagerClient.builder().credentialsProvider(credentialsProvider).build();
	}
	
}
