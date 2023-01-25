package com.base.PetSearchServer.service;

import com.base.PetSearchServer.entity.FileData;
import com.base.PetSearchServer.repository.FileDataRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;
import java.util.UUID;

@Service
public class StorageService {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private FileDataRepository fileDataRepository;


    public String uploadImageToFileSystem(MultipartFile file) throws IOException {

        String resultFilename = UUID.randomUUID() + "." + file.getOriginalFilename();

        String filePath = uploadPath + resultFilename;

        FileData fileData = fileDataRepository.save(FileData.builder()
                .name(resultFilename)
                .type(file.getContentType())
                .filePath(filePath).build());

        file.transferTo(new File(filePath));

        if (fileData != null && !file.getOriginalFilename().isEmpty()) {
            return resultFilename;
        }
        return null;
    }

    public byte[] downloadImageFromFileSystem(String fileName) throws IOException {
        Optional<FileData> fileData = fileDataRepository.findByName(fileName);
        String filePath=fileData.get().getFilePath();
        byte[] images = Files.readAllBytes(new File(filePath).toPath());
        return images;
    }
}