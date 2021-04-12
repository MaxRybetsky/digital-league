package org.example.hrsample.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.hrsample.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Mapper
@Repository
public interface UserMapper {
    Optional<UserEntity> getByLogin(String login);
}
