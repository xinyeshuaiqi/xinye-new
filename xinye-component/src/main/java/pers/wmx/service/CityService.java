package pers.wmx.service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import pers.wmx.model.City;


public interface CityService {

    public City findById(Long id);

    public List<City> findCityList();

     Map<Long, City> batchGetCityInfo(Collection<Long> cityIds);

     default City getCityInfo(long cityId) {
         return batchGetCityInfo(Collections.singleton(cityId)).get(cityId);
     }

}
