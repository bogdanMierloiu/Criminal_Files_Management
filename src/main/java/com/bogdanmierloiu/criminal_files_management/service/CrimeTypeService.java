package com.bogdanmierloiu.criminal_files_management.service;

import com.bogdanmierloiu.criminal_files_management.dto.CrimeTypeRequest;
import com.bogdanmierloiu.criminal_files_management.dto.CrimeTypeResponse;
import com.bogdanmierloiu.criminal_files_management.mapper.CrimeTypeMapper;
import com.bogdanmierloiu.criminal_files_management.repository.CrimeTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CrimeTypeService implements Crud<CrimeTypeResponse, CrimeTypeRequest> {

    private final CrimeTypeRepository crimeTypeRepository;
    private final CrimeTypeMapper crimeTypeMapper;

    @Override
    public CrimeTypeResponse add(CrimeTypeRequest request) {
        return crimeTypeMapper.map(crimeTypeRepository.save(crimeTypeMapper.map(request)));
    }

    @Override
    public List<CrimeTypeResponse> getAll() {
        return null;
    }

    @Override
    public CrimeTypeResponse findById(Long id) {
        return null;
    }

    @Override
    public CrimeTypeResponse update(CrimeTypeRequest request) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
