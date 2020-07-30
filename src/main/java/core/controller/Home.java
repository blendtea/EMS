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
public class Home {
	
	@Autowired
    private UserService userService;
    //ログイン認証成功したアカウントのみアクセスできるページマッピング
    @GetMapping(value="/admin/home")
    public ModelAndView home(Model model){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getUserName() + "/" + user.getLastName() + " " + user.getFirstName());
        modelAndView.addObject("info","デバッグ中・・・");
        modelAndView.setViewName("admin/home");
        return modelAndView;
    }
}
