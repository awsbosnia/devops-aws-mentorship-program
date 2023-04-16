package devops.mentorship.program.s3webappdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import devops.mentorship.program.s3webappdemo.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
