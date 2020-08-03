package core.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import core.model.User;
import core.repository.UserRepository;

@Controller
@RestController
public class Profile {
	@Autowired
    private UserRepository userRepository;
	//メイン画面からプロフィールへ遷移する
    @GetMapping(value="/admin/Edit")
    public ModelAndView Edit() {
    	ModelAndView modelAndView = new ModelAndView();
    	modelAndView.setViewName("admin/Edit");
    	return modelAndView;
    }
    //登録ユーザ一覧をHomeにリスト表示する
    @GetMapping("/admin/search")
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
}

/* 
 * システムロケーション
 * Registration=>Login=>Home=>{Search}|{[Profile]}
 */