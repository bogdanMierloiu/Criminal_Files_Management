package com.bogdanmierloiu.criminal_files_management.repository;

import com.bogdanmierloiu.criminal_files_management.entity.CriminalFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CriminalFileRepository extends JpaRepository<CriminalFile, Long> {
}
