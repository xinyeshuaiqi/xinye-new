package pers.wmx.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pers.wmx.aspect.ActionAspect;
import pers.wmx.service.XinyeService;
import pers.wmx.utils.XinyeSpringHelper;

/**
 * @author wangmingxin03
 * Created on 2023-07-25
 */
@RestController
@RequestMapping("/test")
public class XinyeController {
    @Autowired
    private XinyeService xinyeService;

    @Autowired
    private XinyeSpringHelper xinyeSpringHelper;

    @ActionAspect(value = "do aspect")
    @RequestMapping("/test")
    public Map<String, Object> test() {
        Map<String, Object> result = xinyeService.get();
        return result;
    }

    @RequestMapping("/setRedis")
    public Map<String, Object> setRedis() {
        Map<String, Object> result = xinyeService.setRedis();
        return result;
    }

    @RequestMapping("/getRedis")
    public Map<String, Object> getRedis() {
        Map<String, Object> result = xinyeService.getRedis();
        return result;
    }

    @RequestMapping("/testSpring")
    public Map<String, Object> testSpring() {
        XinyeService bean = XinyeSpringHelper.getBean(XinyeService.class);
        return bean.get();
    }
}
