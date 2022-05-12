package com.service.imp;

import com.Mapper.driverMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.domain.driver;

import com.domain.user;
import com.service.driverservice;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverServiceimpl implements driverservice {

    @Autowired
    private driverMapper driverMapper;


    @Override
    public driver selectbydriver(String drivername, String driverpassword) {
        return driverMapper.selectbydriverid(drivername, driverpassword);
    }

    @Override
    public Integer selectbydrivername(user user) {
        String username = user.getUsername();
        String userpassword = user.getUserpassword();

        driver driver1 = driverMapper.selectbydrivername(username, userpassword);
        if (driver1 != null) {
            return 0;
        } else {
            return driverMapper.insertdriver(username, userpassword);
        }

    }

    @Override
    public Boolean selectbydrivername1(String drivername) {

        driver driver = driverMapper.selectbydrivername1(drivername);
        if (driver != null) {
            return false;
        } else {
            return true;
        }
    }

    //    查询所有
    @Override
    public List<driver> selectall() {
        return driverMapper.selectList(null);
    }

    @Override
    public Boolean insertdriver(driver driver) {

        int insert = driverMapper.insert(driver);
        if (insert > 0) {

            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean deletedriver(Integer id) {

        int delete = driverMapper.deleteById(id);
        if (delete > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public driver selectbyid2(Integer id) {
        driver driver = driverMapper.selectById(id);
        return driver;
    }

    @Override
    public Boolean updatebydr(driver driver) {
        int delete = driverMapper.updateById(driver);
        if (delete > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public IPage<driver> getpage(int currentPage, int pageSize, driver driver) {
        LambdaQueryWrapper<driver> lambdaQueryWrapper = new LambdaQueryWrapper<driver>();

        lambdaQueryWrapper.like(Strings.isNotEmpty(driver.getDrivername()), com.domain.driver::getDrivername, driver.getDrivername());
        lambdaQueryWrapper.like(Strings.isNotEmpty(driver.getDphonenumber()), com.domain.driver::getDphonenumber, driver.getDphonenumber());
        lambdaQueryWrapper.like(Strings.isNotEmpty(driver.getDriverenglishname()), com.domain.driver::getDriverenglishname, driver.getDriverenglishname());
        IPage<driver> iPage = new Page<driver>(currentPage, pageSize);
        IPage<driver> iPage1 = driverMapper.selectPage(iPage, lambdaQueryWrapper);
        return iPage1;

    }


//    @Override
//    public List<driver> getcon(driver driver) {
//        LambdaQueryWrapper<driver> lambdaQueryWrapper = new LambdaQueryWrapper<driver>();
//        String age = driver.getAge()+"";
//        lambdaQueryWrapper.like(Strings.isEmpty(driver.getDrivername()), com.domain.driver::getDrivername,driver.getDrivername());
//        lambdaQueryWrapper.like(Strings.isEmpty(age), com.domain.driver::getAge,driver.getAge());
//        lambdaQueryWrapper.like(Strings.isEmpty(driver.getDriverenglishname()), com.domain.driver::getDriverenglishname,driver.getDriverenglishname());
//        return driverMapper.selectList(lambdaQueryWrapper);
//    }


}
