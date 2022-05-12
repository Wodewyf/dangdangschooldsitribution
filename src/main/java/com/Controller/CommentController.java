package com.Controller;


import com.Controller.utils.R;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.domain.comment;
import com.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    public CommentService commentService;

    @PostMapping
    public R insertcomment(@RequestBody comment comment) {
        Integer insertcomment = commentService.insertcomment(comment);
        if (insertcomment != 0) {
            return new R("true", null);
        } else {
            return new R("flase", null);
        }

    }

    @PostMapping("/comment")
    public R selectcomment(@RequestBody comment comment) {
        com.domain.comment selectcomment = commentService.selectcomment(comment);
        return new R("true", selectcomment);
    }

    @GetMapping("/{currentPage}/{pageSize}")
    public R selectcommentlist(@PathVariable Integer currentPage,@PathVariable Integer pageSize,comment comment){
        IPage<comment> selectcommentlist = commentService.selectcommentlist(currentPage, pageSize,comment);

//        如果当前页面比最大页面还大，返回最大页数
        if(selectcommentlist.getPages()<currentPage){
            selectcommentlist=commentService.selectcommentlist(Math.toIntExact((Long) selectcommentlist.getPages()),pageSize,comment);
        }


            return new R("true",selectcommentlist);

    }

    @DeleteMapping("/delete/{commentid}")
    public R deletecommnetid(@PathVariable Integer commentid){
        Integer deletecomment = commentService.deletecomment(commentid);
        if(deletecomment!=0){
            return new R("true",null);
        }else{
            return new R("flase",null);
        }
    }

//    统计 每个骑手的的评价单数和平均评分
    @GetMapping("/table")
    public R selectstaic(){
        List<Map<String, Object>> selectstaic = commentService.selectstaic();
        return new R("true",selectstaic);

    }

}
