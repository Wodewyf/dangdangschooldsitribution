package com.Controller;

import com.Controller.utils.R;
import com.service.imageservice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/images")
@Slf4j
public class ImageController {
    //    定义文件存放位置
    @Value("${school.imagepath}")
    private String basepath;

    @Autowired
    public imageservice imageservice;

    @PostMapping("/insertimages")
    public R insertimnages(MultipartFile file) throws IOException {
//        file只是一个临时文件，需要转存到其他位置


//        得到初始文件名
        String originalFilename = file.getOriginalFilename();
//        获取文件后缀
        String substring = originalFilename.substring(originalFilename.lastIndexOf("."));
//        使用uuid重新生成文件名
        String newfilename = UUID.randomUUID().toString();

//        创建目录
        File dir = new File(basepath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        file.transferTo(new File(basepath + newfilename + substring));


        return new R("true", newfilename + substring);
    }


    @GetMapping("/download")
    public R downloadimage(String image, HttpServletResponse response) {
        try {
            System.out.println(image);
            FileInputStream fileInputStream = new FileInputStream(new File(basepath + image));
            ServletOutputStream outputStream = response.getOutputStream();

            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = fileInputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, len);
                outputStream.flush();

            }
            outputStream.close();
            fileInputStream.close();
            return null;
        } catch (Exception e) {
            return new R("false", null);
        }

    }
}
