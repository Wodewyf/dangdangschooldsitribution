package com.Mapper;

import com.domain.admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface adminMapper {

    admin selectbyadminid(@Param("adminname") String adminname, @Param("adminpassword") String adminpassword);


}
