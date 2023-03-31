package com.bogdanmierloiu.criminal_files_management.repository;

import com.bogdanmierloiu.criminal_files_management.entity.CriminalFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CriminalFileRepository extends JpaRepository<CriminalFile, Long> {

    @Query("SELECT cf FROM CriminalFile cf LEFT JOIN FETCH cf.authors a LEFT JOIN FETCH cf.crimeType ct where a is null or size(cf.authors) = 0 order by cf.registrationDate desc ")
    List<CriminalFile> findByAuthorsIsNullOrderByRegistrationDate();

    List<CriminalFile> findByAuthorsIsNotNullOrderByRegistrationDateDesc();
}
