package com.innoclique.identity.repository;

import com.innoclique.identity.DTO.Provider;
import com.innoclique.identity.entity.ProviderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProviderRepository extends JpaRepository<Provider,Integer> {
    @Query(value = "SELECT p.ProviderID AS providerId, CONCAT(firstName, ' ', lastName) AS fullName FROM Provider p", nativeQuery = true)
    List<Provider> getAllProviders();
}
