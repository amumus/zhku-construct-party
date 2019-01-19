package com.mumu.zhkuconstructparty.biz.mapper;

import com.mumu.zhkuconstructparty.biz.autoCode.pojo.News;
import com.mumu.zhkuconstructparty.vo.NewsVo.NewsQueryVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;

//@Mapper
//@Repository
public interface MyNewsMapper {
    List<News> selectNews(NewsQueryVo vo);
//    List<News> selectAllNews();
    int count();
}
