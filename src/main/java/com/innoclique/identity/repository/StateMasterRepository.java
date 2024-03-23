package com.innoclique.identity.repository;

import com.innoclique.identity.entity.StateMasterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateMasterRepository extends JpaRepository<StateMasterEntity,Integer> {
}
