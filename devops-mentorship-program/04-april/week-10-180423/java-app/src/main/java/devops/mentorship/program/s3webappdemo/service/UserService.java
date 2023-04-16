package devops.mentorship.program.s3webappdemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import devops.mentorship.program.s3webappdemo.model.User;
import devops.mentorship.program.s3webappdemo.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> findAll() {
		 return this.userRepository.findAll();
	}
	
	public Optional<User> findById(Long id) {
		return this.userRepository.findById(id);
	}
	
	public User save(User user) {
		return this.userRepository.save(user);
	}

}
