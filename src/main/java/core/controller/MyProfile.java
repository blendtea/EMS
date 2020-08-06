package core.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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
		User user = new User();
        //Object Mapping
		modelAndView.addObject("user", user);
    	modelAndView.setViewName("admin/MyProfile");
    	//Object data.String
    	modelAndView.addObject("FirstName",finder.getFirstName());
    	modelAndView.addObject("LastName",finder.getLastName());
    	modelAndView.addObject("fullName",finder.getLastName() + finder.getFirstName());
    	modelAndView.addObject("employees_ID","社員番号"+"#"+finder.getUserName());
    	return modelAndView;
	}
	
	@PostMapping(value="/admin/MyProfile", params="new")
	public ModelAndView createNewProfile(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByUserName(user.getUserName());
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("admin/Dashboard");
        } else {
            userService.saveUser(user);
        
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("admin/MyProfile");   
        }
        return modelAndView;
	}
}
