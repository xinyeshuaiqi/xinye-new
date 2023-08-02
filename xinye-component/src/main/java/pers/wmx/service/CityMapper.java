package pers.wmx.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import pers.wmx.model.City;

@Mapper
public interface CityMapper {
    @Select("select * from  city where id = #{id}")
    public City findById(Long id);

    @Select("select * from  city")
    public List<City> findCityList();

}
