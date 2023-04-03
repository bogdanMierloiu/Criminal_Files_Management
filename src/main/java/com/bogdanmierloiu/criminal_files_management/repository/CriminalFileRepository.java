package com.bogdanmierloiu.criminal_files_management.repository;

import com.bogdanmierloiu.criminal_files_management.entity.CriminalFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CriminalFileRepository extends JpaRepository<CriminalFile, Long> {

    @Query("SELECT cf FROM CriminalFile cf LEFT JOIN FETCH cf.authors a LEFT JOIN FETCH cf.crimeType ct where a is null or size(cf.authors) = 0 order by cf.registrationDate desc")
    List<CriminalFile> findAllByAuthorsIsNullOrderByRegistrationDate();

    List<CriminalFile> findAllByAuthorsIsNotNullOrderByRegistrationDateDesc();

    @Query("SELECT cf FROM CriminalFile cf LEFT JOIN FETCH cf.authors a LEFT JOIN FETCH cf.crimeType ct WHERE cf.isResolved = false AND (a IS null OR size(cf.authors) = 0)  ORDER BY cf.registrationDate DESC")
    List<CriminalFile> findAllInProgressOrderByRegistrationDate();

    @Query("SELECT cf FROM CriminalFile cf LEFT JOIN FETCH cf.authors a LEFT JOIN FETCH cf.crimeType ct WHERE a IS not null AND cf.isResolved = false ORDER BY cf.registrationDate DESC")
    List<CriminalFile> findAllInProgressWithAuthorsOrderByRegistrationDate();

    @Query("SELECT DISTINCT cf FROM CriminalFile cf " +
            "LEFT JOIN FETCH cf.authors a " +
            "LEFT JOIN FETCH cf.crimeType " +
            "WHERE cf.registrationNumberPS = :longSearchTerm " +
            "OR cf.registrationNumberProsecutor LIKE %:stringSearchTerm% ")
    List<CriminalFile> findByRegistrationNumber(@Param("longSearchTerm") Long longSearchTerm,
                                                @Param("stringSearchTerm") String stringSearchTerm);


    @Query(
            "SELECT DISTINCT cf FROM CriminalFile cf " +
                    "LEFT JOIN FETCH cf.authors a " +
                    "LEFT JOIN FETCH cf.crimeType " +
                    "WHERE a.id = :id "
    )
    List<CriminalFile> findByAuthorId(@Param("id") Long id);

}
