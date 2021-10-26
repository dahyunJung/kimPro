package com.kim.test.service;

import com.kim.test.FileProperties;
import com.kim.test.Exception.FileUploadException;
import com.kim.test.domain.BehaviorDAO;
import com.kim.test.domain.BehaviorDTO;
import com.kim.test.mapper.BehaviorMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.*;
import java.sql.Timestamp;
import java.util.List;

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
        //String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try{
            // 파일을 저장할 경로를 Path 객체로 받는다
            Path targetLocation = this.fileLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return fileName;
            //return targetLocation.toString();
        } catch (Exception e) {
            throw new FileUploadException("fail", e);
        }
    }


/*
    @Override
    public void insertBehavior(BehaviorDTO behaviorDTO){
        behaviorMapper.insertBehavior(behaviorDTO);
    }

    @Override
    public List<BehaviorDTO> findAllBehavior(){
        return behaviorMapper.findAllBehavior();
    }
 */

    /*
    @Override
    public List<BehaviorDTO> findByBehavior(String cctv_name){
        return behaviorMapper.findByBehavior(cctv_name);
    }

    @Override
    public List<BehaviorDTO> findByBehaviorTime(String cctv_name, String regdate){
        return behaviorMapper.findByBehaviorTime(cctv_name, regdate);
    }

    public String getImg(String cctv_name, String regdate){
        return behaviorMapper.getImg(cctv_name, regdate);
    }

     */


}
