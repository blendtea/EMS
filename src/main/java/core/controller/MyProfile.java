package core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
    	modelAndView.addObject("employees_ID","社員番号"+"#"+finder.getUserName());
    	return modelAndView;
	}
//	@Transactional
//	@PostMapping("/admin/MyProfile")
//	  public ModelAndView greetingSubmit(@ModelAttribute User user) {
//		ModelAndView modelAndView = new ModelAndView();
//	    modelAndView.addObject("user", user);
//	    return modelAndView;
//	  }
	@PostMapping("/admin/MyProfile/" + "{userName}")
    public ModelAndView createNewProfile(User user) {   
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("user", userService.findAll());
		userService.saves(user);
		modelAndView.addObject("user", new User());
		modelAndView.setViewName("admin/MyProfile");
        return modelAndView;
	}
}