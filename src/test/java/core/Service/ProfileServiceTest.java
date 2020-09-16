package core.Service;

import static org.junit.Assert.*;

import java.lang.reflect.Field;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import core.Model.Profile;
import core.Repository.UserProfileRepository;

class ProfileServiceTest {

	@Autowired
	private ProfileService profileservice;

	private UserProfileRepository profileRepository;

	private Profile profile = new Profile();

	private Profile find;

	private ProfileServiceTest service;

	private Logger logger = LoggerFactory.getLogger(ProfileServiceTest.class);


//	public ProfileServiceTest(UserProfileRepository profileRepository) {
//		this.profileRepository = profileRepository;
//	}
	@BeforeEach
	void setUp() throws Exception {
		profile.setUserName("000");
		System.out.println("SystemOutPrintlnとは出力レベルが異なる");
		    }

	@Test
	public void servicetest() throws Exception{
		//インスタンスの生成  コンストラクタを呼び出す
		profileservice = new ProfileService(profileRepository);
//
		Field test = profileservice.getClass().getDeclaredField("profileRepository");
//		アクセス制限を解除
		test.setAccessible(true);
		assertEquals(profileRepository, (UserProfileRepository) test.get(profileservice));
	}



	@Test
	public void savetest() throws Exception{

		Profile saves = new ProfileService(profileRepository).save(profile);
		System.out.println(saves);
//		assertEquals(profile.getUserName(),save.getUserName());
	}

//		@Test
//		public void findtest() throws Exception{
////			ProfileService profileservice = new ProfileService(profileRepository);
////			Field test3 = profileservice.getClass().getDeclaredField("profileRepository");
//////		アクセス制限を解除
////			test3.setAccessible(true);
//			find = profileservice.findUserByUserName(profile.getUserName());
////			assertEquals(profile.getUserName(),);
//		}

}