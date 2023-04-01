package com.bogdanmierloiu.criminal_files_management.repository;

import com.bogdanmierloiu.criminal_files_management.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    @Query("SELECT c FROM Author c order by c.lastName asc")
    List<Author> findAllOrderByLastNameAsc();
}
