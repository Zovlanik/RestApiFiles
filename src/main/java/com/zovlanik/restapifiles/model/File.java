package com.zovlanik.restapifiles.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="files")
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "filename")
    private String filename;
    @Column(name = "creationDate")
    private Date creationDate;
    @Column(name = "deletionDate")
    private Date deletionDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "file_status")
    private FileStatus status;
    @ManyToMany(mappedBy = "files")
    private List<User> users;

    public File() {
    }

    public File(String filename) {
        this.filename = filename;
        this.creationDate = new Date(System.currentTimeMillis());
        this.status = FileStatus.ACTIVE; //при создании файл доступен
    }

    public String getFilename() {
        return filename;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
