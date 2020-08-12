package core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserTable {
	
	@GetMapping(value="/pages/UserList")
	public ModelAndView UserList() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("pages/UserList");
		return modelAndView;
		}

}
