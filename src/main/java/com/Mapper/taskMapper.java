package com.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.domain.driver;
import com.domain.statistic;
import com.domain.task;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Repository
@Mapper
public interface taskMapper extends BaseMapper<task> {

    List<task> selectbyusername(String username);

    //    取消订单，实际上就是修改订单状态，所以是update
    Integer updatestatus(@Param("id") Long id, @Param("status") String status);

    Integer updatedeletestatus2(@Param("id") Long id, @Param("status") String status);

    //    返回所有带接单的任务
    List<task> selectbytask(task task);

    Integer updatetask(task task);

    List<task> selectbydrivername(String drivername);

    Integer updatetask2(task task);

    //    查询状态
    String selectstatus(Long id);

    //    查询总订单
    Integer selecttotaltask();

    Integer selecttotalyisongda();

    Integer selecttotalcancel();

    Integer selecttotaldelete();

    Integer selecttotalpeisongzhong();

    ArrayList<statistic> selectmosttaskusername();

    ArrayList<statistic> selectmosttaskdrivername();
}
