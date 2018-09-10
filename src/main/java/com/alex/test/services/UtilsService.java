package com.alex.test.services;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

public class UtilsService {

    public static String addUserPhotoToDir(MultipartFile userPhoto, String fullPath) throws IOException {
        File uploadDir = new File(fullPath);

        if (!uploadDir.exists()) {
            Files.createDirectories(Paths.get(fullPath));
        } else {
            FileUtils.cleanDirectory(uploadDir);
        }

        return photoTransfer(userPhoto, fullPath);
    }

    public static String addCarPhotoToDir(MultipartFile carPhoto, String fullPath) throws IOException {
        File uploadDir = new File(fullPath);

        if (!uploadDir.exists()) {
            Files.createDirectories(Paths.get(fullPath));
        }

        return photoTransfer(carPhoto, fullPath);
    }

    private static String photoTransfer(MultipartFile file, String fullPath) throws IOException {
        String uuidFile = UUID.randomUUID().toString();
        String resultFile = uuidFile + "." + file.getOriginalFilename();

        file.transferTo(new File(fullPath + "/" + resultFile));

        return resultFile;
    }

}
