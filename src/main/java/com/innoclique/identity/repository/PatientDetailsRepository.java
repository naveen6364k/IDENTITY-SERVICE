package com.innoclique.identity.repository;

import com.innoclique.identity.entity.PatientDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientDetailsRepository extends JpaRepository<PatientDetailsEntity, Integer> {

}
