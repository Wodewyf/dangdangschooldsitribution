package com.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.domain.driver;
import com.domain.driver;
import com.domain.user;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface driverservice {

    driver selectbydriver(String drivername, String driverpassword);

    Integer selectbydrivername(user user);

    //   这个是没点注册之前判断提醒用户账号是否被注册
    Boolean selectbydrivername1(String drivername);

    //    查询所有数据
    List<driver> selectall();

    //    新增操作
    Boolean insertdriver(driver driver);

    //    删除操作
    Boolean deletedriver(Integer id);

    //    前台弹出修改界面，通过id获取到所有数据,返回给前端
    driver selectbyid2(Integer id);

    //    修改数据
    Boolean updatebydr(driver driver);


    IPage<driver> getpage(int currentPage, int pageSize, driver driver);

////    按条件查询
//    List<driver> getcon(driver driver);


}
