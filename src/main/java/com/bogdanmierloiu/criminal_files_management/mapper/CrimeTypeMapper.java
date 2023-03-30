package com.bogdanmierloiu.criminal_files_management.mapper;

import com.bogdanmierloiu.criminal_files_management.dto.CrimeTypeRequest;
import com.bogdanmierloiu.criminal_files_management.dto.CrimeTypeResponse;
import com.bogdanmierloiu.criminal_files_management.entity.CrimeType;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CrimeTypeMapper {

    CrimeType map(CrimeTypeRequest request);

    CrimeTypeResponse map(CrimeType author);

    List<CrimeTypeResponse> map(List<CrimeType> authorList);
}
