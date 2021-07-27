package com.ishan.reactivesystem.userservice.service;

import com.ishan.reactivesystem.userservice.dao.UserRepository;
import com.ishan.reactivesystem.userservice.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public Flux<UserDTO> getAll() {
    return userRepository.findAll()
        .map(UserMapper::toUserDTO);
  }

  public Mono<UserDTO> get(Long id) {
    return userRepository.findById(id)
        .map(UserMapper::toUserDTO);
  }

  public Mono<UserDTO> insert(Mono<UserDTO> userDTO) {
    return userDTO
        .map(UserMapper::toUser)
        .flatMap(userRepository::save)
        .map(UserMapper::toUserDTO);
  }

  public Mono<UserDTO> update(Long id, Mono<UserDTO> userDTO) {
    return userRepository.findById(id)
        .flatMap(user ->
            userDTO.map(UserMapper::toUser)
            .doOnNext(u -> u.setId(user.getId()))
        ).flatMap(userRepository::save)
        .map(UserMapper::toUserDTO);
  }

  public Mono<Void> delete(Long id) {
    return userRepository.deleteById(id);
  }

}