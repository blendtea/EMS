package core.Service;

import org.junit.Assert;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import core.Model.User;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
class UserServiceTest {
	@Autowired
	private UserService userService;

	User user = new User();
	@DisplayName("アカウント登録成功テスト")
	@Test
	@Disabled //comment out this
	public void AccountRegistrationSuccessedTest() throws Exception {
		user.setLastName("ADMIN");
		user.setFirstName("SYSTEM");
		user.setUserName("541");
		user.setPassword("thisPassword");
		userService.saveUser(user);
		System.out.println(user);
		Assert.assertNotNull(user);
		}
	@DisplayName("アカウント登録失敗テスト") /* [fatal] bypassed if codes :( */
	@Test
	@Disabled //comment out this
	public void AccountRegistrationFailedTest() throws Exception {
		User userExists = userService.findUserByUserName(user.getUserName());
		user.setLastName("ADMIN");
		user.setFirstName("SYSTEM");
		user.setUserName("541");
		user.setPassword("thisPassword");
		if (userExists != null) {
			System.out.println("null passed : " + user);
			return;
		}else {
			userService.saveUser(user);
		}

		Assert.assertNotNull(user);

	}
}
