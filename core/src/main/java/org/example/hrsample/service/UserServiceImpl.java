package org.example.hrsample.service;

import lombok.RequiredArgsConstructor;
import org.example.hrsample.dao.UserMapper;
import org.example.hrsample.dto.UserDto;
import org.example.hrsample.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final ModelMapper modelMapper;

    @Override
    public UserDto findByLogin(String login) {
        UserEntity userEntity = userMapper.getByLogin(login).orElseThrow(
                () -> new RuntimeException("No user with login = " + login)
        );
        return modelMapper.map(userMapper, UserDto.class);
    }
}
