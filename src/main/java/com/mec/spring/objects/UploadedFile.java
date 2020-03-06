package com.mec.spring.objects;

import org.springframework.web.multipart.MultipartFile;

public class UploadedFile {
    private MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public  String getFileName(){
        return file.getOriginalFilename();
    }
}
