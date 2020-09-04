package core.Service;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
public class BeginningTheTerm {

	//mockMvc Mock object for handling Http request/response without deploying to Tomcat server
	@Autowired
	private MockMvc mockMvc;
	@Test
	/*Specify view in get request and judge success or failure of http status
	 Display request/response with andDo(print())
	 ==============================================
	 Be execute (perform)
	Get request from "/login" (get)
	Display results (andDo(print))
	(for Result)Expect (andExpect)
	is HTTP status is 200 (status().isOK())
	 */
		void HTTPisResponding() throws Exception {
    this.mockMvc.perform(get("/login")).andDo(print())
        .andExpect(status().isOk());
    }
}

