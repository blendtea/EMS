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
    //Crud Repository
    public Optional<User> findOne(Long id) {
    	return userRepository.findById(id);
    }
    public List <User> findAll() {
    	return userRepository.findAll();
    }
    public User save(User user) {
    	return userRepository.save(user);
    }

    //Addition
    public User findUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }
    /*
    public Optional<User> selectById(long id) {
    	return userRepository.findById(id);
    }*/

    public User saveUser(User user) {
    	user.setPassword(user.getPassword());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findByRole("ADMIN");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        return userRepository.save(user);
    }
//    public User createOrUpdateEmployee(User user)
//	{
//		if(user.getUserName()  == null)
//		{
//			user = repository.save(user);
//
//			return user;
//		}
//		else
//		{
//			User usr = userRepository.findByUserName(get.UserName());
//
//			if(user.isPresent())
//			{
//				User newuser = user.get();
//				newuser.setUserName(user.getUserName());
//				newuser.setFirstName(user.getFirstName());
//				newuser.setLastName(user.getLastName());
//
//				newuser = UserRepository.save(user);
//
//				return newuser;
//			} else {
//				user = UserRepository.save(user);
//
//				return user;
//			}
//		}
//	}
}
