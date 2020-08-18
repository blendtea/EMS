package core.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	@PostMapping(value="/pages/MyProfile/edit")
	@PreAuthorize("hasRole('USER')")
	public ModelAndView findByUserName(@RequestParam("hobby") String hobby, @ModelAttribute User user,String userName, BindingResult result) {
		ModelAndView mav = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User finder = userService.findUserByUserName(auth.getName());
		if (result.hasErrors()) {
			mav.setViewName("redirect:/pages/UserList");
		}
		if (finder == null) {
			System.out.println("User not found !");
			}else {
				System.out.println("Processed CPU : " + finder);
	            userService.save(user);
	            mav.addObject("user", auth.getName());
	            mav.setViewName("redirect:/pages/profile");
	        }
		return mav;
		}
	}
