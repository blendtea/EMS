package core.Service;

import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import core.Model.Profile;
import core.Model.User;

@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ProfileServiceTest {
	@Autowired
	private ProfileService profileService;
	Profile profile = new Profile();
	User user = new User();
	@DisplayName("プロフィール登録成功テスト")
	@Test
//	@Disabled //comment out this and requirements : user account
	public void ProfileRegistrationSuccessedTest() throws Exception {

		// [fatal] validation error

		user.setLastName("ADMIN");
		user.setFirstName("SYSTEM");
		user.setUserName("541");
		profile.setId((long) 15);
		profile.setLastName(user.getLastName());
		profile.setFirstName(user.getFirstName());
		profile.setUserName(user.getUserName());
		profile.setBirth("1990/01/01");
		profile.setAssigned("Individual");
		profile.setHobby("any");
		profile.setSchool("any");
		profile.setSex("");
		profile.setTown("any");
		profile.setMsg("any");

		profileService.save(profile);
		System.out.println(profile);
		Assert.assertNotNull(profile);
		}
}
