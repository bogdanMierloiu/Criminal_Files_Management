package com.bogdanmierloiu.criminal_files_management.repository;

import com.bogdanmierloiu.criminal_files_management.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    @Query("SELECT c FROM Author c order by c.lastName asc")
    List<Author> findAllOrderByLastNameAsc();

    @Query("SELECT a FROM Author a where (lower(a.firstName) LIKE lower(concat('%', :firstName, '%'))) " +
            "OR (lower(a.lastName) LIKE lower(concat('%', :lastName, '%')))")
    List<Author> findByFirstNameAndLastName(@Param("firstName") String firstName,
                                            @Param("lastName") String lastName);

}
