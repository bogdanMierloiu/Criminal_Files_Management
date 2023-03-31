package com.bogdanmierloiu.criminal_files_management.service;

import com.bogdanmierloiu.criminal_files_management.dto.CrimeTypeRequest;
import com.bogdanmierloiu.criminal_files_management.dto.CrimeTypeResponse;
import com.bogdanmierloiu.criminal_files_management.entity.CrimeType;
import com.bogdanmierloiu.criminal_files_management.mapper.CrimeTypeMapper;
import com.bogdanmierloiu.criminal_files_management.repository.CrimeTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

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
        return crimeTypeMapper.map(crimeTypeRepository.findAll());
    }

    @Override
    public CrimeTypeResponse findById(Long id) {

        return crimeTypeMapper.map(crimeTypeRepository.findById(id).orElseThrow(
                () -> new NotFoundException("The crime type with id " + id + " not found")
        ));
    }

    @Override
    public CrimeTypeResponse update(CrimeTypeRequest request) {

        CrimeType crimeTypeToUpdate = crimeTypeRepository.findById(request.getId()).orElseThrow(
                () -> new NotFoundException("The crime type with id " + request.getId() + " not found"));
        crimeTypeToUpdate.setType(request.getType() != null ? request.getType() : crimeTypeToUpdate.getType());
        return crimeTypeMapper.map(crimeTypeRepository.save(crimeTypeToUpdate));
    }

    @Override
    public void delete(Long id) {
        CrimeType crimeTypeToDelete = crimeTypeRepository.findById(id).orElseThrow(
                () -> new NotFoundException("The crime type with id " + id + " not found"));
        crimeTypeRepository.delete(crimeTypeToDelete);
    }
}
