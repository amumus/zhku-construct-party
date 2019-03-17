package com.mumu.zhkuconstructparty.serviceImpl;

import com.mumu.zhkuconstructparty.biz.autoCode.mapper.ContentMapper;
import com.mumu.zhkuconstructparty.biz.autoCode.pojo.Content;
import com.mumu.zhkuconstructparty.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentServiceImpl implements ContentService{

    @Autowired
    ContentMapper contentMapper;

    @Override
    public List<Content> getUniList() {
        return contentMapper.selectAll();
    }
}
