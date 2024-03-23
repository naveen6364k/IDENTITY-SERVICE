package com.innoclique.identity.repository;


import com.innoclique.identity.entity.PatientMoodMoniterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientMoodMoniterRepository  extends JpaRepository<PatientMoodMoniterEntity,Integer> {
}
