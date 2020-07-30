package core.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import core.model.User;
import core.repository.UserRepository;

@RestController
public class Search {
	//登録ユーザー一覧をHomeにリスト表示する
    @Autowired
    private UserRepository userRepository;
    @GetMapping("/admin/search")
    public List<User> ID(Model model) {
        List<User> list = userRepository.findAll();
        return list;
    }
}

/* 
 * システムロケーション
 * Registration=>Login=>Home=>{[Search]}|{Profile}
 */
