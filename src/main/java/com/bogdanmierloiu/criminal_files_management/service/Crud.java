package com.bogdanmierloiu.criminal_files_management.service;

import java.util.List;

public interface Crud<T,K> {

    T add(K request);

    List<T> getAll();

    T findById(Long id);

    T update(K request);

    void delete(Long id);
}
