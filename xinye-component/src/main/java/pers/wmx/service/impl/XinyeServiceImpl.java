package pers.wmx.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import pers.wmx.service.XinyeService;

/**
 * @author wangmingxin03
 * Created on 2023-07-26
 */
@Service
public class XinyeServiceImpl implements XinyeService {
    @Override
    public Map<String, Object> get(){
        Map<String, Object> result = new HashMap();
        result.put("hello world", "666");
        return result;
    }
}
