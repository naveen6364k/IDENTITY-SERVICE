package com.innoclique.identity.repository;


import com.innoclique.identity.entity.PatientLocationMappingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientLocationMappingRepository extends JpaRepository<PatientLocationMappingEntity, Integer> {

}
