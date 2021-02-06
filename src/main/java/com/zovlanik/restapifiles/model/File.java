package com.zovlanik.restapifiles.model;

import javax.xml.crypto.Data;
import java.util.Date;

public class File {
    String filename;
    Date creationDate;
    Date deletionDate;
    FileStatus status;

    public File() {
    }

    public File(String filename, Date creationDate) {
        this.filename = filename;
        this.creationDate = creationDate;
        this.status = FileStatus.ACTIVE; //при создании файл доступен
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getDeletionDate() {
        return deletionDate;
    }

    public void setDeletionDate(Date deletionDate) {
        this.deletionDate = deletionDate;
    }

    public FileStatus getStatus() {
        return status;
    }

    public void setStatus(FileStatus status) {
        this.status = status;
    }
}
