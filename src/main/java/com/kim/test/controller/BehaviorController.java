package com.kim.test.controller;

import com.kim.test.domain.BehaviorDTO;
import com.kim.test.mapper.BehaviorMapper;
import com.kim.test.service.BehaviorService;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "*")
@Slf4j
@RestController
public class BehaviorController{

    @Autowired
    private BehaviorService behaviorService;

    @Autowired
    private BehaviorMapper behaviorMapper;

    @Autowired
    private SimpMessagingTemplate websocket;


    // 이상행동 감지 시 db에 추가
    @PostMapping("/behavior/add-behavior")
    @MessageMapping("/Template")
    public void uploadBehavior(BehaviorDTO behaviorDTO,
                               @RequestParam("image") MultipartFile file)throws IOException{
        if (file.isEmpty()){
            behaviorDTO.setFile(null);
        }
        else{
            String fileName = behaviorService.storeFile(file);
            behaviorDTO.setFile(fileName);
        }

        behaviorMapper.insertBehavior(behaviorDTO);
        websocket.convertAndSend("/topics/template", behaviorDTO.getCctv() + ", 상활발생: "+behaviorDTO.getRegdate());
        log.info("POST BEHAVIOR: " + behaviorDTO.getRegdate());
    }


    // 이상행동 전체 리스트 얻어오기
    @GetMapping("/behavior/list")
    public List<BehaviorDTO> findAllBehavior(){
        log.info("GET All Behaviorlist");
        return behaviorMapper.findAllBehavior();
    }

    @GetMapping(value = "/behavior/list/{file}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> userSearch(@PathVariable("file") String file) throws IOException {
        InputStream imageStream = new FileInputStream("/root/app/step1/pic/" + file);
        byte[] imageByteArray = IOUtils.toByteArray(imageStream);
        imageStream.close();
        log.info("GET PICTURE: " + file);
        return new ResponseEntity<byte[]>(imageByteArray, HttpStatus.OK);
    }


 /*
    // 특정 cctv에서 일어난 이상행동 전체 리스트 얻어오기
    @GetMapping("/list/{cctv_name}")
    public List<BehaviorDTO> findByBehavior(@PathVariable("cctv_name") String cctv_name){
        log.info("GET '" + cctv_name + "'");
        return behaviorService.findByBehavior(cctv_name);
    }


    // cctv와 일어난 시간에 해당하는 이상행동 리스트 가져오기
    @GetMapping("/list/{cctv_name}/{regdate}")
    public List<BehaviorDTO> findByBehaviorTime(@PathVariable("cctv_name") String cctv_name,
                                                @PathVariable("regdate") String regdate){
        log.info("GET " + cctv_name + ", "+ regdate);
        return behaviorService.findByBehaviorTime(cctv_name, regdate);
    }
     */


}
