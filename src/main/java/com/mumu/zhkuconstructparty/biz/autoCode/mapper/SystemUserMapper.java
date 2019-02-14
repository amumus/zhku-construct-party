package com.mumu.zhkuconstructparty.biz.autoCode.mapper;

import com.mumu.zhkuconstructparty.biz.autoCode.pojo.SystemUser;
import java.util.List;

public interface SystemUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SystemUser record);

    SystemUser selectByPrimaryKey(Integer id);

    List<SystemUser> selectAll();

    int updateByPrimaryKey(SystemUser record);
}