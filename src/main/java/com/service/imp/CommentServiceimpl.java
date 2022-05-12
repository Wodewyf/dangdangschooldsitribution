package com.service.imp;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.domain.comment;
import com.service.CommentService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Mapper.commentMapper;

import java.util.List;
import java.util.Map;


@Service
public class CommentServiceimpl implements CommentService {
    @Autowired
    public commentMapper commentMapper;

    @Override
    public Integer insertcomment(comment comment) {
        int insert = commentMapper.insertcomment1(comment);
        System.out.println(insert);
        return insert;
    }

    @Override
    public comment selectcomment(comment comment) {
        return commentMapper.selectcomment(comment);
    }

    @Override
    public IPage<comment> selectcommentlist(Integer currentPage, Integer pageSize,comment comment) {



        LambdaQueryWrapper<comment> lambdaQueryWrapper = new LambdaQueryWrapper<comment>();
        lambdaQueryWrapper.like(Strings.isNotEmpty(comment.getUsername()),com.domain.comment::getUsername,comment.getUsername());
        lambdaQueryWrapper.like(Strings.isNotEmpty(comment.getDrivername()),com.domain.comment::getDrivername,comment.getDrivername());
        lambdaQueryWrapper.like(isidempty(comment.getId()),com.domain.comment::getId,comment.getId());


        IPage<comment> commentIPage = new Page<comment>(currentPage,pageSize);
        IPage<comment> commentIPage1 = commentMapper.selectPage(commentIPage,lambdaQueryWrapper);
        return commentIPage1;


    }

    @Override
    public Integer deletecomment(Integer commentid) {
        int i = commentMapper.deleteByCommentid(commentid);
        return i;
    }

//    统计数据
    @Override
    public List<Map<String, Object>> selectstaic() {
        return commentMapper.selectstaic();
    }

    public static boolean isidempty(Long id){
        if(id==null){
            return false;
        }else{
            return true;
        }
    }

}
