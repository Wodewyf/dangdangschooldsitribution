package com.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.domain.user;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletRequest;

@Mapper
@Repository
public interface userMapper extends BaseMapper<user> {

    //    登录操作
    user selectbyuserid(@Param("username") String username, @Param("userpassword") String userpassword);


    //    查询操作 1，查看有无username在表中存在，如果有，就注册失败，如果没有，就注册成功
    user selectbyusername(@Param("username") String username, @Param("userpassword") String userpassword);

    //    新增操作
    Integer insertuser(@Param("username") String username, @Param("userpassword") String userpassword);

    //    异步查询根据用户输入的账号是否在数据库中存留。。。。
    user selectbyusername1(@Param("username") String username);
}
