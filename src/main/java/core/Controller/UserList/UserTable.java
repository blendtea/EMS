package core.Controller.UserList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import core.Model.Profile;
import core.Repository.UserProfileRepository;
@RestController
@Controller
public class UserTable {
	@Autowired
    private UserProfileRepository profileRepository;

	@GetMapping(value="/pages/UserList")
	public ModelAndView UserList() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("pages/UserList");
		return mav;
		}
	//登録ユーザ一覧をリスト表示する
    @GetMapping("/127.0.0.1")
    public List<Profile> ID(Model model) {
        List<Profile> list = profileRepository.findAll();
        return list;
    }
    //サービスへ登録しているユーザーを一覧表示する
    @GetMapping(value="/pages/Users")
    public ModelAndView Users() {
    	ModelAndView mav = new ModelAndView();
    	mav.setViewName("pages/Users");
    	return mav;
    }

}
