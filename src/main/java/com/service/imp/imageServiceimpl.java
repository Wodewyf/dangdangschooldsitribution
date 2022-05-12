package com.service.imp;

import com.Mapper.imageMapper;
import com.service.imageservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class imageServiceimpl implements imageservice {
    @Autowired
    public imageMapper imageMapper;

    @Override
    public Integer insertimage(String image) {
        Integer insertimage = imageMapper.insertimage(image);
        return insertimage;
    }
}
