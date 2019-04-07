package com.mumu.zhkuconstructparty.serviceImpl;

import com.mumu.zhkuconstructparty.biz.autoCode.mapper.VideoMapper;
import com.mumu.zhkuconstructparty.biz.autoCode.pojo.Video;
import com.mumu.zhkuconstructparty.biz.mapper.MyVideoMapper;
import com.mumu.zhkuconstructparty.dto.VideoDto.VideoDto;
import com.mumu.zhkuconstructparty.service.VideoService;
import org.apache.http.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VideoServiceImpl implements VideoService {
    @Autowired
    private MyVideoMapper myVideoMapper;

    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private VideoUploadService videoUploadService;

    @Override
    public Map getUniVideoList(VideoDto videoDto) {
        videoDto.setPageStart(videoDto.getPageStart() * videoDto.getPageNum());
        List<Video> list = myVideoMapper.getVideoList(videoDto);
        Integer count = myVideoMapper.getVideoListCount(videoDto);
        Map result = new HashMap();
        result.put("list",list);
        result.put("count",count);
        return result;
    }

    @Override
    public Video getUniVideoDetial(Integer id){
        Video video = videoMapper.selectByPrimaryKey(id);
        return video;
    }

    @Override
    public String uploadVideo(MultipartFile file) {
        File f = null;
        try {
            f=File.createTempFile(file.getOriginalFilename(), ".mp4");
            file.transferTo(f);
            f.deleteOnExit();
            return videoUploadService.upload( f );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer addVideo(VideoDto videoDto) {
        return myVideoMapper.addVideo(videoDto);
    }

    @Override
    public Integer editVideo(VideoDto videoDto) {
        return myVideoMapper.editVideoById(videoDto);
    }

    @Override
    public Integer deleteVideo(Integer id) {
        return videoMapper.deleteByPrimaryKey(id);
    }


}
