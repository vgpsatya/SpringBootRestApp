package com.spring.rest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileSystemStorageService implements StorageService {

    private final Path rootLocation;
    

    @Autowired
    public FileSystemStorageService(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
    }

    @Override
    public void store(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + file.getOriginalFilename());
            }
            Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));
        } catch (IOException e) {
        	e.printStackTrace();
            throw new StorageException("Failed to store file " + file.getOriginalFilename(), e);
        }
    }
    
    @Override
    public void writeToAFile(String content, String fileName) {
    	try (BufferedWriter bw = new BufferedWriter(new FileWriter(this.rootLocation.toString()+File.separator+fileName))) {
			bw.write(content);
		}  catch (IOException e) {
        	e.printStackTrace();
            throw new StorageException("Failed to write file " + fileName, e);
        }
    }
    
    @Override
    public String readFile(String fileName) {
    	StringBuilder str = new StringBuilder();
    	try (BufferedReader br = new BufferedReader(new FileReader(this.rootLocation.toString()+File.separator+fileName))) {
			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {
				str.append(sCurrentLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new StorageException("Failed to read file " + e);
		}
    	return str.toString();
    }
    


    @Override
    public void init() {
        try {
            Files.createDirectory(rootLocation);
        } catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }
    
    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }
}
