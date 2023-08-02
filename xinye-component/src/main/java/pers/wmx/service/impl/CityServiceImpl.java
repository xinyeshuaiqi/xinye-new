package pers.wmx.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pers.wmx.model.City;
import pers.wmx.dao.CityMapper;
import pers.wmx.service.CityService;


/**
 * @author: wangmingxin02
 * @create: 2019-03-26
 **/
@Service
public class CityServiceImpl implements CityService {
    @Autowired
    private CityMapper cityMapper;

    @Override
    public City findById(Long id) {
        return cityMapper.findById(id);
    }

    @Override
    public List<City> findCityList() {
        return cityMapper.findCityList();
    }

    @Override
    public Map<Long, City> batchGetCityInfo(Collection<Long> cityIds) {
        return null;
    }

}
