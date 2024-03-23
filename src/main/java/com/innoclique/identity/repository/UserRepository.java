package com.innoclique.identity.repository;

import com.innoclique.identity.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer> {
    UserEntity findByFirstNameAndLastName(String firstName, String lastName);
}
