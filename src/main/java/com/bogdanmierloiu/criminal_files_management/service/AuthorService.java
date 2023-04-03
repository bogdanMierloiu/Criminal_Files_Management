package com.bogdanmierloiu.criminal_files_management.service;

import com.bogdanmierloiu.criminal_files_management.dto.AuthorRequest;
import com.bogdanmierloiu.criminal_files_management.dto.AuthorResponse;
import com.bogdanmierloiu.criminal_files_management.entity.Author;
import com.bogdanmierloiu.criminal_files_management.mapper.AuthorMapper;
import com.bogdanmierloiu.criminal_files_management.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthorService implements Crud<AuthorRequest, AuthorResponse> {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    @Override
    public AuthorResponse add(AuthorRequest request) {
        return authorMapper.map(authorRepository.save(authorMapper.map(request)));
    }

    @Override
    public List<AuthorResponse> getAll() {
        return authorMapper.map(authorRepository.findAllOrderByLastNameAsc());
    }

    @Override
    public AuthorResponse findById(Long id) {
        return authorMapper.map(authorRepository.findById(id).orElseThrow(
                () -> new NotFoundException("The author with " + id + " not found")));
    }

    @Override
    public AuthorResponse update(AuthorRequest request) {
        Author authorToUpdate = authorRepository.findById(request.getId()).orElseThrow(
                () -> new NotFoundException("The author with id " + request.getId() + " not found"));
        authorToUpdate.setFirstName(request.getFirstName() != null ? request.getFirstName() : authorToUpdate.getFirstName());
        authorToUpdate.setLastName(request.getLastName() != null ? request.getLastName() : authorToUpdate.getLastName());
        authorToUpdate.setPersonalCode(request.getPersonalCode() != null ? request.getPersonalCode() : authorToUpdate.getPersonalCode());
        return authorMapper.map(authorToUpdate);
    }

    @Override
    public void delete(Long id) {
        Author authorToDelete = authorRepository.findById(id).orElseThrow(
                () -> new NotFoundException("The author with id " + id + " not found!")
        );
        authorRepository.delete(authorToDelete);
    }

    public List<AuthorResponse> findByName(String firstName, String lastName) {
        return authorMapper.map(authorRepository.findByFirstNameAndLastName(firstName, lastName));
    }
}
