package core.Controller.Profile;

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

import core.Model.Profile;
import core.Model.User;
import core.Service.ProfileService;
import core.Service.UserService;

@Controller
@Transactional
public class MyProfile {
	@Autowired
    private UserService userService;
	@Autowired
	private ProfileService profileService;

	@GetMapping(value="/pages/MyProfile")
    public ModelAndView MyProfileCard(Profile profile, User identifier) {
		ModelAndView mav = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		//identifierはプロフィール情報と紐づける為にアカウント情報を一部取得する
		identifier = userService.findUserByUserName(auth.getName());
		//profileは自分のプロフィール情報を取得する(作成していない場合は登録する)
		profile = profileService.findUserByUserName(auth.getName());
		//profileオブジェクト作成
		if (profile == null) {
			mav.addObject("FirstName",identifier.getFirstName());
	    	mav.addObject("LastName",identifier.getLastName());
	    	mav.addObject("fullName",identifier.getLastName() + identifier.getFirstName());
	    	mav.addObject("emsID",identifier.getUserName());
			mav.setViewName("pages/MyProfile");
			return mav;
		}else {
			mav.addObject("profile", profile);
			mav.addObject("FirstName",identifier.getFirstName());
	    	mav.addObject("LastName",identifier.getLastName());
	    	mav.addObject("fullName",identifier.getLastName() + identifier.getFirstName());
	    	mav.addObject("emsID",identifier.getUserName());
			mav.setViewName("pages/MyProfile");
			}
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
				profile.setFirstName(finder.getFirstName());
				profile.setLastName(finder.getLastName());
				profileService.save(profile);
				mav.addObject("profile", profile);
				mav.setViewName("redirect:/pages/MyProfile");
				}
		return mav;
		}
	}
