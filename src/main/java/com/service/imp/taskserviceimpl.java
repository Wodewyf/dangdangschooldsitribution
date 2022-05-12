package com.service.imp;

import com.Mapper.taskMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.domain.statistic;
import com.domain.task;
import com.service.taskservice;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class taskserviceimpl implements taskservice {
    @Autowired
    public taskMapper taskMapper;


    @Override
    public int inserttask(task task) {

        task.setTaskstarttime(String.valueOf(LocalDateTime.now()));

        return taskMapper.insert(task);
    }

    @Override
    public List<task> selectbyusername(String username) {
        List<task> tasks = taskMapper.selectbyusername(username);
        return tasks;
    }

    @Override
    public task selectbyid(Long id) {
        return taskMapper.selectById(id);
    }

    @Override
    public Integer updatestatus(Long id, String status) throws UnsupportedEncodingException {


        Integer updatestatus = taskMapper.updatestatus(id, status);
        return updatestatus;
    }

    @Override
    public Integer updatedeletestatus2(Long id, String status) {
        Integer updatedeletestatus2 = taskMapper.updatedeletestatus2(id, status);
        return updatedeletestatus2;
    }

    @Override
    public List<task> selectbytask(task task) {

        return taskMapper.selectbytask(task);
    }

    @Override
    public Integer updatetask(task task) {
//        接取任务时间
        task.setTaskmiddletime(String.valueOf(LocalDateTime.now()));
        task.setStatus("配送中");

        int update = taskMapper.updatetask(task);

        return update;


    }

    @Override
    public List<task> selectbydrivername(String drivername) {

        List<task> selectbydrivername = taskMapper.selectbydrivername(drivername);

        return selectbydrivername;
    }

    @Override
    public Integer updatestatus2(task task) {

        task.setDriversendtime(String.valueOf(LocalDateTime.now()));

        return taskMapper.updatetask2(task);
    }

    @Override
    public IPage<task> getPage(int currentPage, int pageSize, task task) {
        LambdaQueryWrapper<task> lambdaQueryWrapper = new LambdaQueryWrapper<task>();
        lambdaQueryWrapper.like(Strings.isNotEmpty(task.getUserename()), com.domain.task::getUserename, task.getUserename());
        lambdaQueryWrapper.like(Strings.isNotEmpty(task.getDriverenglishname()), com.domain.task::getDriverenglishname, task.getDriverenglishname());
        lambdaQueryWrapper.like(Strings.isNotEmpty(task.getStatus()), com.domain.task::getStatus, task.getStatus());

        IPage<task> iPage = new Page<task>(currentPage, pageSize);
        IPage<com.domain.task> iPage1 = taskMapper.selectPage(iPage, lambdaQueryWrapper);
        return iPage1;
    }

    @Override
    public Integer deletetask(Long id) {
        int delete = taskMapper.deleteById(id);
        return delete;
    }


    @Override
    public List<task> getstatus(task task) {
        LambdaQueryWrapper<task> lambdaQueryWrapper = new LambdaQueryWrapper<task>();
        lambdaQueryWrapper.like(Strings.isNotEmpty(task.getStatus()), com.domain.task::getStatus, task.getStatus());
        lambdaQueryWrapper.like(Strings.isNotEmpty(task.getUseraddress()), com.domain.task::getUseraddress, task.getUseraddress());
        lambdaQueryWrapper.eq(price(task.getTaskprice()), com.domain.task::getTaskprice, task.getTaskprice());
        lambdaQueryWrapper.ne(com.domain.task::getStatus, "delete");
        lambdaQueryWrapper.eq(com.domain.task::getUsername, task.getUsername());


        List<com.domain.task> tasks = taskMapper.selectList(lambdaQueryWrapper);


        return tasks;

    }

    @Override
    public List<task> getstatus1(task task) {
        LambdaQueryWrapper<task> lambdaQueryWrapper = new LambdaQueryWrapper<task>();
        lambdaQueryWrapper.like(Strings.isNotEmpty(task.getUserename()), com.domain.task::getUserename, task.getUserename());
        lambdaQueryWrapper.like(Strings.isNotEmpty(task.getUseraddress()), com.domain.task::getUseraddress, task.getUseraddress());
        lambdaQueryWrapper.eq(price(task.getTaskprice()), com.domain.task::getTaskprice, task.getTaskprice());
        lambdaQueryWrapper.eq(com.domain.task::getStatus, "待接单");
        return taskMapper.selectList(lambdaQueryWrapper);
    }

    @Override
    public List<task> getstatus2(task task) {
        LambdaQueryWrapper<task> lambdaQueryWrapper = new LambdaQueryWrapper<task>();
        lambdaQueryWrapper.like(Strings.isNotEmpty(task.getUserename()), com.domain.task::getUserename, task.getUserename());
        lambdaQueryWrapper.like(Strings.isNotEmpty(task.getUseraddress()), com.domain.task::getUseraddress, task.getUseraddress());
        lambdaQueryWrapper.eq(price(task.getTaskprice()), com.domain.task::getTaskprice, task.getTaskprice());

        lambdaQueryWrapper.ne(com.domain.task::getStatus, "delete");
        lambdaQueryWrapper.eq(com.domain.task::getDrivername, task.getDrivername());

        return taskMapper.selectList(lambdaQueryWrapper);
    }

    @Override
    public String selectstatus(Long id) {
        String selectstatus = taskMapper.selectstatus(id);
        return selectstatus;
    }

    @Override
    public task selectidcomment(Long id) {
        task task1 = taskMapper.selectById(id);
        return task1;
    }

    @Override
    public statistic selecttotaltask() {
        statistic statistic = new statistic();
        statistic.setTotaltask(taskMapper.selecttotaltask());
        statistic.setTotalyisongda(taskMapper.selecttotalyisongda());
        statistic.setTotalcancel(taskMapper.selecttotalcancel());
        statistic.setTotaldelete(taskMapper.selecttotaldelete());
        statistic.setTotalpeisongzhong(taskMapper.selecttotalpeisongzhong());

//        把数据放到一个arrylist集合里面，只有一行的集合
        ArrayList<statistic> list = taskMapper.selectmosttaskusername();
        for (com.domain.statistic statistic1 : list) {
            statistic.setUsername(statistic1.getUsername());
            statistic.setCountxiadan(statistic1.getCountxiadan());
            statistic.setUsertotalprice(statistic1.getUsertotalprice());
        }

        ArrayList<statistic> list1 = taskMapper.selectmosttaskdrivername();
        for (com.domain.statistic statistic1 : list1) {
            statistic.setDrivername(statistic1.getDrivername());
            statistic.setCountjiedan(statistic1.getCountjiedan());
            statistic.setDrivertotalprice(statistic1.getDrivertotalprice());
        }


        return statistic;
    }

    static boolean price(Double str) {
        if (str != 0) {
            return true;
        } else {
            return false;
        }
    }


}
