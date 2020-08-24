package core.Controller.Profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import core.Model.Profile;
import core.Model.User;
import core.Service.ProfileService;
import core.Service.UserService;

@Controller
public class MyProfile {
	@Autowired
    private UserService userService;
	@Autowired
	private ProfileService profileService;

	@GetMapping(value="/pages/MyProfile")
    public ModelAndView MyProfileCard(Profile profile, User identifier) {
		ModelAndView mav = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		identifier = userService.findUserByUserName(auth.getName());
		mav.addObject("FirstName",identifier.getFirstName());
    	mav.addObject("LastName",identifier.getLastName());
    	mav.addObject("fullName",identifier.getLastName() + identifier.getFirstName());
    	mav.addObject("employees_ID",identifier.getUserName());
		mav.setViewName("pages/MyProfile");
		return mav;
	}
	@GetMapping(value="/pages/iFunBox")
	public ModelAndView TestField(Profile profile, User identifier) {
		ModelAndView mav = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		identifier = userService.findUserByUserName(auth.getName());
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
				profile.setUserName(finder.getUserName());
				System.out.println(" ");System.out.println(" ");System.out.println(" ");System.out.println(" ");
				System.out.println("finder CPU : " + profile.toString());
				System.out.println(" ");System.out.println(" ");System.out.println(" ");System.out.println(" ");
				profileService.save(profile);
				mav.setViewName("redirect:/pages/iFunBox");
				}
		return mav;
		}
	}
