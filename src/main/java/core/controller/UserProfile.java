package core.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import core.model.User;
import core.repository.UserRepository;
import core.service.UserService;

@Controller
@RestController
public class UserProfile {
	@Autowired
    private UserRepository userRepository;
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
    //登録ユーザ一覧をリスト表示する
    @GetMapping("/127.0.0.1")
    public List<User> ID(Model model) {
        List<User> list = userRepository.findAll();
        return list;
    }
    //サービスへ登録しているユーザーを一覧表示する
    @GetMapping(value="/pages/Users")
    public ModelAndView Users() {
    	ModelAndView modelAndView = new ModelAndView();
    	modelAndView.setViewName("pages/Users");	
    	return modelAndView;
    }
}

/* 
 * システムロケーション
 * Registration=>Login=>Home=>{Search}|{[Profile]}
 */