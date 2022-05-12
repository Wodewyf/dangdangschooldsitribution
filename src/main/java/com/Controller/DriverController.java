package com.Controller;


import com.Controller.utils.R;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.domain.driver;
import com.domain.user;
import com.service.driverservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/drivers")
public class DriverController {

    @Autowired
    private driverservice driverservice;

    @GetMapping("/login/{drivername}/{driverpassword}")
    public R selectbydriver(HttpServletRequest request, @PathVariable String drivername, @PathVariable String driverpassword) {
        if (driverservice.selectbydriver(drivername, driverpassword) == null) {
            return new R("flase", driverservice.selectbydriver(drivername, driverpassword));
        } else {
//            存session
            request.getSession().setAttribute("driver", driverservice.selectbydriver(drivername, driverpassword).getId());
            return new R("true", driverservice.selectbydriver(drivername, driverpassword));
        }
    }

    @PostMapping
    public R insertdrivername(@RequestBody user user) {
        Integer intscucess = driverservice.selectbydrivername(user);
        if (intscucess == 0) {
            return new R("flase", driverservice.selectbydrivername(user));

        } else {
            return new R("true", driverservice.selectbydrivername(user));
        }
    }

    @GetMapping("/{drivername}")
    public R selectdrivername1(@PathVariable String drivername) {
        Boolean beizhuce = driverservice.selectbydrivername1(drivername);
        if (beizhuce == true) {
            return new R("true", null);
        } else {
            return new R("flase", null);
        }
    }


    @GetMapping
    public R selectall() {
        List<driver> selectalldriver = driverservice.selectall();

        if (selectalldriver != null) {
            return new R("true", selectalldriver);
        } else {
            return new R("flase", selectalldriver);
        }

    }

    //        新增操作
    @PostMapping("/admin")
    public R insertdriver(@RequestBody driver driver) {
        if (driverservice.insertdriver(driver) == true) {
            return new R("true", null);
        } else {
            return new R("flase", null);
        }

    }

    //        删除操作
    @DeleteMapping("/admin/{id}")
    public R deletedriver(@PathVariable Integer id) {
        if (driverservice.deletedriver(id) == true) {
            return new R("true", null);
        } else {
            return new R("flase", null);
        }
    }

    //    删除前的查找操作
    @GetMapping("/admin/{id}")
    public R selectbyid2(@PathVariable Integer id) {
        if (driverservice.selectbyid2(id) != null) {
            return new R("true", driverservice.selectbyid2(id));
        } else {
            return new R("flase", null);
        }
    }


    //    修改数据
    @PutMapping("/admin")
    public R updatedriver(@RequestBody driver driver) {
        if (driverservice.updatebydr(driver)) {
            return new R("true", null);
        } else {
            return new R("flase", null);
        }

    }

    //    分页查询
    @GetMapping("/admin/{currentPage}/{pageSize}")
    public R getpage(@PathVariable Integer currentPage, @PathVariable Integer pageSize, driver driver) {
        IPage<driver> getpage = driverservice.getpage(currentPage, pageSize, driver);
//        如果当前页码值＞总页码值 用最大页码值刷新
        if (currentPage > getpage.getPages()) {
            getpage = driverservice.getpage((int) getpage.getPages(), pageSize, driver);
        }

        System.out.println(getpage);
        return new R("true", getpage);
    }

//    @PostMapping("/admincond")
//    public R getcon(@RequestBody driver driver){
//        List<com.domain.driver> getcon = driverservice.getcon(driver);
//        if(driverservice.getcon(driver)!=null){
//            return new R("true",getcon);
//        }else{
//            return new R("flase",null);
//        }
//    }


    //    个人中心查找driver
    @GetMapping("/driver/{id}")
    public R selectbyid(@PathVariable Integer id) {
        driver driver = driverservice.selectbyid2(id);
        if (driver != null) {
            return new R("true", driver);
        } else {
            return new R("flase", null);
        }
    }

    @PostMapping("/exit")
    public R loginexit(HttpServletRequest request) {
//        admin1 = (Integer) request.getSession().getAttribute("admin");

//        清理session
        request.getSession().removeAttribute("driver");

        return new R("true1", null);

    }


}
