package pers.wmx.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import pers.wmx.service.XinyeService;

/**
 * @author wangmingxin03
 * Created on 2023-07-26
 */
@Service
public class XinyeServiceImpl implements XinyeService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Map<String, Object> get(){
        Map<String, Object> result = new HashMap();
        result.put("hello world", "666");
        return result;
    }

    @Override
    public Map<String, Object> setRedis() {
        Map<String, Object> result = new HashMap();
        stringRedisTemplate.opsForValue()
                .set("hello", "world");
        return result;
    }

    @Override
    public Map<String, Object> getRedis() {
        Map<String, Object> result = new HashMap();
        String str = stringRedisTemplate.opsForValue().get("hello");
        result.put("result", str);
        return result;
    }
}
