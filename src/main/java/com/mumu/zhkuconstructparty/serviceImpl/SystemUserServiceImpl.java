package com.mumu.zhkuconstructparty.serviceImpl;

import com.mumu.zhkuconstructparty.biz.mapper.MySystemUserMapper;
import com.mumu.zhkuconstructparty.service.SystemUserService;
import com.mumu.zhkuconstructparty.vo.SystemUserVo.SystemUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SystemUserServiceImpl implements SystemUserService{

    @Autowired
    private MySystemUserMapper mySystemUserMapper;

    @Override
    public Map<String,Integer> login(SystemUserVo vo) {
        int r =  mySystemUserMapper.login(vo);
        Map map = new HashMap();
        map.put("result",r);
        return map;
    }
}
