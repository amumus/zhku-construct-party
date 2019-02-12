package com.mumu.zhkuconstructparty.biz.autoCode.mapper;

import com.mumu.zhkuconstructparty.biz.autoCode.pojo.NewsContent;
import java.util.List;

public interface NewsContentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NewsContent record);

    NewsContent selectByPrimaryKey(Integer id);

    List<NewsContent> selectAll();

    int updateByPrimaryKey(NewsContent record);
}