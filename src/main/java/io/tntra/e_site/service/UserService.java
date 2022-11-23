package io.tntra.e_site.service;

import io.tntra.e_site.dto.UserDto;
import io.tntra.e_site.model.User;

import java.util.List;


public interface UserService  {

    void  saveUser(UserDto userDto);
    User findUserByEmail(String email);
    
    List<UserDto> findAllUsers();


}
