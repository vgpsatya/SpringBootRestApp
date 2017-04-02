package com.spring.rest;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    void init();

    void store(MultipartFile file);

    void writeToAFile(String content, String fileName);
    
    public String readFile(String fileName);
    
    void deleteAll();

}
