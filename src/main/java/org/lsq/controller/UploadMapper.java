package org.lsq.controller;


import lombok.extern.slf4j.Slf4j;
import org.lsq.pojo.Result;
import org.lsq.utils.AliOSSUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
public class UploadMapper {
//    @PostMapping("/upload")
//    public Result upload(String username, Integer age, MultipartFile image) throws Exception {
//        log.info("接收到上传文件");
//        String originName = image.getOriginalFilename();
//
//        //使用uuid的方式来，防止文件名相同upload被覆盖。uuid：唯一通用标识符
//        String extname = originName.substring(originName.lastIndexOf("."));
//        String newFileName = UUID.randomUUID().toString() + extname;
//
//        image.transferTo(new File("D:\\javalearn\\Springbootwebproject\\images\\" + newFileName));
//        return Result.success();
    @Autowired
    private AliOSSUtils aliOSSUtils;

    //上传本地图片给OSS，返回对应的网络图片路径
    @PostMapping("/upload")
    public Result upload(MultipartFile image) throws IOException {
        log.info("阿里云上传图片");
        String url = aliOSSUtils.upload(image);
        return Result.success(url);
    }

}
