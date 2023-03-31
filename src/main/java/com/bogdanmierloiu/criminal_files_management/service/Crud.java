package com.bogdanmierloiu.criminal_files_management.service;

import java.util.List;

public interface Crud<T,K> {

    K add(T request);

    List<K> getAll();

    K findById(Long id);

    K update(T request);

    void delete(Long id);
}
