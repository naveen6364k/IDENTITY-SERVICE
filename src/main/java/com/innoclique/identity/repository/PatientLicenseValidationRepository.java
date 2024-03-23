package com.innoclique.identity.repository;

import com.innoclique.identity.DTO.LicenseValidation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientLicenseValidationRepository extends JpaRepository<LicenseValidation,Integer> {

    @Procedure(procedureName = "USP_ValidateLicenseKey")
    List<LicenseValidation> validateLicenseKey(@Param("pvcLicenseKey") String licenseKey);

}
