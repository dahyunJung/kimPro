package com.kim.test.mapper;

import com.kim.test.domain.BehaviorDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BehaviorMapper {

    @Insert("insert into behaviorlist(cctv, file, regdate) values (#{cctv}, #{file}, #{regdate})")
    public void insertBehavior(BehaviorDTO behaviorDTO);

    @Select("select * from behaviorlist")
    public List<BehaviorDTO> findAllBehavior();

}
