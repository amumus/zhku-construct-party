package com.mumu.zhkuconstructparty.service;

import com.mumu.zhkuconstructparty.common.CommonException;
import com.mumu.zhkuconstructparty.vo.UserVo.UserQueryVo;
import com.mumu.zhkuconstructparty.vo.UserVo.UserVo;

import java.util.List;
import java.util.Map;

public interface UserService {
    Map listUser(UserQueryVo vo) throws CommonException;
    Integer addUser(UserQueryVo vo) throws CommonException;
    Integer updateUser(UserQueryVo vo)  throws CommonException;
    Integer deleteByIdentityCode(UserQueryVo vo) throws CommonException;

}
