package core.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import core.model.Profile;
import core.model.User;
import core.service.ProfileService;
import core.service.UserService;
@Transactional
@Controller
public class MyProfile {
	@Autowired
    private UserService userService;
	private ProfileService profileService;

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
	@GetMapping(value="/pages/iFunBox")
	public ModelAndView TestField(Profile profile, User identifier) {
		ModelAndView mav = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		identifier = userService.findUserByUserName(auth.getName());
		mav.addObject("profile", profile);
		mav.addObject("FirstName",identifier.getFirstName());
    	mav.addObject("LastName",identifier.getLastName());
    	mav.addObject("fullName",identifier.getLastName() + identifier.getFirstName());
    	mav.addObject("employees_ID",identifier.getUserName());
		mav.setViewName("pages/MyProfile");
		return mav;
	}

	@PostMapping(value="/pages/MyProfile")
	@PreAuthorize("hasRole('USER')")
	public ModelAndView findByUserName(Profile profile, User finder, BindingResult result) {
		ModelAndView mav = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		finder = userService.findUserByUserName(auth.getName());

		if (result.hasErrors()) {
			mav.setViewName("redirect:/pages/UserList");
		}
		if (finder == null) {
			System.out.println("User not found !");
			}else {
				profile.setId(finder.getId());
//				profileService.save(profile);
				System.out.println(" ");System.out.println(" ");System.out.println(" ");System.out.println(" ");
				System.out.println("finder CPU : " + profile.toString());
				System.out.println(" ");System.out.println(" ");System.out.println(" ");System.out.println(" ");
	            profileService.save(profile);
				mav.setViewName("redirect:/pages/iFunBox");
	        }
		return mav;
		}



//	@PostMapping(value="/pages/MyProfile")
//	@PreAuthorize("hasRole('USER')")
//	public ModelAndView findByUserName(@ModelAttribute User user, BindingResult result) {
//		ModelAndView mav = new ModelAndView();
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		User finder = userService.findUserByUserName(auth.getName());
//		if (result.hasErrors()) {
//			mav.setViewName("redirect:/pages/UserList");
//		}
//		if (finder == null) {
//			System.out.println("User not found !");
//			}else {
//				user.setId(finder.getId());
//				user.setUserName(finder.getUserName());
//				user.setPassword(finder.getPassword());
//				user.setFirstName(finder.getFirstName());
//				user.setLastName(finder.getLastName());
//	            userService.save(user);
////	            mav.addObject("user", auth.getName());
//	            mav.setViewName("redirect:/pages/MyProfile");
//	        }
//		return mav;
//		}
	}