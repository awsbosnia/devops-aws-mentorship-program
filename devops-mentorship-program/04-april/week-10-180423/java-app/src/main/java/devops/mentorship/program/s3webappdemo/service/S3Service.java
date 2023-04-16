package devops.mentorship.program.s3webappdemo.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.HeadObjectRequest;
import software.amazon.awssdk.services.s3.model.NoSuchKeyException;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
public class S3Service {

	private static final String PROFILE_IMAGES_PATH_PREFIX = "profile-images/";

	@Value("${app.profile.image.s3.bucket}")
	private String bucket;

	@Autowired
	private S3Client s3client;

	public void uploadProfileImage(Long id, MultipartFile profileImage) throws IOException {
		this.s3client.putObject(getPutRequestForId(id), getRequestBodyForFile(profileImage));
	}

	public ResponseInputStream<GetObjectResponse> getProfileImage(Long id) {
		if (exists(id)) {
			return this.s3client.getObject(getGetRequestForId(id));
		} else {
			return getDefaultProfileImage();
		}
	}

	private ResponseInputStream<GetObjectResponse> getDefaultProfileImage() {
		return this.s3client.getObject(getGetRequestForKey(PROFILE_IMAGES_PATH_PREFIX + "default.png"));
	}

	private boolean exists(Long id) {
		try {
			this.s3client.headObject(HeadObjectRequest.builder()
					.bucket(this.bucket).key(getS3PathForId(id)).build());
			return true;
		} catch (NoSuchKeyException e) {
			return false;
		}
	}

	private RequestBody getRequestBodyForFile(MultipartFile file) throws IOException {
		return RequestBody.fromInputStream(file.getInputStream(), file.getSize());
	}

	private GetObjectRequest getGetRequestForId(Long id) {
		return getGetRequestForKey(getS3PathForId(id));
	}
	
	private GetObjectRequest getGetRequestForKey(String key) {
		return GetObjectRequest.builder().bucket(this.bucket).key(key).build();
	}

	private PutObjectRequest getPutRequestForId(Long id) {
		return PutObjectRequest.builder().bucket(this.bucket).key(getS3PathForId(id)).build();
	}

	private String getS3PathForId(Long id) {
		return PROFILE_IMAGES_PATH_PREFIX + reverse(id);
	}

	private String reverse(Long id) {
		return new StringBuilder(String.valueOf(id)).reverse().toString();
	}

}
