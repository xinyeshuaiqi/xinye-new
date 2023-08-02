package pers.wmx.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import pers.wmx.model.City;
import pers.wmx.service.CityService;

/**
 * @author: wangmingxin02
 * @create: 2019-03-26
 **/

@Controller
public class CityController {

    @Autowired
    CityService cityService;

    @RequestMapping("/api/city/{id}")
    public String findCityById(Model model, @PathVariable("id") Long id){

        City city = cityService.findById(id);
        model.addAttribute("city",city);

        return "index";
    }

    @RequestMapping("/api/city")
    public String findCityList(Model model){

        List<City> cityList = cityService.findCityList();
        model.addAttribute("cityList",cityList);

        return "citylist";
    }

    //前端body里传123，456即可
    @ResponseBody
    @RequestMapping("/cities")
    public String testList(@RequestParam("cities") List<String> cities){
        return JSON.toJSONString(cities);
    }

    @RequestMapping("/bytes")
    public ResponseEntity<byte[]> getBytes() {
        return new ResponseEntity<>("hehe".getBytes(), HttpStatus.OK);
    }
}
