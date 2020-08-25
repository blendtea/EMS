package core.Controller.Profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import core.Model.User;
import core.Service.UserService;

@Controller
public class UserProfile {
	@Autowired
    private UserService userService;
	@GetMapping(value="/pages/View")
    public ModelAndView View() {
    	ModelAndView mav = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
    	mav.addObject("userName",user.getUserName());
        mav.addObject("fullName",user.getLastName() + " " + user.getFirstName());
    	mav.setViewName("pages/UserProfile");
    	return mav;
    }
}
