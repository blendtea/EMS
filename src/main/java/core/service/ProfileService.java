package core.Service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import core.Model.Profile;
import core.Repository.UserProfileRepository;

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
    	profile.setAssigned(profile.getAssigned());
    	profile.setBirth(profile.getBirth());
    	profile.setHobby(profile.getHobby());
    	profile.setMsg(profile.getMsg());
    	profile.setSchool(profile.getSchool());
    	profile.setSex(profile.getSex());
    	profile.setTown(profile.getTown());
    	return profileRepository.save(profile);
    }
}
