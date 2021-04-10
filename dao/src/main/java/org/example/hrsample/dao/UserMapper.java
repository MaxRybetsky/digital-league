package org.example.hrsample.dao;

import org.apache.ibatis.annotations.Mapper;
import org.example.hrsample.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Mapper
@Repository
public interface UserMapper {
    Optional<UserEntity> getByLogin(String login);
}
