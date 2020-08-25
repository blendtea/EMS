package core.Controller.Dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import core.Model.User;
import core.Service.UserService;

@Controller
public class Dashboard {

	@Autowired
    private UserService userService;
    //Dashboardへマッピングする
    @GetMapping(value="/pages/Dashboard")
    public ModelAndView Main(Model model){
        ModelAndView mav = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        //Model String()
        mav.addObject("Welcome","ようこそ " + user.getLastName() + " " + user.getFirstName() + "さん");
        mav.addObject("Version","1.2.2");
        mav.addObject("Title","Welcome to EMS PROJECT");
        mav.addObject("GetStarted","Info board");
        mav.addObject("updated","last updated : 25 Aug");
        mav.setViewName("pages/Dashboard");
        return mav;
    }
}
