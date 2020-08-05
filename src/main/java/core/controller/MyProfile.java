package core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import core.model.User;
import core.service.UserService;

@Controller
public class MyProfile {
	@Autowired
    private UserService userService;
	@GetMapping(value="/admin/MyProfile")
    public ModelAndView Edit() {
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
        modelAndView.addObject("user", user);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User finder = userService.findUserByUserName(auth.getName());
    	modelAndView.setViewName("admin/MyProfile");
    	modelAndView.addObject("fullName",finder.getLastName() + finder.getFirstName());
    	modelAndView.addObject("employees_ID","社員番号"+"#"+finder.getUserName());
    	return modelAndView;
	}
//	@SuppressWarnings("unused")
//	@PostMapping(value = "/admin/MyProfile")
//    public ModelAndView addNewUser(@Valid User user, BindingResult bindingResult) {
//        ModelAndView modelAndView = new ModelAndView();
//		User finder = userService.findUserByUserName(user.getUserName());
//            userService.saveUser(user);
//            modelAndView.addObject("successMessage", "登録が完了しました。");
//            modelAndView.addObject("user", new User());
//            modelAndView.setViewName("admin/MyProfile");
//
//        return modelAndView;
//    }
}
