package core.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

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
    public User saveUser(User user) {
    	user.setPassword(user.getPassword());
    	user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    	Role userRole = roleRepository.findByRole("USER");
    	user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
    	return userRepository.save(user);
    }
    //Addition
    public List <User> findAll() {
    	return userRepository.findAll();
    }
    public User save(User finder) {
    	finder.setSex(finder.getSex());
    	finder.setAssigned(finder.getAssigned());
    	finder.setBirth(finder.getBirth());
    	finder.setSchool(finder.getSchool());
    	finder.setHobby(finder.getHobby());
    	finder.setTown(finder.getTown());
    	finder.setMsg(finder.getMsg());
    	return userRepository.save(finder);
    }
    public User findUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }
    public User findOne(Long id) {
    	Optional<User> user = userRepository.findById(id);
    	if(user.isPresent()) {
    		return user.get();
    	}else {
    		return null;
    	}
    }
}
