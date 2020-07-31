package core.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import core.service.UserService;

import core.model.User;

@Controller
public class Profile {
	@Autowired
    private UserService userService;
    //メイン画面からプロフィール編集画面へ遷移するためのページマッピング
    @GetMapping(value="/admin/G_003")
    public ModelAndView G_003() {
    	ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
    	modelAndView.addObject("userName",user.getUserName());
        modelAndView.addObject("fullName",user.getLastName() + " " + user.getFirstName());
    	modelAndView.setViewName("admin/G_003");
    	return modelAndView;
    }

    @GetMapping(value="/admin/Users")
    public ModelAndView Users() {
    	ModelAndView modelAndView = new ModelAndView();
    	modelAndView.setViewName("admin/Users");
    	return modelAndView;
    }
}
