package com.domain;

import lombok.Data;
import org.springframework.stereotype.Component;


@Data
public class statistic {

    Integer totaltask;
    Integer totalyisongda;
    Integer totalcancel;
    Integer totaldelete;
    Integer totalpeisongzhong;


    String username;
    String drivername;

    //    下单最多的用户的下单次数
    Integer countxiadan;

    Double usertotalprice;
    //    接单最多的骑手的接单次数
    Integer countjiedan;

    Double drivertotalprice;
}
