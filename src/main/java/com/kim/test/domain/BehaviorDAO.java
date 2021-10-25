package com.kim.test.domain;

import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.List;

public interface BehaviorDAO {
    public String storeFile(MultipartFile file);

    public void insertBehavior(BehaviorDTO behaviorDTO);
    public List<BehaviorDTO> findAllBehavior();

    //public List<BehaviorDTO> findByBehavior(String cctv_name);
    //public List<BehaviorDTO> findByBehaviorTime(String cctv_name, String regdate);
}
