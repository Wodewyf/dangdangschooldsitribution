package com.service.imp;

import com.Mapper.adminMapper;
import com.domain.admin;
import com.service.adminservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class AdminServiceimpl implements adminservice {

    @Autowired
    private adminMapper adminMapper;


    @Override
    public admin selectbyadmin(String adminname, String adminpassword) {


        return adminMapper.selectbyadminid(adminname, adminpassword);
    }
}
