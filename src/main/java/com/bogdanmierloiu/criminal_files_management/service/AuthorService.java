package com.bogdanmierloiu.criminal_files_management.service;

import com.bogdanmierloiu.criminal_files_management.dto.AuthorRequest;
import com.bogdanmierloiu.criminal_files_management.dto.AuthorResponse;
import com.bogdanmierloiu.criminal_files_management.entity.Author;
import com.bogdanmierloiu.criminal_files_management.mapper.AuthorMapper;
import com.bogdanmierloiu.criminal_files_management.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthorService implements Crud<AuthorResponse, AuthorRequest> {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    @Override
    public AuthorResponse add(AuthorRequest request) {
        return authorMapper.map(authorRepository.save(authorMapper.map(request)));
    }

    @Override
    public List<AuthorResponse> getAll() {
        return null;
    }

    @Override
    public AuthorResponse findById(Long id) {
        return null;
    }

    @Override
    public AuthorResponse update(AuthorRequest request) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
