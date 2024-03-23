package com.innoclique.identity.repository;

import com.innoclique.identity.DTO.CityMaster;
import com.innoclique.identity.entity.CityMasterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CityMasterRepository extends JpaRepository<CityMaster,Integer> {
    List<CityMaster> findByStateId(Integer stateId);
}
