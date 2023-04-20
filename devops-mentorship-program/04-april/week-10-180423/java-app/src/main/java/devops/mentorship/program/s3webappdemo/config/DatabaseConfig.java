package devops.mentorship.program.s3webappdemo.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;

@Configuration
public class DatabaseConfig {
	@Value("${app.datasource.schema:}")
	private String schema;
	
	@Value("${app.secrets.db:}")
	private String dbSecretId;
	
	@Bean
	@ConditionalOnProperty(name = "app.secrets.enabled", havingValue = "true")
	public DataSource getDataSource(SecretsManagerClient secretsManager) throws JsonMappingException, JsonProcessingException {
		AWSSecretDBModel dbModel = getDBUserPass(secretsManager);
		return DataSourceBuilder.create().url("jdbc:mysql://" + dbModel.host + "/" + this.schema)
				.driverClassName("com.mysql.cj.jdbc.Driver").username(dbModel.username)
				.password(dbModel.password).build();
	}
	
	private AWSSecretDBModel getDBUserPass(SecretsManagerClient secretsManager) throws JsonMappingException, JsonProcessingException {
		GetSecretValueRequest getSecretValueRequest = GetSecretValueRequest.builder()
	            .secretId(this.dbSecretId)
	            .build();

	    GetSecretValueResponse getSecretValueResponse;

	    try {
	        getSecretValueResponse = secretsManager.getSecretValue(getSecretValueRequest);
	    } catch (Exception e) {
	        // For a list of exceptions thrown, see
	        // https://docs.aws.amazon.com/secretsmanager/latest/apireference/API_GetSecretValue.html
	        throw e;
	    }

	    String secret = getSecretValueResponse.secretString();
	    return (new ObjectMapper()).readValue(secret, AWSSecretDBModel.class);
	}
	
	@Getter
	@Setter
	public static final class AWSSecretDBModel {
		private String username;
		private String password;
		private String host;
		private String port;
		private String dbname;
		private String engine;
		private String dbInstanceIdentifier;
	}
}
