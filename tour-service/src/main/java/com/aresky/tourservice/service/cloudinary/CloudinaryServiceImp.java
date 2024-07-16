package com.aresky.tourservice.service.cloudinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CloudinaryServiceImp implements ICloudinaryService {
    @Autowired
    private Cloudinary cloudinary;

    @Override
    @SuppressWarnings("rawtypes")
    public Map uploadImage(MultipartFile file) throws IOException {
        return this.uploadImage(file, "bk-travel");
    }

    @Override
    @SuppressWarnings("rawtypes")
    public Map uploadImage(MultipartFile file, String folderName) throws IOException {
        return cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap(
                "resource_type", "auto",
                "folder", folderName));
    }

    @Override
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List uploadMultipartFiles(MultipartFile[] files) throws IOException {
        List result = new ArrayList();

        for (MultipartFile file : files) {
            Map res = this.uploadImage(file, "bk-travel");
            result.add(res);
        }

        return result;
    }

    @Override
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List uploadMultipartFiles(MultipartFile[] files, String folderName) throws IOException {
        List result = new ArrayList();

        for (MultipartFile file : files) {
            Map res = this.uploadImage(file, folderName);
            result.add(res);
        }

        return result;
    }

    @Override
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List uploadMultipartFiles(List<MultipartFile> files) {
        List result = new ArrayList();
        files.forEach(file -> {
            Map res = null;

            try {
                res = this.uploadImage(file, "bk-travel");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if (res != null) {
                result.add(res);
            }
        });
        return result;
    }

    @Override
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<String> uploadMultipartFiles(List<MultipartFile> files, String folderName) {
        List result = new ArrayList();
        files.forEach(file -> {
            Map res = null;

            try {
                res = this.uploadImage(file, folderName);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if (res != null) {
                result.add(res);
            }
        });
        return result;
    }
}
