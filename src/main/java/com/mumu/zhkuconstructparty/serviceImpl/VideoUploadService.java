package com.mumu.zhkuconstructparty.serviceImpl;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.annotation.Resources;
import java.io.File;
import java.net.URL;
import java.util.Date;

@Service
public class VideoUploadService {
    public static String SecretId = "AKIDsVmqfYR0XO8JKoCB2eBUFpEOK3Zs3mOm";
    public static String SecretKey = "JNEpMklaCsLZYJsHQUJsFOJ0ThhSxDID";


    public String upload(File localFile){

        // 1 初始化用户身份信息（secretId, secretKey）。
        COSCredentials cred = new BasicCOSCredentials(SecretId, SecretKey);
        // 2 设置bucket的区域, COS地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        // clientConfig中包含了设置 region, https(默认 http), 超时, 代理等 set 方法, 使用可参见源码或者接口文档 FAQ 中说明。
        ClientConfig clientConfig = new ClientConfig(new Region("ap-guangzhou"));
        // 3 生成 cos 客户端。
        COSClient cosClient = new COSClient(cred, clientConfig);
        // bucket的命名规则为{name}-{appid} ，此处填写的存储桶名称必须为此格式
        // 指定要上传到的存储桶
        String bucketName = "construct-party-1256364044";


//        File localFile = new File("D:\\HBuilderProjects\\ConstructParty\\ConstructParty\\static\\img\\score_bg.jpg");
//        File localFile = new File("C:\\Users\\mumu\\Pictures\\1.jpg");
        System.out.println(localFile.getName());
        // 指定要上传到 COS 上对象键
//        String key = "video/1.jpg";
        String key = "/video/"+(new Date()).getTime() +localFile.getName();
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, localFile);
        PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
        String etag = putObjectResult.getETag();
        System.out.println(etag);
        // 关闭客户端(关闭后台线程)
        cosClient.shutdown();

        return "http://construct-party-1256364044.cos.ap-guangzhou.myqcloud.com"+key;
    }

    public static void main(String[] args) {
        VideoUploadService videoUploadService = new VideoUploadService();
       String s= videoUploadService.upload( new File("C:\\Users\\Administrator\\Videos\\test.mp4"));
        System.out.println(s);
        // 1 初始化用户身份信息（secretId, secretKey）。
//        COSCredentials cred = new BasicCOSCredentials(SecretId, SecretKey);
//        // 2 设置bucket的区域, COS地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
//        // clientConfig中包含了设置 region, https(默认 http), 超时, 代理等 set 方法, 使用可参见源码或者接口文档 FAQ 中说明。
//        ClientConfig clientConfig = new ClientConfig(new Region("ap-guangzhou"));
//        // 3 生成 cos 客户端。
//        COSClient cosClient = new COSClient(cred, clientConfig);
//        // bucket的命名规则为{name}-{appid} ，此处填写的存储桶名称必须为此格式
//        // 指定要上传到的存储桶
//        String bucketName = "construct-party-1256364044";
//        URL url = cosClient.generatePresignedUrl(bucketName,"test1.mp4",null);
//        System.out.println(url.toString());
    }


}
