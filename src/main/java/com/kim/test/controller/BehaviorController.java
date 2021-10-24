package com.kim.test.controller;

import com.kim.test.domain.BehaviorDTO;
import com.kim.test.service.BehaviorService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.List;

@CrossOrigin(origins = "http://192.168.1.106:3004")
//@Component
@Slf4j
@RestController
@RequestMapping(value = "/behavior")
@ApiOperation(value = "controller")
public class BehaviorController{

    @Autowired
    private BehaviorService behaviorService;


    // 이상행동 감지 시 db에 추가
    @PostMapping("/add-behavior")
    public void uploadBehavior(BehaviorDTO behaviorDTO,
                               @RequestParam("image") MultipartFile file){
        if (file.isEmpty()){
            behaviorDTO.setFile_path("none");
        }
        else{
            String fileName = behaviorService.storeFile(file);
            behaviorDTO.setFile_path(fileName);
        }

        behaviorService.insertBehavior(behaviorDTO);

        log.info("POST BEHAVIOR: " + behaviorDTO.toString());
    }

    // 이상행동 전체 리스트 얻어오기
    @GetMapping("/list")
    public List<BehaviorDTO> getAllBehavior(){
        log.info("GET All Behaviorlist");
        return behaviorService.findAllBehavior();
    }

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


    @GetMapping("img/{cctv_name}/{regdate}")
    public String getImg(@PathVariable("cctv_name") String cctv_name,
                         @PathVariable("regdate") String regdate){
        log.info("img");
        return behaviorService.getImg(cctv_name, regdate);
    }


}
