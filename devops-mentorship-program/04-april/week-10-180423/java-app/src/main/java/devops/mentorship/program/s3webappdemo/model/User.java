package devops.mentorship.program.s3webappdemo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
    @Column(nullable = false)
	private String firstName;
    @Column(nullable = false)
	private String lastName;
    @Column(nullable = false)
	private String email;
}
