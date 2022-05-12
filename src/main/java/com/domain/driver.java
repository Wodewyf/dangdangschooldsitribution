package com.domain;

//骑手账号表

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("tb_driver")
@Data
public class driver {
    Integer id;
    String drivername;
    String driverpassword;
    String driverenglishname;
    Integer age;
    String dphonenumber;
    String description;


}
