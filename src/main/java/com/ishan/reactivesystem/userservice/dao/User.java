package com.ishan.reactivesystem.userservice.dao;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

//Cannot use @Entity as that is a JPA annotation
@Getter
@Setter
@Table("users")
public class User {

  @Id
  private Long id;
  private String name;
  private BigDecimal balance;

}
