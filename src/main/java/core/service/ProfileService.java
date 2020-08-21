package core.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import core.model.Profile;
import core.repository.UserProfileRepository;

@Service
@Transactional
public class ProfileService {

    private UserProfileRepository profileRepository;
    @Autowired
    public ProfileService(UserProfileRepository profileRepository) {
    	this.profileRepository = profileRepository;
    	}
    
    //Custom userService <Profile Model>
    public Profile save(Profile profile) {
    	return profileRepository.save(profile);
    }
}
