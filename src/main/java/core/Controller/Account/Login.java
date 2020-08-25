package core.Controller.Account;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import core.Model.User;

@Controller
public class Login {
    @GetMapping(value={"/", "/login"})
    public ModelAndView login(){
        ModelAndView mav = new ModelAndView();
        User user = new User();
        mav.addObject("user", user);
        mav.setViewName("startup");
        return mav;
    }
}
