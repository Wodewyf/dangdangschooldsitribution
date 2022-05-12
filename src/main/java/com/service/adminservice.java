package com.service;

import com.domain.admin;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public interface adminservice {
    admin selectbyadmin(String adminname, String adminpassword);
}
