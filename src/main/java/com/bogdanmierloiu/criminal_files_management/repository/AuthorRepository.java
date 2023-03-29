package com.bogdanmierloiu.criminal_files_management.repository;

import com.bogdanmierloiu.criminal_files_management.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
