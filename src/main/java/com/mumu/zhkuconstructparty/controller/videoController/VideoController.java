package com.mumu.zhkuconstructparty.controller.videoController;

import com.mumu.zhkuconstructparty.biz.autoCode.pojo.Video;
import com.mumu.zhkuconstructparty.common.ResultObject;
import com.mumu.zhkuconstructparty.dto.VideoDto.VideoDto;
import com.mumu.zhkuconstructparty.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
public class VideoController {
    @Autowired
    VideoService videoService;

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

}
