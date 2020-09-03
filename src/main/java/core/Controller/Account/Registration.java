package core.Controller.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import core.Model.User;
import core.Service.UserService;

@Controller
public class Registration {

	@Autowired
    private UserService userService;

	//登録画面へ遷移する
    @GetMapping(value="/registration")
    public ModelAndView registration(@ModelAttribute User user, ModelAndView mav){
        mav.addObject("user", user);
        mav.setViewName("registration");
        return mav;
    }
    //登録情報を送信する
    @PostMapping(value = "/registration")
    public ModelAndView createNewUser(@Validated @ModelAttribute User user, BindingResult result, ModelAndView mav) {
        User userExists = userService.findUserByUserName(user.getUserName());
        if (userExists != null) {
            result
                    .rejectValue("userName", "error.user",
                            "*登録済み");
        }
        if (result.hasErrors()) {
            mav.setViewName("registration");
//            mav.addObject("failedMessage","登録に失敗しました");
//            mav.addObject("tryMessage","再試行してください");
        } else {
            userService.saveUser(user);
    //アカウント登録成功時に通知する
            mav.addObject("successMessage", "登録が完了しました");
            mav.addObject("user", new User());
            mav.setViewName("startup");
        }
        return mav;
    }
}
