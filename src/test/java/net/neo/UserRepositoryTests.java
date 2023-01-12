package net.neo;

import static org.assertj.core.api.Assertions.assertThat;

import net.neo.entity.User;
import net.neo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {

	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void testCreateUser() {
		User user = new User();
		user.setEmail("sagarjosh2@gmail.com");
		user.setPassword("sagar7781");
		user.setFirstName("sagar");
		user.setLastName("josh");
		
		User savedUser = userRepository.save(user);
		
		User existUser = entityManager.find(User.class, savedUser.getId());
		
		assertThat(user.getEmail()).isEqualTo(existUser.getEmail());
		
	}
	
	@Test
	public void testFindByEmail() {
		String email = "sagarjosh@gmail.com";
		User user = userRepository.findByEmail(email);
		
		assertThat(user.getEmail()).isEqualTo(email);
	}
}
