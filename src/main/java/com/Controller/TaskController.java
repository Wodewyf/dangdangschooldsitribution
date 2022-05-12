package com.Controller;


import com.Controller.utils.R;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.domain.task;
import com.domain.user;
import com.service.taskservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    public taskservice taskservice;
    @Value("${school.imagepath}")
    private String basepath;

    @PostMapping
    public R inserttask(@RequestBody task task) {
        if (taskservice.inserttask(task) != 0) {
            return new R("true", null);
        } else {
            return new R("flase", null);
        }
    }


    @GetMapping("/username/{username}")
    public R selectbyusername(HttpServletRequest request, @PathVariable String username) {
        request.getSession().setAttribute("username", username);
        if (taskservice.selectbyusername(username) != null) {
            return new R("true", taskservice.selectbyusername(username));
        } else {
            return new R("flase", null);
        }
    }

    @GetMapping("/{id}")
    public R selectbyid(HttpServletRequest request, @PathVariable Long id, HttpServletResponse response) throws IOException {

        if (taskservice.selectbyid(id) != null) {
            //        把任务号存下来
            request.getSession().setAttribute("taskid2", taskservice.selectbyid(id).getId());


            return new R("true", taskservice.selectbyid(id));
        } else {
            return new R("flase", null);
        }
    }

    //    评论功能打开侧栏获取内容
    @GetMapping("/comment")
    public R selectidcomment(HttpServletRequest request) {
        Long taskid3 = (Long) request.getSession().getAttribute("taskid3");

        if (taskservice.selectidcomment(taskid3) != null) {
            return new R("true", taskservice.selectidcomment(taskid3));
        } else {
            return new R("flase", null);
        }

    }


    @GetMapping("/cancel/{id}/{status}")
    public R updatestatus(@PathVariable Long id, @PathVariable String status) throws UnsupportedEncodingException {
        if (taskservice.updatestatus(id, status) != 0) {
            return new R("true", null);
        } else {
            return new R("flase", null);
        }
    }


    @GetMapping("/delete/{id}/{status}")
    public R updatestatus2(@PathVariable Long id, @PathVariable String status) throws UnsupportedEncodingException {
        if (taskservice.updatedeletestatus2(id, status) != 0) {
            return new R("true", null);
        } else {
            return new R("flase", null);
        }
    }

    @PostMapping("/daijiedan")
    public R selectbystatus(@RequestBody task task) throws IOException {


        if (taskservice.selectbytask(task) != null) {
            return new R("true", taskservice.selectbytask(task));
        } else {
            return new R("flase", null);
        }
    }

    @PutMapping("/update")
    public R updatetask(HttpServletRequest request, @RequestBody task task) {
//        读取session
        Object taskid = request.getSession().getAttribute("taskid2");
        task.setId((Long) taskid);

        if (taskservice.updatetask(task) == 1) {
            return new R("true", "1");

        } else {
            return new R("flase", null);
        }
    }

    @GetMapping("/drivername/{drivername}")
    public R selectbydirvername(HttpServletRequest request, @PathVariable String drivername) {
        request.getSession().setAttribute("drivername1", drivername);
        if (taskservice.selectbydrivername(drivername) != null) {
            return new R("true", taskservice.selectbydrivername(drivername));
        } else {
            return new R("flase", null);
        }
    }

    @PutMapping("/confirm")
    public R updateconfirmstatus(HttpServletRequest request, @RequestBody task task) {


        if (taskservice.updatestatus2(task) == 1) {
            return new R("true", "1");

        } else {
            return new R("flase", null);
        }
    }

    @GetMapping("/admin/{currentPage}/{pageSize}")
    public R getpage(@PathVariable Integer currentPage, @PathVariable Integer pageSize, task task) {
        IPage<com.domain.task> page = taskservice.getPage(currentPage, pageSize, task);
        if (currentPage > page.getPages()) {
            page = taskservice.getPage((int) page.getPages(), pageSize, task);
        }

        return new R("true", page);
    }

    //        删除操作
    @DeleteMapping("/admin/{id}")
    public R deletetask(@PathVariable Long id) {
        if (taskservice.deletetask(id) != null) {
            return new R("true", null);
        } else {
            return new R("flase", null);
        }
    }


    //    用户端动态查询
    @PostMapping("/getstatus")
    public R getstatus(HttpServletRequest request, @RequestBody task task) {
        String username = (String) request.getSession().getAttribute("username");
        task.setUsername(username);
        List<com.domain.task> getstatus = taskservice.getstatus(task);
        if (getstatus != null) {
            return new R("true", getstatus);
        } else {
            return new R("flase", getstatus);
        }

    }

    //    骑手端任务大厅动态查询
    @PostMapping("/getstatus1")
    public R getstatus1(@RequestBody task task) {
        List<com.domain.task> getstatus1 = taskservice.getstatus1(task);
        if (getstatus1 != null) {
            return new R("true", getstatus1);
        } else {
            return new R("flase", getstatus1);
        }

    }


    //    骑手端接单界面动态查询
    @PostMapping("/getstatus2")
    public R getstatus2(HttpServletRequest request, @RequestBody task task) {
        Object drivername1 = request.getSession().getAttribute("drivername1");
        task.setDrivername((String) drivername1);

        List<com.domain.task> tasks = taskservice.getstatus2(task);
        if (tasks != null) {
            return new R("true", tasks);
        } else {
            return new R("flase", tasks);
        }

    }


    @GetMapping("/getstatus/{id}")
    public R getstatus(HttpServletRequest request, @PathVariable Long id) {
//        存task的id
        request.getSession().setAttribute("taskid3", id);
        return new R("true", taskservice.selectstatus(id));
    }

    @PostMapping("/totaltask")
    public R gettotaltask() {
        return new R("true", taskservice.selecttotaltask());
    }


}
