package core.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import core.model.User;
import core.repository.UserRepository;
import core.service.UserService;

@Controller
@RestController
public class Profile {
	@Autowired
    private UserRepository userRepository;
	@Autowired
    private UserService userService;
	//Home画面からプロフィール編集へ遷移する
	@GetMapping(value="/admin/Edit")
    public ModelAndView Edit() {
    	ModelAndView modelAndView = new ModelAndView();
    	User user = new User();
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User finder = userService.findUserByUserName(auth.getName());
    	modelAndView.addObject("userName",finder.getUserName());
        modelAndView.addObject("Name",finder.getLastName() + finder.getFirstName());
        modelAndView.addObject("user", user);
    	modelAndView.setViewName("admin/Edit");
    	return modelAndView;
    }
	//Home画面からプロフィール検索画面へ遷移する
	@GetMapping(value="/admin/View")
    public ModelAndView View() {
    	ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
    	modelAndView.addObject("userName",user.getUserName());
        modelAndView.addObject("fullName",user.getLastName() + " " + user.getFirstName());
    	modelAndView.setViewName("admin/View");
    	return modelAndView;
    }
    //登録ユーザ一覧をリスト表示する
    @GetMapping("/127.0.0.1")
    public List<User> ID(Model model) {
        List<User> list = userRepository.findAll();
        return list;
    }
    //サービスへ登録しているユーザーを一覧表示する
    @GetMapping(value="/admin/Users")
    public ModelAndView Users() {
    	ModelAndView modelAndView = new ModelAndView();
    	modelAndView.setViewName("admin/Users");	
    	return modelAndView;
    }
    
    @PostMapping(value = "/admin/Edit")
    public ModelAndView Profile(){ 
    	ModelAndView modelAndView = new ModelAndView();
    	modelAndView.setViewName("admin/Edit");        
        return modelAndView;
    }
}

/* 
 * システムロケーション
 * Registration=>Login=>Home=>{Search}|{[Profile]}
 */