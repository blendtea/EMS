package core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
		ModelAndView mav = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = new User();
		user = userService.findUserByUserName(auth.getName());
		//Object Mapping
		mav.addObject("user", user);
    	mav.setViewName("admin/MyProfile");
    	//Object data.String
    	mav.addObject("FirstName",user.getFirstName());
    	mav.addObject("LastName",user.getLastName());
    	mav.addObject("fullName",user.getLastName() + user.getFirstName());
//    	mav.addObject("employees_ID","社員番号"+"#"+ user.getUserName());
    	mav.addObject("employees_ID",user.getUserName());
    	return mav;
	}
//	@PostMapping(value="/admin/MyProfile")
//	public ModelAndView update(@ModelAttribute User user, BindingResult bindingresult) {
//		ModelAndView mav = new ModelAndView();
//		if (bindingresult.hasErrors()) {
//			mav.setViewName("admin/Dashboard");
//		}else {
//			userService.save(user);
//		mav.setViewName("redirect:/admin/MyProfile");
//	}
//		return mav;
//	}

	 @PostMapping(value="/admin/MyProfile/Edit")
	    public ModelAndView Edit(@ModelAttribute User user, BindingResult result) {
		 ModelAndView mav = new ModelAndView();
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		 if (result.hasErrors()) {
			 mav.setViewName("admin/Dashboard");
	            }else{
	            	userService.save(user);
	            	mav.addObject("user", new User());
	            	mav.setViewName("admin/MyProfile/" + auth.getName());
	            }
	        return mav;
	        //("redirect:/user/%d",
	        		//userUpdateRequest.getId());
	    }
	}