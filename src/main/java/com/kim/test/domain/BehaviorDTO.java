package com.kim.test.domain;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter @Setter
public class BehaviorDTO {
    private int id;             // 이상행동 고유번호
    private String cctv_name;   // 해당 cctv명
    private String file_path;   // 해당 이상행동 사진 경로
    private String regdate;  // 해당 이상행동 시간

    public BehaviorDTO(int id, String cctv_name, String file_path, String regdate) {
        this.id = id;
        this.cctv_name = cctv_name;
        this.regdate = regdate;
        this.file_path = file_path;
    }

    @Override
    public String toString() {
        return "{ cctv_name='" + cctv_name + '\'' +
                ", regdate='" + regdate + '\'' +
                ", file_path='" + file_path + "' }";
    }
}
