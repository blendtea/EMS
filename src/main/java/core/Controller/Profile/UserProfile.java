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
    	ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
    	modelAndView.addObject("userName",user.getUserName());
        modelAndView.addObject("fullName",user.getLastName() + " " + user.getFirstName());
    	modelAndView.setViewName("pages/View");
    	return modelAndView;
    }
}

/*
 * システムロケーション
 * Registration=>Login=>Home=>{Search}|{[Profile]}
 */