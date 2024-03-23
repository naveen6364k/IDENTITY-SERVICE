package com.innoclique.identity.repository;

import com.innoclique.identity.entity.MoodstatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoodStatusRepository extends JpaRepository<MoodstatusEntity, Integer> {
    List<MoodstatusEntity> findByMoodStatusId(Integer moodStatusId);
}
