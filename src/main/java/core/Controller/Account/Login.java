package core.Controller.Account;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import core.Model.User;

@Controller
public class Login {
    @GetMapping(value={"/", "/login"})
    public ModelAndView startup(@ModelAttribute User user, ModelAndView mav){

    	/* 以下モデルクラスのインポート */
    	/* ["Model/User"]を引数に入れ、登録フォーム移行時(遷移無し)に必要な登録情報をビュー側に返却する */

    	mav.addObject("user", user);
    	/* ModelAndViewクラスよりコントローラから["Model/User"]をビューへ渡す */

    	mav.setViewName("startup");
        return mav;
    }
}
