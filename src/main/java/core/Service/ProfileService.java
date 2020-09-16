package core.Service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import core.Model.Profile;
import core.Repository.UserProfileRepository;

@Service
@Transactional(rollbackOn = Exception.class)
public class ProfileService {

	@Autowired
	private UserProfileRepository profileRepository;

    public ProfileService(UserProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }
    public Profile save(Profile profile) {
    	return profileRepository.save(profile);
    }
  public Profile findUserByUserName(String userName) {
    	return profileRepository.findByUserName(userName);
    }
}