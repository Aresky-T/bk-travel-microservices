package com.aresky.tourservice.service.cloudinary;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ICloudinaryService {
    @SuppressWarnings("rawtypes")
    Map uploadImage(MultipartFile file) throws IOException;

    @SuppressWarnings("rawtypes")
    Map uploadImage(MultipartFile file, String folderName) throws IOException;

    List<String> uploadMultipartFiles(MultipartFile[] files) throws IOException;

    List<String> uploadMultipartFiles(MultipartFile[] files, String folderName) throws IOException;

    List<String> uploadMultipartFiles(List<MultipartFile> files);

    List<String> uploadMultipartFiles(List<MultipartFile> files, String folderName);
}
