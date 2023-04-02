package com.bogdanmierloiu.criminal_files_management.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "criminal_file")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class CriminalFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "registration_number_police_station", unique = true, nullable = false)
    private Long registrationNumberPS;
    @Column(name = "registration_date", nullable = false)
    private LocalDate registrationDate;
    @Column(name = "registration_number_prosecutor", unique = true, nullable = false)
    private String registrationNumberProsecutor;
    @Column(name = "legal_qualification", nullable = false)
    private String legalQualification;
    @Column(name = "is_resolved", nullable = false)
    @JsonProperty
    private Boolean isResolved;

    @Column(name = "resolution_date")
    private LocalDate resolutionDate;

    @Column(name = "solution_description")
    private String solutionDescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "crime_type_id")
    @ToString.Exclude
    private CrimeType crimeType;
    @ManyToMany
    @JoinTable(name = "criminal_file_author",
            joinColumns = @JoinColumn(name = "criminal_file_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    @ToString.Exclude
    private List<Author> authors = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CriminalFile that = (CriminalFile) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
