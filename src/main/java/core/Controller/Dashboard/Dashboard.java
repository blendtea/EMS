package core.Controller.Dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import core.Model.Profile;
import core.Model.User;
import core.Service.ProfileService;
import core.Service.UserService;

@Controller
public class Dashboard {

	@Autowired
    private UserService userService;
	@Autowired
	private ProfileService profileService;
    //Dashboardへマッピングする
    @GetMapping(value="/pages/Dashboard")
    public ModelAndView Main(Model model){
        ModelAndView mav = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        Profile profile = profileService.findUserByUserName(auth.getName());
        if (profile == null) {
        	mav.addObject("WrnProfile","プロフィールが未登録です");
        }
        if(profile != null) {
        	mav.addObject("FndProfile","プロフィールは登録済みです");
        }
        //Model String()
        mav.addObject("Welcome","ようこそ " + user.getLastName() + " " + user.getFirstName() + "さん");
        mav.addObject("Version","1.2.6");
        mav.addObject("BINGO", "BINGO");
        mav.addObject("VersionBINGO", "1.0(未実装)");
        mav.addObject("Title","Welcome to EMS PROJECT");
        mav.addObject("updated","last updated : 26 Aug");
        mav.setViewName("pages/Dashboard");
        return mav;
    }

    @GetMapping(value = "/pages/BINGO")
    public ModelAndView BINGO(ModelAndView mav) {
    	mav.setViewName("/pages/board");
    	return mav;
    }
}
