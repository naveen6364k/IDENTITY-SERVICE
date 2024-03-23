package com.innoclique.identity.repository;

import com.innoclique.identity.entity.SecurityGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SecurityGroupRepository extends JpaRepository<SecurityGroupEntity,Integer> {
    @Query("SELECT groupName FROM SecurityGroupEntity sg WHERE sg.securityGroupId = :securityGroupId")
    String findGroupNameBySecurityGroupId(@Param("securityGroupId") int securityGroupId);
}
