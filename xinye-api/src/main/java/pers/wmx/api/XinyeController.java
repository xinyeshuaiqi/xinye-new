package pers.wmx.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pers.wmx.service.XinyeService;

/**
 * @author wangmingxin03
 * Created on 2023-07-25
 */
@RestController
@RequestMapping("/test")
public class XinyeController {
    @Autowired
    private XinyeService xinyeService;

    @RequestMapping("/test")
    public Map<String, Object> test() {
        Map<String, Object> result = xinyeService.get();
        return result;
    }
}
