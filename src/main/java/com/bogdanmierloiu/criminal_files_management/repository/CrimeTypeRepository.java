package com.bogdanmierloiu.criminal_files_management.repository;

import com.bogdanmierloiu.criminal_files_management.entity.Author;
import com.bogdanmierloiu.criminal_files_management.entity.CrimeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CrimeTypeRepository extends JpaRepository<CrimeType, Long> {

}
