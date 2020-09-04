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

    @GetMapping(value="/registration")
    public ModelAndView registration(ModelAndView mav){
        mav.setViewName("registration");
        return mav;
    }
    //アカウント登録マッピング
    @PostMapping(value = "/registration")
    public ModelAndView createNewUser(@Validated @ModelAttribute User user, BindingResult result, ModelAndView mav) {

    	/* 以下モデルクラス、バリデーションのインポート */
    	/* 登録フォーム送信時にモデルクラス内に設定されているバリデーションを通過しBindingResultクラスから結果を格納する
    	 * 分岐式を通して結果に応じた値をビュー側に返却する */

    	User userExists = userService.findUserByUserName(user.getUserName());
    	/* UserRepository内に["Model/User"]から[String:userName]を取り出すリポジトリを登録済み */
    	/* 全てのリポジトリは基本、各種サービスクラスにインジェクション済み */
    	/* userExistsは["Model/User"]から[String:userName]を取り出すためにリポジトリを呼び出しフォームから取得した
    	 * 社員番号の値を取得するためのもの */

        if (userExists != null) {
            result
                    .rejectValue("userName", "error.user",
                            "*登録済み");
        }

        /* 登録フォームから送信された社員番号が既に登録されている場合 */
        /* 結果をBindingResultクラスに格納し、該当のフォーム["社員番号"]へバリデーション結果
                              "*登録済み"を表示する */

        if (result.hasErrors()) {
            mav.setViewName("registration");
        /* 登録フォームから送信されたいずれかの値にバリデーションが通過できなかった場合 */
        /* 全ての結果をBindingResultクラスからビュー側へ返却する */
        /* コントローラ側はURLパラメータに["registration"]を付与するだけ */

        } else {
            userService.saveUser(user);
            mav.addObject("successMessage", "登録が完了しました");
//          mav.addObject("user", new User()); /* 単体テスト結果では必要ないかもしれない。*/
            mav.setViewName("startup");
        }
        /* 処理が正常に終了した場合 */
        /* ["Service/UserService"]からsaveUserを実行する */
        /* 登録処理が正常に終了したことを通知する為に["successMessage"]オブジェクトをビュー側へ返却する */
        /* 登録処理後は["startup"]に遷移する */
        return mav;
    }
}
