package core.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import core.Model.Profile;

@Repository
public interface UserProfileRepository extends JpaRepository<Profile, Long> {}