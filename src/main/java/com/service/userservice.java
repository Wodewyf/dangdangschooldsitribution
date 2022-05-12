package com.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.domain.driver;
import com.domain.user;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public interface userservice {

    user selectbyuserid(String username, String userpassword);

    List<user> selectall();

    Integer selectbyusername(user user);

    //   这个是没点注册之前判断提醒用户账号是否被注册
    Boolean selectbyusername1(String username);

    //   通过id返回所有user数据
    user selectbyid(Integer id);

    Boolean updateuserinfo(user user);

    IPage<user> getpage(int currentPage, int pageSize, user user);

    Integer insert(user user);

    //    删除操作
    Integer deleteuser(Integer id);

    user selectbyid2(Integer id);

    Integer updatebydr(user user);
}
