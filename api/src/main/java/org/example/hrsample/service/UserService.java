package org.example.hrsample.service;

import org.example.hrsample.dto.UserDto;

public interface UserService {
    UserDto findByLogin(String login);
}
