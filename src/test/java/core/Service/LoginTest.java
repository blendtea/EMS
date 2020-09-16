package core.Service;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import core.Model.User;

@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoginTest {


	@SuppressWarnings("unused")
	@Autowired
	private UserService userService;

	@SuppressWarnings("unused")
	@Autowired
	private MyUserDetailsService service;

	@Autowired
	private MockMvc mockMvc;

	@Before
	void before(){
		User user = new User();
	}



	@Test
	@DisplayName("ログインテスト")
	public void Logintest() throws Exception {
		this.mockMvc.perform(
				get("/")
				.flashAttr("user_name", "")
				.flashAttr("password", ""))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(view().name("startup"));
	}
	@Test
	@DisplayName("ログインテスト")
	public void Logintest2() throws Exception {
		this.mockMvc.perform(
				get("/")
				.flashAttr("user_name", "000")
				.flashAttr("password", "00000"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(view().name("startup"));
	}

//	@Test
//	@DisplayName("ログインテスト")
//	public void Logintest() throws Exception {
//		this.mockMvc.perform(
//				formLogin("/"))
//		.andDo(print())
//		.andExpect(status().isOk())
//		.andExpect(view().name("startup"));
//	}
}