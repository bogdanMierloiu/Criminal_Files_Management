package com.bogdanmierloiu.criminal_files_management.service;

import com.bogdanmierloiu.criminal_files_management.dto.CriminalFileRequest;
import com.bogdanmierloiu.criminal_files_management.dto.CriminalFileResponse;
import com.bogdanmierloiu.criminal_files_management.entity.Author;
import com.bogdanmierloiu.criminal_files_management.entity.CriminalFile;
import com.bogdanmierloiu.criminal_files_management.mapper.CriminalFileMapper;
import com.bogdanmierloiu.criminal_files_management.repository.AuthorRepository;
import com.bogdanmierloiu.criminal_files_management.repository.CrimeTypeRepository;
import com.bogdanmierloiu.criminal_files_management.repository.CriminalFileRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CriminalFileService implements Crud<CriminalFileResponse, CriminalFileRequest> {
    private final CriminalFileRepository criminalFileRepository;
    private final CrimeTypeRepository crimeTypeRepository;
    private final AuthorRepository authorRepository;
    private final CriminalFileMapper criminalFileMapper;
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @Override
    public CriminalFileResponse add(CriminalFileRequest request) {
        CriminalFile criminalFileToSave = criminalFileMapper.map(request);
        criminalFileToSave.setCrimeType(request.getCrimeTypeId() != null ? crimeTypeRepository.findById(request.getCrimeTypeId())
                .orElseThrow(() -> new NotFoundException("The crime type with id " + request.getCrimeTypeId() + " not found!")) : null);
        List<Author> authorList = new ArrayList<>();
        for (var i : request.getAuthorsId()) {
            Author author = authorRepository.findById(i)
                    .orElseThrow(() -> new NotFoundException("The author with id " + i + " not found!"));
            authorList.add(author);
        }
        criminalFileToSave.setAuthors(authorList);
        CriminalFile savedCriminalFile = criminalFileRepository.save(criminalFileToSave);
        return criminalFileMapper.mapWithDetails(savedCriminalFile);
    }

    @Override
    public List<CriminalFileResponse> getAll() {
        return null;
    }

    @Override
    public CriminalFileResponse findById(Long id) {
        return null;
    }

    @Override
    public CriminalFileResponse update(CriminalFileRequest request) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }


    public void importFiles() {
        String filePath = "C:\\Users\\Andreea\\Desktop\\EvidentaAN.docx";
        try (FileInputStream inputStream = new FileInputStream(filePath);
             XWPFDocument document = new XWPFDocument(inputStream)) {
            List<XWPFTable> tables = document.getTables();
            XWPFTable table = tables.get(0); // get the first table
            List<XWPFTableRow> rows = table.getRows();
            for (int i = 0; i < rows.size(); i++) { // start from 0 - do not skip header row
                XWPFTableRow row = rows.get(i);
                List<XWPFTableCell> cells = row.getTableCells();
                String[] registrationParts = cells.get(0).getText().split("/");
                String registrationNumber = registrationParts[0];
                LocalDate registrationDate = LocalDate.parse(registrationParts[1], DATE_FORMAT);
                String registrationNumberProsecutor = cells.get(1).getText();
                String legalQualification = cells.get(2).getText();

                CriminalFile criminalFile = new CriminalFile();
                criminalFile.setRegistrationNumberPS(Long.parseLong(registrationNumber));
                criminalFile.setRegistrationDate(registrationDate);
                criminalFile.setRegistrationNumberProsecutor(registrationNumberProsecutor);
                criminalFile.setLegalQualification(legalQualification);

                criminalFileRepository.save(criminalFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
