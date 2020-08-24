package core.Service;

import java.util.Arrays;
import java.util.HashSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import core.Model.Role;
import core.Model.User;
import core.Repository.RoleRepository;
import core.Repository.UserRepository;

@Service
@Transactional
public class UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    public User saveUser(User user) {
    	user.setPassword(user.getPassword());
    	user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    	Role userRole = roleRepository.findByRole("USER");
    	user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
    	return userRepository.save(user);
    }
    public User save(User user) {
    	return userRepository.save(user);
    }
    public User findUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }
}
