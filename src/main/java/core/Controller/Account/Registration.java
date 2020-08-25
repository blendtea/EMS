package core.Controller.Account;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
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
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("startup");
        return modelAndView;
    }
    //登録情報を送信する
    @PostMapping(value = "/registration")
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByUserName(user.getUserName());
        if (userExists != null) {
            bindingResult
                    .rejectValue("userName", "error.user",
                            "*登録済み");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("startup");
            modelAndView.addObject("failedMessage","登録に失敗しました");
            modelAndView.addObject("tryMessage","再試行してください");
        } else {
            userService.saveUser(user);
    //アカウント登録成功時に通知する
            modelAndView.addObject("successMessage", "登録が完了しました");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("startup");
        }
        return modelAndView;
    }
}
