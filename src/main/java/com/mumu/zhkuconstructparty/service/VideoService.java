package com.mumu.zhkuconstructparty.service;

import com.mumu.zhkuconstructparty.biz.autoCode.pojo.Video;
import com.mumu.zhkuconstructparty.dto.VideoDto.VideoDto;

import java.util.Map;

public interface VideoService {
    Map getUniVideoList(VideoDto videoDto);

    Video getUniVideoDetial(Integer id);
}
