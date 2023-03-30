package com.bogdanmierloiu.criminal_files_management.mapper;

import com.bogdanmierloiu.criminal_files_management.dto.AuthorRequest;
import com.bogdanmierloiu.criminal_files_management.dto.AuthorResponse;
import com.bogdanmierloiu.criminal_files_management.entity.Author;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface AuthorMapper {

    Author map(AuthorRequest request);

    AuthorResponse map(Author author);

    List<AuthorResponse> map(List<Author> authorList);
}
