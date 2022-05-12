package com.Controller;


import com.Controller.utils.R;
import com.domain.admin;
import com.service.adminservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/admins")
public class AdminController {

    @Autowired
    public adminservice adminservice;


    @GetMapping("/login/{adminname}/{adminpassword}")
    public R selectbyadmin(HttpServletRequest request, @PathVariable String adminname, @PathVariable String adminpassword) {

        if (adminservice.selectbyadmin(adminname, adminpassword) == null) {

            return new R("flase", adminservice.selectbyadmin(adminname, adminpassword));
        } else {
            request.getSession().setAttribute("admin", adminservice.selectbyadmin(adminname, adminpassword).getId());
            Integer admin1 = (Integer) request.getSession().getAttribute("admin");
            System.out.println("admin的session是"+admin1);
            return new R("true", adminservice.selectbyadmin(adminname, adminpassword));
        }

    }

    @PostMapping("/exit")
    public R loginexit(HttpServletRequest request) {
//        admin1 = (Integer) request.getSession().getAttribute("admin");

//        清理session
        request.getSession().removeAttribute("admin");

        return new R("true1", null);

    }


}
