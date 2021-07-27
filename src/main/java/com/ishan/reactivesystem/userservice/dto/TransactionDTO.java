package com.ishan.reactivesystem.userservice.dto;

import com.ishan.reactivesystem.userservice.dao.TransactionStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionDTO {

  private Long id;
  private Long userId;
  private LocalDateTime time;
  private TransactionStatus status;
  private BigDecimal amount;
  private String reference;

}
