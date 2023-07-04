package com.shravani.TimeSheetApp.service;
import com.shravani.TimeSheetApp.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    List<UserDto> findAllUsers();

    UserDto findUserByEmail(String email);

    UserDto saveUser(UserDto userDto);

    UserDto findUserByName(String userName);
}
