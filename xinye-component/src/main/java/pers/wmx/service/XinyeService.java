package pers.wmx.service;

import java.util.Map;

/**
 * @author wangmingxin03
 * Created on 2023-07-26
 */
public interface XinyeService {
    Map<String, Object> get();

    Map<String, Object> setRedis();

    Map<String, Object> getRedis();
}
