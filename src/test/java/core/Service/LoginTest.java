package core.Service;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.github.springtestdbunit.annotation.DatabaseSetup;
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
class LoginTest {

	@Autowired
	private UserService userService;

	@Autowired
	private MyUserDetailsService service;

	@Autowired
	private MockMvc mockMvc;

	@Before
	void TestService(String userName) {

	}

	@Test
	@DatabaseSetup(value = "")
	void user_nametest() throws Exception {
		this.mockMvc.perform(
				formLogin("/login")
				.user("000")
				.password("shun0425"))
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/pages/Dashboard"));
	}
}
