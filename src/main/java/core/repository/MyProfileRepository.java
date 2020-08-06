package core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import core.model.MyProfile;

	@Repository
	public interface MyProfileRepository extends JpaRepository<MyProfile, Long> {
		MyProfile findByUserName(String userName);
	}