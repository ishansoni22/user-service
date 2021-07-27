package com.ishan.reactivesystem.userservice.controller;

import com.ishan.reactivesystem.userservice.dto.UserDTO;
import com.ishan.reactivesystem.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1/users")
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping
  public Flux<UserDTO> getAll() {
    return userService.getAll();
  }

  @GetMapping("/{id}")
  public Mono<ResponseEntity<UserDTO>> get(@PathVariable("id") Long id) {
    return userService.get(id)
        .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @PostMapping
  public Mono<UserDTO> add(@RequestBody Mono<UserDTO> userDTO) {
    return userService.insert(userDTO);
  }

  @PutMapping("/{id}")
  public Mono<ResponseEntity<UserDTO>> update(@PathVariable("id") Long id,
      @RequestBody Mono<UserDTO> userDTO) {
    return userService.update(id, userDTO)
        .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public Mono<Void> delete(@PathVariable("id") Long id) {
    return userService.delete(id);
  }

}
