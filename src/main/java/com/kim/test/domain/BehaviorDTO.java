package com.kim.test.domain;

import lombok.Data;

//@Getter
//@Setter
//@AllArgsConstructor
@Data
public class BehaviorDTO {
    private int id;             // 이상행동 고유번호
    private String cctv;   // 해당 cctv명
    private String file;   // 해당 이상행동 사진 경로
    private String regdate;  // 해당 이상행동 시간
}
