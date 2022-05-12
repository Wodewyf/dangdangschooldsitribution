package com.domain;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tb_comment")
public class comment {
    Integer commentid;
    Long id;
    String username;
    String drivername;
    Integer distributionspeedcomment;
    Integer distributionattitudecomment;
    Integer distributionobjectcommnet;
    String descriptioncommnet;

}
