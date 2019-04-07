package com.mumu.zhkuconstructparty.service;

import com.mumu.zhkuconstructparty.biz.autoCode.pojo.Video;
import com.mumu.zhkuconstructparty.dto.VideoDto.VideoDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface VideoService {
    Map getUniVideoList(VideoDto videoDto);

    Video getUniVideoDetial(Integer id);


    String uploadVideo(MultipartFile file);

    Integer addVideo(VideoDto videoDto);

    Integer editVideo(VideoDto videoDto);

    Integer deleteVideo(Integer id);
}
