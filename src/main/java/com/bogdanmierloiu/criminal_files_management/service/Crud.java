package com.bogdanmierloiu.criminal_files_management.service;

import java.util.List;

public interface Crud<T> {

    T add(T request);

    List<T> getAll();

    T findById(Long id);

    T update(T request);

    void delete(Long id);
}
