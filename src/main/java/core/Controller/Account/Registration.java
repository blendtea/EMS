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
        modelAndView.setViewName("registration");
        return modelAndView;
    }
    //登録情報を送信する
    @PostMapping(value = "/registration")
    //@Validによるバリデーションチェックを行う
    //ModelAndViewクラス(Model)からコントローラが取得した値をView側へ値を渡す
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
    //UserRepository(リポジトリ=データ格納場所)からModelクラスでuserNameの値を取得する
        User userExists = userService.findUserByUserName(user.getUserName());
        if (userExists != null) {
            bindingResult
                    .rejectValue("userName", "error.user",
                            "*既に社員IDは登録されています");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
        } else {
            userService.saveUser(user);
    //アカウント登録成功時に通知する
            modelAndView.addObject("successMessage", "登録が完了しました。ログインしてください");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("registration");
        }
        return modelAndView;
    }
}

/*
 * システムロケーション
 * [Registration]=>Login=>Home=>{Search}|{Profile}
 */
