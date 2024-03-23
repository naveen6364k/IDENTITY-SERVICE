package com.innoclique.identity.repository;

import com.innoclique.identity.entity.LocationDetailsEntity;
import com.innoclique.identity.entity.UserEntity;
import com.innoclique.identity.entity.UserLocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLocationRepository extends JpaRepository<UserLocationEntity,Integer> {
    UserLocationEntity findByUserAndDefaultFlag(UserEntity updatedUser, int b);

    UserLocationEntity findByUserAndLocation(UserEntity updatedUser, LocationDetailsEntity allowedLocation);
}
