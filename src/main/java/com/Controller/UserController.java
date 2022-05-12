package com.Controller;


import com.Controller.utils.R;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.domain.driver;
import com.domain.user;
import com.service.userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    public userservice userservice;

    @GetMapping("/login/{username}/{userpassword}")
    public R selectbyuser(HttpServletRequest request,@PathVariable String username, @PathVariable String userpassword) {
        if (userservice.selectbyuserid(username, userpassword) == null) {
            return new R("flase", userservice.selectbyuserid(username, userpassword));
        } else {
//            存session
            request.getSession().setAttribute("user", userservice.selectbyuserid(username, userpassword).getId());
            return new R("true", userservice.selectbyuserid(username, userpassword));
        }


    }

    @GetMapping
    public List<user> selectall() {
        return userservice.selectall();
    }

    @PostMapping
    public R insertusername(@RequestBody user user) {
        Integer intscucess = userservice.selectbyusername(user);
        if (intscucess == 0) {
            return new R("flase", userservice.selectbyusername(user));

        } else {
            return new R("true", userservice.selectbyusername(user));
        }
    }

    @GetMapping("/{username}")
    public R selectusername1(@PathVariable String username) {
        Boolean beizhuce = userservice.selectbyusername1(username);
        if (beizhuce == true) {
            return new R("true", null);
        } else {
            return new R("flase", null);
        }
    }

    @PostMapping("/exit")
    public R loginexit(HttpServletRequest request) {
//        admin1 = (Integer) request.getSession().getAttribute("admin");

//        清理session
        request.getSession().removeAttribute("user");

        return new R("true1", null);

    }

    @GetMapping("/chazhaoid/{id}")
    public R selectbyid(@PathVariable Integer id) {
        if (userservice != null) {
            return new R("true", userservice.selectbyid(id));
        } else {
            return new R("flase", userservice.selectbyid(id));
        }
    }

    @PutMapping("/chazhaoid")
    public R updateuserinfo(@RequestBody user user) {

        if (userservice.updateuserinfo(user) == true) {
            return new R("true", null);
        } else {
            return new R("flase", null);
        }

    }

    @GetMapping("/admin/{currentPage}/{pageSize}")
    public R getpage(@PathVariable Integer currentPage, @PathVariable Integer pageSize, user user) {
        IPage<com.domain.user> getpage = userservice.getpage(currentPage, pageSize, user);
        if (currentPage > getpage.getPages()) {
            getpage = userservice.getpage((int) getpage.getPages(), pageSize, user);
        }

        return new R("true", getpage);
    }

    //        新增操作
    @PostMapping("/admin")
    public R insertuser(@RequestBody user user) {
        if (userservice.insert(user) != 0) {
            return new R("true", null);
        } else {
            return new R("flase", null);
        }

    }

    //        删除操作
    @DeleteMapping("/admin/{id}")
    public R deleteuser(@PathVariable Integer id) {
        if (userservice.deleteuser(id) != null) {
            return new R("true", null);
        } else {
            return new R("flase", null);
        }
    }

    //    删除前查找
    @GetMapping("/admin/{id}")
    public R selectbyid2(@PathVariable Integer id) {
        if (userservice.selectbyid2(id) != null) {
            return new R("true", userservice.selectbyid2(id));
        } else {
            return new R("flase", null);
        }
    }

    //    修改数据
    @PutMapping("/admin")
    public R updateuser(@RequestBody user user) {
        if (userservice.updatebydr(user) != 0) {
            return new R("true", null);
        } else {
            return new R("flase", null);
        }

    }
}
