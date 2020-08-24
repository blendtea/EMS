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
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        //Model String()
        modelAndView.addObject("Welcome","ようこそ " + user.getLastName() + " " + user.getFirstName() + "さん");
        modelAndView.addObject("Version","1.2.0");
        modelAndView.addObject("Title","Welcome to EMS PROJECT");
        modelAndView.addObject("GetStarted","Info board");
        modelAndView.addObject("updated","last updated : 24 Aug");
        modelAndView.setViewName("pages/Dashboard");
        return modelAndView;
    }
}
