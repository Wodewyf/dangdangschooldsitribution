package com.domain;

//用户账号表


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.stereotype.Repository;

//lombok.data注解可以获得get和set方法
@TableName("tb_user")
@Data
public class user {
    private Integer id;
    private String username;
    private String userpassword;
    private String phonenumber;
    private String address;
    private String userename;

}
