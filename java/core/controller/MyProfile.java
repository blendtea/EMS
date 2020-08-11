package core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import core.model.User;
import core.service.UserService;

@Controller
public class MyProfile {
	@Autowired
    private UserService userService;
	@GetMapping(value="/admin/MyProfile")
    public ModelAndView Edit() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User finder = userService.findUserByUserName(auth.getName());
		ModelAndView modelAndView = new ModelAndView();
		//Object Mapping
    	modelAndView.setViewName("admin/MyProfile");
    	modelAndView.addObject("user",new User());
    	//Object data.String
    	modelAndView.addObject("FirstName",finder.getFirstName());
    	modelAndView.addObject("LastName",finder.getLastName());
    	modelAndView.addObject("fullName",finder.getLastName() + finder.getFirstName());
//    	modelAndView.addObject("employees_ID","社員番号"+"#"+finder.getUserName());
    	modelAndView.addObject("employees_ID",finder.getUserName());
    	return modelAndView;
	}
	@PostMapping("/admin/MyProfile/edit")
	public ModelAndView update(@PathVariable String userName, @ModelAttribute User user) {
		ModelAndView mav = new ModelAndView();
		if(userName == null) {
		userService.findUserByUserName(userName);
		mav.addObject("admin/Dashboard");
			return mav;
		}
		userService.save(user);
		return mav;
	}
}