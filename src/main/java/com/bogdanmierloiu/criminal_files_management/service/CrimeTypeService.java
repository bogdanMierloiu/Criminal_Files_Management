package com.bogdanmierloiu.criminal_files_management.service;

import com.bogdanmierloiu.criminal_files_management.entity.CrimeType;
import com.bogdanmierloiu.criminal_files_management.repository.CrimeTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CrimeTypeService implements Crud<CrimeType> {

    private final CrimeTypeRepository crimeTypeRepository;

    @Override
    public CrimeType add(CrimeType request) {
        return crimeTypeRepository.save(request);
    }

    @Override
    public List<CrimeType> getAll() {
        return null;
    }

    @Override
    public CrimeType findById(Long id) {
        return null;
    }

    @Override
    public CrimeType update(CrimeType request) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
