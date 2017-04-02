package com.spring.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class RestService {
	
	@Autowired 
	StorageService storageService;

	@RequestMapping(value="/upload")
	  public String handleFileUpload(
	         @RequestParam(value="file", required=true) MultipartFile file) {
	   String response = "";
		try {
				storageService.store(file);
				StringBuilder content = new StringBuilder();
				content.append("File Name:"+file.getOriginalFilename());
				content.append(", ");
				content.append("File Size:"+file.getSize()+" bytes");
				content.append(", ");
				content.append("File ContentType:"+file.getContentType());
				storageService.writeToAFile(content.toString(), file.getOriginalFilename()+"_metadata.txt");
	    	   response = "File uploaded successfully";
	    	   System.out.println(response);
	    } catch (Exception e) {
	    	response = e.getMessage();
	    }
	    
	    return response;
	  }
	
	
	@RequestMapping(value="/getMetadata")
	public String getFileMetadata(
			@RequestParam(value="fileName", required=true) String fileName){
		String response = "";
		try{
			response = storageService.readFile(fileName+"_metadata.txt");
		}catch(Exception e){
			response = e.getMessage();
		}
		return response;
	}

	public StorageService getStorageService() {
		return storageService;
	}
	
	public void setStorageService(StorageService storageService) {
	    this.storageService = storageService;
	}
	
}
