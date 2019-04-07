package com.mumu.zhkuconstructparty.biz.mapper;

import com.mumu.zhkuconstructparty.biz.autoCode.pojo.Video;
import com.mumu.zhkuconstructparty.dto.VideoDto.VideoDto;

import java.util.List;

public interface MyVideoMapper {
    List<Video> getVideoList(VideoDto videoDto);

    Integer getVideoListCount(VideoDto videoDto);

    List<Video> findByIds(List<Integer> videoIdList);

    Integer addVideo(VideoDto videoDto);

    Integer editVideoById(VideoDto videoDto);
}
