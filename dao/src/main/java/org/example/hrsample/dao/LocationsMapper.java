package org.example.hrsample.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.example.hrsample.entity.LocationsEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Mapper
@Repository
public interface LocationsMapper {
    @Select("select * from locations where location_id = #{id}")
    Optional<LocationsEntity> getLocationById(Integer id);

    @Select("select * from locations")
    List<LocationsEntity> getAll();

    @Insert("insert into locations(street_address, city) VALUES " +
            "(#{streetAddress}, #{city})")
    @Options(useGeneratedKeys = true, keyProperty = "locationsId")
    void insert(LocationsEntity entity);
}
