package devops.mentorship.program.s3webappdemo.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import devops.mentorship.program.s3webappdemo.exception.NotFoundException;
import devops.mentorship.program.s3webappdemo.service.S3Service;
import devops.mentorship.program.s3webappdemo.service.UserService;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private S3Service s3Service;

	@GetMapping("/users")
	public String users(Model model) {
		model.addAttribute("users", this.userService.findAll());
		return "users";
	}

	@GetMapping("/user/{id}")
	public String user(Model model, @PathVariable Long id) {
		model.addAttribute("user", this.userService.findById(id).orElseThrow(() -> new NotFoundException()));
		return "user";
	}
	
	@GetMapping("/user/{id}/profile-image")
	public ResponseEntity<StreamingResponseBody> profileImage(@PathVariable Long id) throws IOException {
		ResponseInputStream<GetObjectResponse> stream = this.s3Service.getProfileImage(id);
		final StreamingResponseBody body = outputStream -> {
            int numberOfBytesToWrite = 0;
            byte[] data = new byte[1024];
            while ((numberOfBytesToWrite = stream.read(data, 0, data.length)) != -1) {
                outputStream.write(data, 0, numberOfBytesToWrite);
            }
            stream.close();
        };
        return new ResponseEntity<>(body, HttpStatus.OK);
	}
	
	@PostMapping("/user/{id}/profile-image")
	public String uploadProfileImage(@RequestParam("file") MultipartFile file, @PathVariable Long id,
			RedirectAttributes redirectAttributes) throws IOException {
		this.s3Service.uploadProfileImage(id, file);
		return "redirect:/user/" + id;
	}

}
