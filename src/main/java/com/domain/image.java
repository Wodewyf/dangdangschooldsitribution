package com.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("tb_image")
@Data
public class image {
    Integer id;
    String image;
}
