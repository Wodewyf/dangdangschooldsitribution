package com.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.domain.statistic;
import com.domain.task;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Service
public interface taskservice {
    // ada

    //    生成任务
    int inserttask(task task);

    //    通过username查找出该用户的所有任务单
    List<task> selectbyusername(String username);

    //    通过id查找出任务的详情
    task selectbyid(Long id);

    //    修改任务状态 这里是取消 将状态修改为cancle
    Integer updatestatus(Long id, String status) throws UnsupportedEncodingException;

    //    删除订单（只删除记录，数据库不删除） 还是修改，将状态修改为delete
    Integer updatedeletestatus2(Long id, String status);

    //    返回待接单状态的任务
    List<task> selectbytask(task task);

    //    骑手接取订单，实际就是骑手的信息往task表里加
    Integer updatetask(task task);

    List<task> selectbydrivername(String drivername);

    //    修改订单状态为已送达
    Integer updatestatus2(task task);

    IPage<task> getPage(int currentPage, int pageSize, task task);

    Integer deletetask(Long id);

    //    用户端动态查询状态，地址，价格
    List<task> getstatus(task task);

    //    骑手端任务大厅动态查询
    List<task> getstatus1(task task);

    //     骑手配送页面动态查询
    List<task> getstatus2(task task);

    String selectstatus(Long id);

    //    评论侧栏获取id
    task selectidcomment(Long id);

    statistic selecttotaltask();
}
