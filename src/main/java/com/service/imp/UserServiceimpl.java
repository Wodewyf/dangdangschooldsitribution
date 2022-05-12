package com.service.imp;

import com.Mapper.userMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.domain.user;
import com.service.userservice;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class UserServiceimpl implements userservice {

    @Autowired
    public userMapper userMapper;

    //    登录验证用户名和密码在数据库中是否存在
    @Override
    public user selectbyuserid(String username, String userpassword) {

        return userMapper.selectbyuserid(username, userpassword);
    }

    @Override
    public List<user> selectall() {

        return userMapper.selectList(null);
    }

    @Override
    public Integer selectbyusername(user user) {
        String username = user.getUsername();
        String userpassword = user.getUserpassword();

        user user1 = userMapper.selectbyusername(username, userpassword);
        if (user1 != null) {
            return 0;
        } else {
            return userMapper.insertuser(username, userpassword);
        }

    }

    @Override
    public Boolean selectbyusername1(String username) {

        user user = userMapper.selectbyusername1(username);
        if (user != null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public user selectbyid(Integer id) {
        return userMapper.selectById(id);
    }

    @Override
    public Boolean updateuserinfo(user user) {
        int i = userMapper.updateById(user);
        if (i > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public IPage<user> getpage(int currentPage, int pageSize, user user) {
        LambdaQueryWrapper<user> lambdaQueryWrapper = new LambdaQueryWrapper<user>();
        lambdaQueryWrapper.like(Strings.isNotEmpty(user.getUsername()), com.domain.user::getUsername, user.getUsername());
        lambdaQueryWrapper.like(Strings.isNotEmpty(user.getUserename()), com.domain.user::getUserename, user.getUserename());
        lambdaQueryWrapper.like(Strings.isNotEmpty(user.getAddress()), com.domain.user::getAddress, user.getAddress());

        IPage<user> iPage = new Page<user>(currentPage, pageSize);
        IPage<com.domain.user> iPage1 = userMapper.selectPage(iPage, lambdaQueryWrapper);
        return iPage1;

    }

    @Override
    public Integer insert(user user) {
        return userMapper.insert(user);
    }

    @Override
    public Integer deleteuser(Integer id) {
        return userMapper.deleteById(id);
    }

    @Override
    public user selectbyid2(Integer id) {
        return userMapper.selectById(id);
    }

    @Override
    public Integer updatebydr(user user) {
        return userMapper.updateById(user);
    }


}
