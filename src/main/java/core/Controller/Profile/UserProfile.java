package core.Controller.Profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import core.Model.Profile;
import core.Service.ProfileService;

@Controller
public class UserProfile {
	@Autowired
    private ProfileService profileService;
	@GetMapping(value="/pages/profile/id={userName}")
    public ModelAndView View(@ModelAttribute Profile profile, @PathVariable String userName) {
    	ModelAndView mav = new ModelAndView();
    	profile = profileService.findUserByUserName(userName);
    	mav.addObject("emsID", userName);
    	mav.addObject("fullName", profile.getLastName() + " " + profile.getFirstName());
    	mav.addObject("profile", profile);
    	System.out.println(profile);
    	mav.setViewName("pages/UserProfile");
    	return mav;
    }
}
