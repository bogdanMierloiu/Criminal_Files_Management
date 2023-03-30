package com.bogdanmierloiu.criminal_files_management.service;

import com.bogdanmierloiu.criminal_files_management.entity.Author;
import com.bogdanmierloiu.criminal_files_management.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthorService implements Crud<Author> {

    private final AuthorRepository authorRepository;

    @Override
    public Author add(Author request) {
        return authorRepository.save(request);
    }

    @Override
    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author findById(Long id) {
        return null;
    }

    @Override
    public Author update(Author request) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
