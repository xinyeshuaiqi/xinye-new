package pers.wmx.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

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
