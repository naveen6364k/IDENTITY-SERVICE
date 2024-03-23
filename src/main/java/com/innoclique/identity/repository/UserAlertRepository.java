package com.innoclique.identity.repository;

import com.innoclique.identity.DTO.UserAlerts;
import com.innoclique.identity.entity.UserAlertsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAlertRepository extends JpaRepository<UserAlerts,Integer> {

}
