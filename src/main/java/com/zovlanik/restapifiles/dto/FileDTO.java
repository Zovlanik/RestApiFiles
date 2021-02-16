package com.zovlanik.restapifiles.dto;

import com.zovlanik.restapifiles.model.File;

public class FileDTO {
    private Integer user_id;
    private String fileName;

    public FileDTO(Integer user_id) {
    }

    public FileDTO(Integer user_id, String fileName) {
        this.user_id = user_id;
        this.fileName = fileName;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public static FileDTO fromFile(File file){
        return new FileDTO(file.getUser_id(), file.getFilename());
    }
}
