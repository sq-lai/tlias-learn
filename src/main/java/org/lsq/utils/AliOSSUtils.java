package org.lsq.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import org.lsq.pojo.AliosProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.util.Properties;
import java.util.UUID;

/**
 * 阿里云 OSS 工具类
 */
@Component
public class AliOSSUtils {
//    @Value("${aliyun.oss.endpoint}")
//    private String endpoint;
//    @Value("${aliyun.oss.accessKeyId}")
//    private String accessKeyId;
//    @Value("${aliyun.oss.accessKeySecret}")
//    private String accessKeySecret;
//    @Value("${aliyun.oss.bucketName}")
//    private String bucketName;
//    @Value("${aliyun.oss.objectName}")
//    private String objectName;
//
// 通过创建一个实体类，交给ioc容器管理，这边在DI。在那边还要注明configurationproperties——联系到配置文件

    @Autowired
    private AliosProperties aliosProperties;




    /**
     * 实现上传图片到OSS
     */
    public String upload(MultipartFile file) throws IOException {
        // 获取上传的文件的输入流
        InputStream inputStream = file.getInputStream();

        // 避免文件覆盖
        String originalFilename = file.getOriginalFilename();
        String fileName = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));

        //上传文件到 OSS
        OSS ossClient = new OSSClientBuilder().build(aliosProperties.getEndpoint(), aliosProperties.getAccessKeyId(), aliosProperties.getAccessKeySecret());
        // 创建PutObjectRequest对象。
        String objectName = aliosProperties.getObjectName() +"/" + fileName;
        PutObjectRequest putObjectRequest = new PutObjectRequest(aliosProperties.getBucketName(), objectName, inputStream);
        // 设置该属性可以返回response。如果不设置，则返回的response为空。
        putObjectRequest.setProcess("true");
        // 创建PutObject请求。
        PutObjectResult result = ossClient.putObject(putObjectRequest);
        // 如果上传成功，则返回200。
        System.out.println(result.getResponse().getStatusCode());
//        ossClient.putObject(bucketName, fileName, inputStream);

        //文件访问路径
        String url = "https://" + aliosProperties.getBucketName() + "." + aliosProperties.getEndpoint() + "/" + objectName;
        // 关闭ossClient
        ossClient.shutdown();
        return url;// 把上传到oss的路径返回
    }

}
