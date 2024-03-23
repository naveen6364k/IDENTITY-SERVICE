package com.innoclique.identity.repository;

import com.innoclique.identity.entity.LocationDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<LocationDetailsEntity, Integer> {

    @Query("SELECT ld.locationId FROM LocationDetailsEntity ld WHERE ld.locationName = :locationName")
    Integer findLocationIdByLocationName(String locationName);

    LocationDetailsEntity findByLocationId(int locationID);

}
