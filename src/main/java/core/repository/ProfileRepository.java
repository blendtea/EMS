package core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import core.model.ProfileData;

@Repository
public interface ProfileRepository extends JpaRepository<ProfileData, Long> {
    ProfileData findByProfile(String fullName);
}
