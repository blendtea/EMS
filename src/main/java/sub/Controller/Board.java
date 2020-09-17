package sub.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import core.Model.User;
import core.Service.UserService;

@Controller
public class Board {

	@Autowired
    private UserService userService;

	@GetMapping(value = "/pages/BINGO")
    public ModelAndView BINGO(ModelAndView mav, User identifier, Authentication auth) {
//		auth = SecurityContextHolder.getContext().getAuthentication();
//		identifier = userService.findUserByUserName(auth.getName());
//		mav.addObject("userName", identifier.getLastName() + identifier.getFirstName());

    	mav.setViewName("/pages/board");
    	return mav;
    }

	@GetMapping(value = "/pages/Dashboarder")
	public ModelAndView retu(ModelAndView mav) {
		mav.setViewName("/pages/Dashboard");
		return mav;
	}
}
