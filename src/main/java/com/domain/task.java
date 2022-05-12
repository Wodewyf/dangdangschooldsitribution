package com.domain;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("tb_task")
public class task {
    @TableId(type = IdType.ASSIGN_ID)
    Long id;
    String taskname;
    double taskprice;
    String taskdescription;
    String phonenumber;
    String username;
    String userename;
    String recevicetime;
    String status;
    String taskstarttime;
    String useraddress;
    String drivername;
    String driverenglishname;
    String dphonenumber;
    //    骑手接单时间
    String taskmiddletime;
    String driversendtime;
    String drivercalsendtime;
    String image;
}
