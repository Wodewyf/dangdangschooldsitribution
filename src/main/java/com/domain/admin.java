package com.domain;

//管理员账号表

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("tb_admin")
@Data
public class admin {
    Integer id;
    String adminname;
    String adminpassword;
    String adminenglishname;
}
