package core.controller;

<<<<<<< HEAD
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import core.model.User;
import core.repository.UserRepository;

@RestController
public class Profile {
	// create userlist for datatable at /admin/home
    @Autowired
    private UserRepository userRepository;
    @GetMapping("/getuserlist")
    public List<User> ID(Model model) {
        List<User> list = userRepository.findAll();
        return list;
    }
}
=======
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Profile {
	
	//メイン画面からプロフィールへ遷移する
    @GetMapping(value="/admin/Profile")
    public ModelAndView Edit() {
    	ModelAndView modelAndView = new ModelAndView();
    	modelAndView.setViewName("admin/Profile");
    	return modelAndView;
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
>>>>>>> master
