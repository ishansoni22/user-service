package com.ishan.reactivesystem.userservice.dao;

import java.math.BigDecimal;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveCrudRepository<User, Long> {

  @Modifying
  @Query(
      "update users set balance = balance - :amount where id = :id and balance >= :amount"
  )
  Mono<Boolean> updateUserBalance(Long id, BigDecimal amount);

}
