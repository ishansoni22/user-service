package com.ishan.reactivesystem.userservice.dto;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionRequest {

  private Long userId;
  private BigDecimal amount;
  private String reference;

}
