package com.mumu.zhkuconstructparty.controller.videoController;

import com.mumu.zhkuconstructparty.biz.autoCode.pojo.Video;
import com.mumu.zhkuconstructparty.common.ResultObject;
import com.mumu.zhkuconstructparty.dto.VideoDto.VideoDto;
import com.mumu.zhkuconstructparty.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
public class VideoController {
    @Autowired
        private VideoService videoService;

    @RequestMapping("/uniApp/video/listVideo")
    @ResponseBody
    public ResultObject getUniVideoList(VideoDto videoDto){
        ResultObject resultObject = ResultObject.successResult();
        Map data = videoService.getUniVideoList(videoDto);
        resultObject.setData(data);
        return resultObject;
    }

    @RequestMapping("/uniApp/video/getVideo")
    @ResponseBody
    public ResultObject getUniVideoList(Integer id){
        ResultObject resultObject = ResultObject.successResult();
        Video video = videoService.getUniVideoDetial(id);
        resultObject.setData(video);
        return resultObject;
    }

    @RequestMapping("/portal/uploadVideo")
    @ResponseBody
    public String uploadVideo(MultipartFile file) {
        return videoService.uploadVideo(file);
    }

    @RequestMapping("/portal/video/addVideo")
    @ResponseBody ResultObject addVideo(@RequestBody  VideoDto videoDto){
        ResultObject resultObject = ResultObject.successResult();
        videoDto.setCreated(new Date());
        Integer result = videoService.addVideo(videoDto);
        return resultObject;
    }

    @RequestMapping("/portal/video/listVideo")
    @ResponseBody
    public ResultObject getVueVideoList(VideoDto videoDto){
        ResultObject resultObject = ResultObject.successResult();
        Map data = videoService.getUniVideoList(videoDto);
        resultObject.setData(data);
        return resultObject;
    }
    @RequestMapping("/portal/video/editVideo")
    @ResponseBody ResultObject editVideo(@RequestBody  VideoDto videoDto){
        ResultObject resultObject = ResultObject.successResult();
//        videoDto.setCreated(new Date());
        Integer result = videoService.editVideo(videoDto);
        return resultObject;
    }

    @RequestMapping("/portal/video/deleteVideo")
    @ResponseBody ResultObject deleteVideo(Integer id){
        ResultObject resultObject = ResultObject.successResult();
//        videoDto.setCreated(new Date());
        Integer result = videoService.deleteVideo(id);
        return resultObject;
    }
    @RequestMapping("/portal/video/batchDeleteVideo")
    @ResponseBody ResultObject batchDeleteNews(List<Integer> list){
        ResultObject resultObject = ResultObject.successResult();
//        videoDto.setCreated(new Date());
        for(Integer id : list){
            Integer result = videoService.deleteVideo(id);
        }
        return resultObject;
    }
}
