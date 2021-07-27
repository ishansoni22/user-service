package com.ishan.reactivesystem.userservice.service;

import com.ishan.reactivesystem.userservice.dao.User;
import com.ishan.reactivesystem.userservice.dto.UserDTO;
import org.springframework.beans.BeanUtils;

public final class UserMapper {

  private UserMapper() {

  }

  public static User toUser(UserDTO userDTO) {
    User user = new User();
    BeanUtils.copyProperties(userDTO, user);
    return user;
  }

  public static UserDTO toUserDTO(User user) {
    UserDTO userDTO = new UserDTO();
    BeanUtils.copyProperties(user, userDTO);
    return userDTO;
  }

}
