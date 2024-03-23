package com.innoclique.identity.repository;

import com.innoclique.identity.entity.LanguageDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguagesRepository extends JpaRepository<LanguageDetailsEntity,Integer> {
}
