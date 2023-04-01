package com.bogdanmierloiu.criminal_files_management.service;

import com.bogdanmierloiu.criminal_files_management.exception.SolutionDescriptionException;

import java.util.List;

public interface Crud<T,K> {

    K add(T request);

    List<K> getAll();

    K findById(Long id);

    K update(T request) throws SolutionDescriptionException;

    void delete(Long id);
}
