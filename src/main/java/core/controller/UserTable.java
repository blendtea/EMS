package core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserTable {
	
	@GetMapping(value="/admin/UserList")
	public ModelAndView UserList() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/UserList");
		return modelAndView;
		}

}
