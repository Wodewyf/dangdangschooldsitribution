package com.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.domain.driver;
import com.domain.driver;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface driverMapper extends BaseMapper<driver> {
    driver selectbydriverid(@Param("drivername") String drivername, @Param("driverpassword") String driverpassword);

    //    查询操作 1，查看有无drivername在表中存在，如果有，就注册失败，如果没有，就注册成功
    driver selectbydrivername(@Param("drivername") String drivername, @Param("driverpassword") String driverpassword);

    //    新增操作
    Integer insertdriver(@Param("drivername") String username, @Param("driverpassword") String userpassword);

    //    异步查询根据用户输入的账号是否在数据库中存留。。。。
    driver selectbydrivername1(@Param("drivername") String drivername);


}
