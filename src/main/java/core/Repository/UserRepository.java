package core.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import core.Model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName);
    /* 基本的にリポジトリクラスから直接使用することはない => ["Service/**"] */
    /* ["Model/User"]から["String:userName"]を取り出すためのリポジトリ追加 */
}
