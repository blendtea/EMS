package core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import core.model.User;
import core.service.UserService;

@Controller
public class Dashboard {

	@Autowired
    private UserService userService;
    //Dashboardへマッピングする
    @GetMapping(value="/admin/Dashboard")
    public ModelAndView Main(Model model){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        //Model String()
        modelAndView.addObject("Welcome","ようこそ " + user.getLastName() + " " + user.getFirstName() + "さん");
        modelAndView.addObject("Version","1.1.0");
        modelAndView.addObject("Title","Welcome to EMS PROJECT");
        modelAndView.addObject("GetStarted","In this service, you can know the profile of the user who registered in this service and the project to which they belong.");
        modelAndView.addObject("updated","last updated : 5 Aug");
        modelAndView.setViewName("admin/Dashboard");
        return modelAndView;
    }
}
