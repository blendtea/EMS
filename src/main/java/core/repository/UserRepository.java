package core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import core.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    User findByUserName(String userName);
//    Optional<User> findById(Long id);
}
