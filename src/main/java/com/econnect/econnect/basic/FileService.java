package com.econnect.econnect.basic;

import jakarta.transaction.Transactional;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.aspectj.util.FileUtil;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Service
public class FileService {
    public static final int MAX_FILE_SIZE = 1024 * 1024 * 10;

    @Transactional()
    public boolean uploadFile(String path, String name, MultipartFile file) throws IOException {
        String mimeType = file.getContentType();
        if(file.getSize() > MAX_FILE_SIZE)
            throw new FileUploadException("10BM 이하 파일만 업로드 가능합니다.");

        //파일이 없으면 생성
        File f = new File(path);
        if(!f.exists()) f.mkdir();

        ClassPathResource classPathResource = new ClassPathResource("img/" + path + name);

        Files.copy((Path) file.getInputStream(), (Path) classPathResource, StandardCopyOption.REPLACE_EXISTING);
        return true;
    }
}
