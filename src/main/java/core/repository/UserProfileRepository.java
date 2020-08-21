package core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import core.model.Profile;

@Repository
public interface UserProfileRepository extends JpaRepository<Profile, Long> {
	
}
