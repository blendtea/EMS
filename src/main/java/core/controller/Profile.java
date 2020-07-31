package core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import core.model.ProfileData;
import core.service.UserService;

@Controller
public class Profile {
	@Autowired
    private UserService userService;
    //メイン画面からプロフィール編集画面へ遷移するためのページマッピング
	//プロフィール編集画面にprofiledataから取得したデータを送る
    @GetMapping(value="/admin/henshuu")
    public ModelAndView henshuu() {
    	ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        ProfileData profiledata = userService.findProfileByProfile(auth.getName());
        modelAndView.addObject("id",profiledata.getId());
    	modelAndView.addObject("name",profiledata.getFullName());
    	modelAndView.addObject("sex",profiledata.getSex());
    	modelAndView.addObject("assigned",profiledata.getAssigned());
    	modelAndView.addObject("birth",profiledata.getBirth());
    	modelAndView.addObject("school",profiledata.getSchool());
    	modelAndView.addObject("hobby",profiledata.getHobby());
    	modelAndView.addObject("msg",profiledata.getShortMessage());
    	modelAndView.setViewName("admin/henshuu");
    	return modelAndView;
    }
    @GetMapping(value="/admin/profile")
    public ModelAndView profile() {
    	ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        ProfileData profiledata = userService.findProfileByProfile(auth.getName());
        modelAndView.addObject("id",profiledata.getId());
    	modelAndView.addObject("fullName",profiledata.getFullName());
    	modelAndView.addObject("sex",profiledata.getSex());
    	modelAndView.addObject("assigned",profiledata.getAssigned());
    	modelAndView.addObject("birth",profiledata.getBirth());
    	modelAndView.addObject("school",profiledata.getSchool());
    	modelAndView.addObject("hobby",profiledata.getHobby());
    	modelAndView.addObject("msg",profiledata.getShortMessage());
    	modelAndView.setViewName("admin/profile");
    	return modelAndView;
    }



    @GetMapping(value="/admin/Users")
    public ModelAndView Users() {
    	ModelAndView modelAndView = new ModelAndView();
    	modelAndView.setViewName("admin/Users");
    	return modelAndView;
    }
}
