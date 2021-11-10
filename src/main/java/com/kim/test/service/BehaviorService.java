package com.kim.test.service;

import com.kim.test.FileProperties;
import com.kim.test.Exception.FileUploadException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class BehaviorService {
    private final Path fileLocation;

    // 디렉토리 기본 설정, 생성
    @Autowired
    public BehaviorService(FileProperties bp){
        // filelocation에 디렉토리 저장위치가 지정됨
        this.fileLocation = Paths.get(bp.getUploadDir()).toAbsolutePath().normalize();
        try{
            Files.createDirectories(this.fileLocation);
        }catch (Exception e){
            throw new FileUploadException("fail", e);
        }
    }

    // 파일 저장 소스
    public String storeFile(MultipartFile file){
        String fileName = file.getOriginalFilename();

        try{
            // 파일을 저장할 경로를 Path 객체로 받는다
            Path targetLocation = this.fileLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        } catch (Exception e) {
            throw new FileUploadException("fail", e);
        }
    }


}
