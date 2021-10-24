package com.kim.test.mapper;

import com.kim.test.domain.BehaviorDTO;
import org.apache.ibatis.annotations.*;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface BehaviorMapper {

    @Insert("insert into behaviorlist(cctv_name, file_path, regdate) values (#{cctv_name}, #{file_path}, #{regdate})")
    public void insertBehavior(BehaviorDTO behaviorDTO);

    @Select("select * from behaviorlist")
    public List<BehaviorDTO> findAllBehavior();

    @Select("select * from behaviorlist where cctv_name = #{cctv_name}")
    public List<BehaviorDTO> findByBehavior(@Param("cctv_name") String cctv_name);

    @Select("select * from behaviorlist where cctv_name = #{cctv_name} && regdate = #{regdate}")
    public List<BehaviorDTO> findByBehaviorTime(@Param("cctv_name") String cctv_name, @Param("regdate") String regdate);


    @Select("select file_path from behaviorlist where cctv_name = #{cctv_name} && regdate = #{regdate}")
    public String getImg(@Param("cctv_name") String cctv_name, @Param("regdate") String regdate);
}
