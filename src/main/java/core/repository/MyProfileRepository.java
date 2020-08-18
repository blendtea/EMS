package core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import core.model.MyProfile;

public interface MyProfileRepository extends JpaRepository<MyProfile, Long> {
	
}