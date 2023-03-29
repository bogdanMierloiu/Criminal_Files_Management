package com.bogdanmierloiu.criminal_files_management.repository;

import com.bogdanmierloiu.criminal_files_management.entity.CrimeType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrimeTypeRepository extends JpaRepository<CrimeType, Long> {
}
