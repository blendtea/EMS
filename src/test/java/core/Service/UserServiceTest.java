package core.Service;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import core.Model.User;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
class UserServiceTest {
	@Autowired
	private UserService userService;

	MockMvc mvc;

	@DisplayName("アカウント登録成功テスト")
	@Test
	@Disabled //comment out this
	void AccountRegistrationSuccessedTest() throws Exception {
		User user = new User();
		user.setLastName("ADMIN");
		user.setFirstName("TESTER");
		user.setUserName("000");
		user.setPassword("thisPassword");
		userService.saveUser(user);
		System.out.println(user);
		Assert.assertNotNull(user);
		}

	@DisplayName("アカウント登録失敗テスト") /* [fatal] bypassed if codes :( */
	@Test
	void AccountRegistrationFailedTest() throws Exception {
		User finder = new User();
		String LastName = null;
			finder.setLastName(LastName);
		String FirstName = null;
			finder.setFirstName(FirstName);
		String UserName = null;
			finder.setUserName(UserName);
		String Password = null;
			finder.setPassword(Password);

		User userExists = userService.findUserByUserName(finder.getUserName());
		System.out.println("call : " + userExists);
		assertAll(
				"Test for Account Registration",
				() -> assertEquals(null, finder.getLastName()),
				() -> assertEquals(null, finder.getFirstName()),
				() -> assertEquals(userExists, finder.getUserName()),
				() -> assertEquals(null, finder.getPassword()));
		}
	}
