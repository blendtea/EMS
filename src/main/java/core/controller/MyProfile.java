package core.controller;

import javax.transaction.Transactional;

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
@Transactional
@Controller
public class MyProfile {
	@Autowired
    private UserService userService;

	@GetMapping(value="/pages/MyProfile")
    public ModelAndView MyProfileCard(User user) {
		ModelAndView mav = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		user = userService.findUserByUserName(auth.getName());
		//Object Mapping
		mav.addObject("user", user);
    	//Object data.String
    	mav.addObject("FirstName",user.getFirstName());
    	mav.addObject("LastName",user.getLastName());
    	mav.addObject("fullName",user.getLastName() + user.getFirstName());
//    	mav.addObject("employees_ID","社員番号"+"#"+ user.getUserName());
    	mav.addObject("employees_ID",user.getUserName());
    	mav.setViewName("pages/MyProfile");
    	return mav;
	}
	@PostMapping(value="/pages/MyProfile")
	public String update(@PathVariable Long id, @ModelAttribute User user) {
		user.setId(id);
		userService.save(user);
		return "redirect:/pages/MyProfile";
	}
//	 @PostMapping(value="/pages/MyProfile")
//	    public ModelAndView Edit(User finder, BindingResult result) {
//		 ModelAndView mav = new ModelAndView();
//		 if (result.hasErrors()) {
//			 mav.setViewName("pages/Dashboard");
//			 }else{
//				 userService.save(finder);
//				 mav.addObject("finder", new User());
//				 mav.setViewName("pages/MyProfile");
//				 }
//		 return mav;
//		 }
	 }
