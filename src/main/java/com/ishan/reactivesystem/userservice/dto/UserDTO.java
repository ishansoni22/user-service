package com.ishan.reactivesystem.userservice.dto;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

  private Long id;
  private String name;
  private BigDecimal balance;

}
