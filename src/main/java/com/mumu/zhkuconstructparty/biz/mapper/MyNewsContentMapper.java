package com.mumu.zhkuconstructparty.biz.mapper;

import com.mumu.zhkuconstructparty.biz.autoCode.pojo.NewsContent;

public interface MyNewsContentMapper {
    NewsContent findNewsContentByNewsId(Integer id);

    Integer insert(NewsContent newsContent);
}
