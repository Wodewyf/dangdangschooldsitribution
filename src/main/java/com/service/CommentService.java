package com.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.domain.comment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface CommentService {

    Integer insertcomment(comment comment);

    comment selectcomment(comment comment);

//    分页查询
    IPage<comment> selectcommentlist(Integer currentPage, Integer pageSize,comment comment);

//    删除
    Integer deletecomment(Integer commentid);

    List<Map<String,Object>> selectstaic();
}

