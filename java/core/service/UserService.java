package core.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import core.model.Role;
import core.model.User;
import core.repository.RoleRepository;
import core.repository.UserRepository;

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

    public List <User> findAll() {
    	return userRepository.findAll();
    }
    public User save(User user) {
    	User finder = findById(user.getId());
    	finder.setSex(user.getSex());
    	finder.setAssigned(user.getAssigned());
    	finder.setBirth(user.getBirth());
    	finder.setSchool(user.getSchool());
    	finder.setHobby(user.getHobby());
    	finder.setTown(user.getTown());
    	finder.setMsg(user.getMsg());
    	return userRepository.save(user);
    }

    //Addition
    public User findUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }
    public User findById(Long id) {
        return userRepository.findById(id).get();
    }
    public User saveUser(User user) {
    	user.setPassword(user.getPassword());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findByRole("ADMIN");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        return userRepository.save(user);
    }
}
