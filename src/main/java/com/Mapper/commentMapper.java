package com.Mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.domain.comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface commentMapper extends BaseMapper<comment> {


    Integer insertcomment1(comment comment);

    comment selectcomment(comment comment);

    Integer deleteByCommentid(Integer id);

    List<Map<String,Object>> selectstaic();


}
