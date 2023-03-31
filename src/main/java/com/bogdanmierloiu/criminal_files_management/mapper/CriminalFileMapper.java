package com.bogdanmierloiu.criminal_files_management.mapper;

import com.bogdanmierloiu.criminal_files_management.dto.AuthorResponse;
import com.bogdanmierloiu.criminal_files_management.dto.CrimeTypeResponse;
import com.bogdanmierloiu.criminal_files_management.dto.CriminalFileRequest;
import com.bogdanmierloiu.criminal_files_management.dto.CriminalFileResponse;
import com.bogdanmierloiu.criminal_files_management.entity.Author;
import com.bogdanmierloiu.criminal_files_management.entity.CrimeType;
import com.bogdanmierloiu.criminal_files_management.entity.CriminalFile;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface CriminalFileMapper {

    CriminalFile map(CriminalFileRequest request);

    @Named("mapCriminalFileToResponse")
    CriminalFileResponse map(CriminalFile author);

    @IterableMapping(qualifiedByName = "mapCriminalFileToResponse")
    List<CriminalFileResponse> map(List<CriminalFile> authorList);

    CrimeTypeResponse map(CrimeType crimeType);

    List<AuthorResponse> mapAuthors(List<Author> authorList);

    @Named("mapCriminalFileToResponseWithDetails")
    default CriminalFileResponse mapWithDetails(CriminalFile criminalFile) {
        CriminalFileResponse response = map(criminalFile);
        response.setCrimeTypeResponse(map(criminalFile.getCrimeType()));
        response.setAuthorResponseList(mapAuthors(criminalFile.getAuthors()));
        return response;
    }

    @Named("mapListCriminalFileToResponseWithDetails")
    default List<CriminalFileResponse> mapWithDetailsList(List<CriminalFile> criminalFileList) {
        List<CriminalFileResponse> criminalFileResponses = map(criminalFileList);
        for (var i : criminalFileResponses) {
            for (var k : criminalFileList) {
                i.setCrimeTypeResponse(map(k.getCrimeType()));
            }
        }
        for (var i : criminalFileResponses) {
            for (var k : criminalFileList) {
                i.setAuthorResponseList(mapAuthors(k.getAuthors()));
            }
        }
        return criminalFileResponses;
    }
}
