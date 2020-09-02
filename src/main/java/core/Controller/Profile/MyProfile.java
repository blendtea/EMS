package core.Controller.Profile;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    public ModelAndView MyProfileCard(@ModelAttribute Profile profile, User identifier, Authentication auth, ModelAndView mav) {
		auth = SecurityContextHolder.getContext().getAuthentication();
		//identifierはプロフィール情報と紐づける為にアカウント情報を一部取得する
		identifier = userService.findUserByUserName(auth.getName());
		//profileは自分のプロフィール情報を取得する(作成していない場合は登録する)
		profile = profileService.findUserByUserName(auth.getName());

		final Map<String, String> SELECT_SEX =
	            Collections.unmodifiableMap(new LinkedHashMap<String, String>() {
	                {
	                	put("選択してください", "" );
	                    put("男性", "男性");
	                    put("女性", "女性");
	                }
	            });
		//profileオブジェクト作成
		if (profile == null) {
			System.out.println("processed : 1");
			mav.addObject("FirstName",identifier.getFirstName());
	    	mav.addObject("LastName",identifier.getLastName());
	    	mav.addObject("fullName",identifier.getLastName() + identifier.getFirstName());
	    	mav.addObject("emsID",identifier.getUserName());
	    	mav.addObject("SELECT_SEX", SELECT_SEX);
			mav.setViewName("pages/MyProfile");
			return mav;
		}else {
			System.out.println("procesed : 2");
			mav.addObject("profile", profile);
			mav.addObject("FirstName",identifier.getFirstName());
	    	mav.addObject("LastName",identifier.getLastName());
	    	mav.addObject("fullName",identifier.getLastName() + identifier.getFirstName());
	    	mav.addObject("emsID",identifier.getUserName());
	    	mav.addObject("SELECT_SEX", SELECT_SEX);
	    	mav.setViewName("pages/MyProfile");
			}
			return mav;
		}

	@PostMapping(value="/pages/MyProfile")
	@PreAuthorize("hasRole('USER')")
	public ModelAndView findByUserName(@Validated Profile profile, BindingResult result, User finder, Authentication auth, ModelAndView mav) {
		final Map<String, String> SELECT_SEX =
	            Collections.unmodifiableMap(new LinkedHashMap<String, String>() {
	                {
	                	put("選択してください", "" );
	                    put("男性", "男性");
	                    put("女性", "女性");
	                }
	            });
		auth = SecurityContextHolder.getContext().getAuthentication();
		finder = userService.findUserByUserName(auth.getName());
		if (finder == null) {
			System.out.println("User not found !");
			System.out.println("passed : 1");
		}
		if (result.hasErrors()) {
			profile.setId(finder.getId());
			profile.setUserName(finder.getUserName());
			profile.setFirstName(finder.getFirstName());
			profile.setLastName(finder.getLastName());
			mav.addObject("emsID", finder.getUserName());
			mav.addObject("fullName", profile.getLastName() + profile.getFirstName());
			mav.addObject("FirstName", finder.getFirstName());
	    	mav.addObject("LastName", finder.getLastName());
	    	mav.addObject("SELECT_SEX", SELECT_SEX);

			System.out.println("passed : 2");
			}else {
				System.out.println("passed : 3");
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
