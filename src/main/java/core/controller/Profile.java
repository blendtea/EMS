package core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Profile {
    //メイン画面からプロフィール編集画面へ遷移するためのページマッピング
    @GetMapping(value="/admin/G_003")
    public ModelAndView G_003() {
    	ModelAndView modelAndView = new ModelAndView();
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
