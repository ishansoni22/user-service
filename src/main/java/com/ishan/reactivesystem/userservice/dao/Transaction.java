package com.ishan.reactivesystem.userservice.dao;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Table("transactions")
public class Transaction {

  public Transaction() {
    this.time = LocalDateTime.now();
  }

  @Id
  private Long id;
  private Long userId;
  private LocalDateTime time;
  private TransactionStatus status;
  private BigDecimal amount;
  private String reference;

}
