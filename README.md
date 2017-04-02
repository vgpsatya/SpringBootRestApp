# SpringBootRestApp
Spring Boot Restful Application
1. Click on "Clone or download" button -> download .zip file.
2. Extract the .zip file into in any of your local directory.
3. Download "Spring Tool Suite" version 3.8.2 (pre requisite java1.8).
4. Import extracted maven project into "Spring Tool Suite" editor.
5. Run "SpringBootRestApplication.java" file as "spring boot app"

API to upload a file with a few meta-data fields. Persist meta-data in persistence store (In memory DB or file system and store the content on a file system)
	-> http://localhost:8080/upload

API to get file meta-data

	-> http://localhost:8080/getMetadata

To test these APIs follow the below instructions

1.open browser and type the following url http://localhost:8080/fileUpload for uploading  file .

2. The file should be less than 10MB.

3. Open browser and type the following url http://localhost:8080/getMetadata for getting  file metadata.
