package com.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.domain.image;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface imageMapper extends BaseMapper<image> {

    Integer insertimage(String image);
}
