package core.Controller;

/*
 * Package : ModelAndView
 */

/* ModelAndViewクラスからコントローラ側が受け取った値をビュー側へ値を渡す
 * public ModelAndView 変数(引数) {
 *
 * ModelAndView mav = new ModelAndView(); ←インスタンスを生成する
 * .setViewName("ページ名")
 * .addObject("変数", オブジェクト)
 * 	return mav; ←返り値はModelAndViewクラスなのでmav(変数)を返す
 * }
 */

/*
 * Package : Account
 */

/* Login.java
 * @GetMapping(value="PathToAny")を使用してマッピング先のURLパスを設定する
 * User user = new User(); ←Model/Userのインスタンスを生成する
 * mav.addObject("user", user); ←モデルオブジェクトにアクセスできるようにする(Thymeleaf th:objが使う)
 * mav.setViewName("startup"); ←ページ名(.htmlを省略する)
 * return mav;
 */
/* Registration.java
 * @Autowiredを使用してServiceクラスをインジェクションする(継承しなくても使えるようになるので便利)
 * @GetMappingについては上記と同じ
 * @PostMapping(value="PathToAny")を使用してマッピング先のURLパスを設定する
 * public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {処理}
 * 上記引数にはバリデーション(@Valid)と遷移結果(BindingResult)、Userモデルを指定している
 * User userExists = userService.findUserByUserName(user.getUserName());
 * 上記userExists変数ではuserService内にある社員番号から該当するユーザーを検索する(Select)
 * 条件分岐文
 * 1) 該当するユーザーがすでに存在している場合 → 登録済み, エラーを返す
 * 2) 処理結果がエラーであった場合 → startupページへ遷移, エラーを返す 
 * 3) アカウント登録が成功している場合 → 入力情報をデータベースへ保存, 登録完了を返す
*/
