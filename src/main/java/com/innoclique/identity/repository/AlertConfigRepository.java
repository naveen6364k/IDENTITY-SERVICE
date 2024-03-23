package com.innoclique.identity.repository;

import com.innoclique.identity.entity.AlertConfigEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlertConfigRepository extends JpaRepository<AlertConfigEntity,Integer> {
}
