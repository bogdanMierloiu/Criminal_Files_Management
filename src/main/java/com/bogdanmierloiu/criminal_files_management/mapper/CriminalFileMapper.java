package com.bogdanmierloiu.criminal_files_management.mapper;

import com.bogdanmierloiu.criminal_files_management.dto.CriminalFileRequest;
import com.bogdanmierloiu.criminal_files_management.dto.CriminalFileResponse;
import com.bogdanmierloiu.criminal_files_management.entity.CriminalFile;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CriminalFileMapper {

    CriminalFile map(CriminalFileRequest request);

    CriminalFileResponse map(CriminalFile author);

    List<CriminalFileResponse> map(List<CriminalFile> authorList);
}
