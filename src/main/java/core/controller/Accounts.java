package core.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import core.model.User;
import core.service.UserService;

@Controller
public class Accounts {

    @Autowired
    private UserService userService;
    //ログイン画面へ遷移する
    @GetMapping(value={"/", "/login"})
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }
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
    //@Validによるバリデーションチェックを含める
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
            modelAndView.addObject("successMessage", "登録が完了しました。ログインしてください");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("registration");

        }
        return modelAndView;
    }
    //ログイン認証成功したアカウントのみアクセスできるページマッピング
    @GetMapping(value="/admin/home")
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getUserName() + "/" + user.getLastName() + " " + user.getName());
//      modelAndView.addObject("adminMessage","ここはサービス利用登録者、管理者のみ閲覧可能です");
        modelAndView.setViewName("admin/home");
        return modelAndView;
    }
    //メイン画面からプロフィール編集画面へ遷移するためのページマッピング
    @GetMapping(value="/admin/G_003")
    public ModelAndView G_003() {
    	ModelAndView modelAndView = new ModelAndView();
    	modelAndView.setViewName("admin/G_003");
    	return modelAndView;
    }
    //プロフィール画面登録のページマッピング
    @PostMapping(value = "/profile")
    public ModelAndView view() {
    	ModelAndView modelAndView = new ModelAndView();
		return modelAndView;
		}
    
}
