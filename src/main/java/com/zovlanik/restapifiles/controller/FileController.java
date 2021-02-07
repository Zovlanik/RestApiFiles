package com.zovlanik.restapifiles.controller;

import com.zovlanik.restapifiles.model.File;
import com.zovlanik.restapifiles.repository.FileRepository;
import com.zovlanik.restapifiles.repository.hibernate.HibernateFileRepositoryImpl;


public class FileController {
    private final FileRepository fileRepository = new HibernateFileRepositoryImpl();

    public void create(File file) {
        fileRepository.create(file);
    }

    public File getById(int id){
        return fileRepository.getById(id);
    }

    public void update(File newFile){
        fileRepository.update(newFile);
    }

    public void deleteById(int id){
        fileRepository.deleteById(id);
    }

}

